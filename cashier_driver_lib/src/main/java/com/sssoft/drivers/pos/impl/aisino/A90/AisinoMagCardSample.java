package com.sssoft.drivers.pos.impl.aisino.A90;


import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;

import com.icbc.smartpos.deviceservice.aidl.IMagCardReader;
import com.icbc.smartpos.deviceservice.aidl.MagCardListener;
import com.sssoft.drivers.pos.aidl.CardListener;
import com.sssoft.drivers.pos.service.AIDLService;

public class AisinoMagCardSample{
	private CardListener cardlistener;
	private IMagCardReader iMagCardReader;
	private int mTimeout;
	private int flag = 0;
	private Timer timer;
	public AisinoMagCardSample() throws RemoteException{
		iMagCardReader = IMagCardReader.Stub.asInterface(AIDLService.getDeviceServiceA90().getMagCardReader());
	}
	
	//磁条卡寻卡
	public void searchCard(int timeout,CardListener allCardlistener) {
		cardlistener = allCardlistener;
		mTimeout = timeout;
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					flag = 0;
					timer = new Timer();  
			        timer.schedule(new TimerTask() {  
			          
			            @Override  
			            public void run() {  
			            	if(flag ==0){
			            		try {
									cardlistener.onError("XX", "异常，请重试");
									timer.cancel();
								} catch (RemoteException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
			            	}
			            	
			            }  }, (mTimeout+2)*1000);
			        iMagCardReader.searchCard(mTimeout, new MagCardListener.Stub() {

						@Override
						public void onSuccess(Bundle track)
								throws RemoteException {
							flag = 1;
							if(track==null){
								cardlistener.onError("XX", "读卡失败，track信息未空");
							}else{
								String[] cardInfo = {track.getString("PAN"),track.getString("TRACK2"),track.getString("TRACK3")}; 
								if(cardInfo[0]==null || cardInfo[0].equals("") || "00000000000000000000".equals(cardInfo[0])){
									cardlistener.onError("XX", "读卡失败，卡号获取失败");
								}else{
									cardlistener.onReadResult(cardInfo);
								}
							}
							timer.cancel();
						}

						@Override
						public void onTimeout() throws RemoteException {
							flag = 1;
							cardlistener.onError("XX", "读卡超时");
							timer.cancel();
						}

						@Override
						public void onError(int error, String message)
								throws RemoteException {
							flag = 1;
							cardlistener.onError(""+error, "读卡失败:"+message);
							timer.cancel();
						}
						
					});
				} catch (Exception e) {
					try {
						flag = 1;
						cardlistener.onError("XX", e.getLocalizedMessage());
						timer.cancel();
					} catch (RemoteException e1) {}
				}		
			}
		}).start();
	}
	//取消寻卡
	public void cancel() {
		try {
			flag = 0;
			iMagCardReader.stopSearch();
			timer.cancel();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
