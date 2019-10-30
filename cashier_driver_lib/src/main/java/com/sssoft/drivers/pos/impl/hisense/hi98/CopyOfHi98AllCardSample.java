package com.sssoft.drivers.pos.impl.hisense.hi98;


import android.util.Log;

import com.hisense.pos.ic.IcCard;
import com.hisense.pos.magic.MagCard;
import com.hisense.pos.magic.TrackResult;
import com.sssoft.drivers.pos.aidl.AllCardListener;

public class CopyOfHi98AllCardSample{
	private  boolean cardStatus;
	private boolean cancelStatus = false;
	private MagCard magCardReader;
	private IcCard icCardReader;
	private AllCardListener allCardlistener;
	private int timeout;

	public CopyOfHi98AllCardSample(){
		Log.i("dj","new magcard");
		magCardReader = new MagCard();
	}
	
//	private void magCardSearch(){
//		TrackResult result = new TrackResult();
//		byte[] readMsr1 = null,readMsr2 = null,readMsr3 = null;
//	}
	//磁条卡寻卡
	public void searchCard(int timeout,AllCardListener allCardlistener) {
		this.allCardlistener = allCardlistener;
		this.timeout = timeout;
		
		try {
			int nret = magCardReader.Mag_Open();
			if(nret != MagCard.MAG_OK){
				allCardlistener.onError("X1", "磁卡设备打开失败!");
				return;
			}
			// 开始寻卡
			TrackResult result = new TrackResult();
			byte[] readMsr1 = null,readMsr2 = null,readMsr3 = null;
			// 刷卡完成后立即停止读卡，未刷卡等待超时时间,读卡错误立即停止读卡			
			for (int i = 0; i < timeout; i++){
				if (cardStatus == true)
					break;
				else if(cancelStatus == true){
					magCardReader.Mag_Close();
					return;
				}	
				//读取数据前清除之前数据
				readMsr1 = null;readMsr2 = null;readMsr3 = null;
				int rc = magCardReader.Mag_Read(result);
				Log.i("read", String.valueOf(rc));
				if( rc == 0 ){
					if(result.isTrackValid(1)){
						readMsr1 = result.getTrackData(1);
						Log.i( "MSR1: " , new String(readMsr1));
						if(result.isTrackValid(2)){
							readMsr2 = result.getTrackData(2);
							Log.i( "MSR2: " , new String(readMsr2));		
							if(result.isTrackValid(3)){
								readMsr3 = result.getTrackData(3);
								Log.i( "MSR3: " , new String(readMsr3));	
								allCardlistener.onReadResult(new String(readMsr3));
								cardStatus = true;
							}else{
								allCardlistener.onReadResult(new String(readMsr2));
								cardStatus = true;
							}
						}else{
							allCardlistener.onReadResult(new String(readMsr1));
							cardStatus = true;
						}
					}else{
						allCardlistener.onError("X2", "一磁信息错误");
						return;
					}
					
				}else if(rc == MagCard.CARD_ERR_NO_CARD ){
					Log.i("test","no card");
				}else{
					allCardlistener.onError("X2", "读磁道信息错误!");
					return;
				}
				Thread.sleep(1000);
			}

			// 结束寻卡
			magCardReader.Mag_Close();
		} catch (Exception e) {
			e.printStackTrace();
			magCardReader.Mag_Close();
		}
	}
		
		
	private class testThread extends Thread{
			@Override
		public void run() {
		try {
			int nret = magCardReader.Mag_Open();
			if(nret != MagCard.MAG_OK){
				allCardlistener.onError("X1", "磁卡设备打开失败!");
				return;
			}
			// 开始寻卡
			TrackResult result = new TrackResult();
			byte[] readMsr1 = null,readMsr2 = null,readMsr3 = null;
			// 刷卡完成后立即停止读卡，未刷卡等待超时时间,读卡错误立即停止读卡			
			for (int i = 0; i < timeout; i++){
				if (cardStatus == true)
					break;
				else if(cancelStatus == true){
					magCardReader.Mag_Close();
					return;
				}	
				//读取数据前清除之前数据
				readMsr1 = null;readMsr2 = null;readMsr3 = null;
				int rc = magCardReader.Mag_Read(result);
				Log.i("read", String.valueOf(rc));
				if( rc == 0 ){
					if(result.isTrackValid(1)){
						readMsr1 = result.getTrackData(1);
						Log.i( "MSR1: " , new String(readMsr1));
						if(result.isTrackValid(2)){
							readMsr2 = result.getTrackData(2);
							Log.i( "MSR2: " , new String(readMsr2));		
							if(result.isTrackValid(3)){
								readMsr3 = result.getTrackData(3);
								Log.i( "MSR3: " , new String(readMsr3));	
								allCardlistener.onReadResult(new String(readMsr3));
								cardStatus = true;
							}else{
								allCardlistener.onReadResult(new String(readMsr2));
								cardStatus = true;
							}
						}else{
							allCardlistener.onReadResult(new String(readMsr1));
							cardStatus = true;
						}
					}else{
						allCardlistener.onError("X2", "一磁信息错误");
						return;
					}
					
				}else if(rc == MagCard.CARD_ERR_NO_CARD ){
					Log.i("test","no card");
				}else{
					allCardlistener.onError("X2", "读磁道信息错误!");
					return;
				}
				Thread.sleep(1000);
			}

			// 结束寻卡
			magCardReader.Mag_Close();
		} catch (Exception e) {
			e.printStackTrace();
			magCardReader.Mag_Close();
		}
	}
	}
	
	//取消寻卡
	public void cancel() {
		cancelStatus = true;
	}
}
