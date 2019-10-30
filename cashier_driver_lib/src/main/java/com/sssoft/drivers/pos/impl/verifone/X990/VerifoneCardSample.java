package com.sssoft.drivers.pos.impl.verifone.X990;


import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;

import com.njabc.sdk.CardInfo;
import com.njabc.sdk.CardReaderListener;
import com.sssoft.drivers.pos.aidl.AllCardListener;
import com.sssoft.drivers.pos.aidl.CardListener;
import com.sssoft.drivers.pos.service.AIDLService;
import com.zacloud.deviceservice.aidl.IPBOC;
import com.zacloud.deviceservice.aidl.CheckCardListener;
import com.zacloud.deviceservice.aidl.ReadCardListener;

public class VerifoneCardSample{
	private AllCardListener cardlistener;
	private IPBOC ipboc;
	private int mTimeout;
	private int flag = 0;
	private Timer timer;
	public VerifoneCardSample() throws RemoteException{
		ipboc = IPBOC.Stub.asInterface(AIDLService.getDeviceService().getPBOC());
	}
	
	//磁条卡寻卡
	public void searchCard(int timeout,AllCardListener allCardlistener) {
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
					ipboc.readCardNo(mTimeout, new ReadCardListener.Stub() {
						
						@Override
						public void onSuccess(String cardNo) throws RemoteException {
							flag = 1;
							cardlistener.onReadResult(cardNo);
							timer.cancel();
						}
						
						@Override
						public void onError(int errorCode, String message) throws RemoteException {
							flag = 1;
							cardlistener.onError(""+errorCode, message);
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
			ipboc.stopReadCard();
			timer.cancel();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
