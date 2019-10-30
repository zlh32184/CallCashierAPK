package com.sssoft.drivers.pos.impl.hisense.hi98;


import android.util.Log;

import com.hisense.pos.magic.MagCard;
import com.hisense.pos.magic.TrackResult;
import com.sssoft.drivers.pos.aidl.AllCardListener;
import com.sssoft.drivers.pos.aidl.CardListener;

public class Hi98CardSample{
	private  boolean cardStatus;
	private boolean cancelStatus = false;
	private MagCard magCardReader;
	private CardListener allCardlistener;
	private int timeout;
	private testThread tth;

	public Hi98CardSample(){
		Log.i("Hi98CardSample","new magcard");
		magCardReader = new MagCard();
	}
	
	//磁条卡寻卡
	public void searchCard(int timeout,CardListener allCardlistener) {
		Log.i("Hi98CardSample","new serch");
		this.allCardlistener = allCardlistener;
		this.timeout = timeout; 
		tth = new testThread();
		tth.start();
	}
		
		
	private class testThread extends Thread{
			@Override
		public void run() {
		try {
			int nret = magCardReader.Mag_Open();
			if(nret != MagCard.MAG_OK){
				allCardlistener.onError("X1", "磁卡设备打开失败!");
				Log.i("dj","X1磁卡设备打开失败!");
				return;
			}
			// 开始寻卡
			TrackResult result = new TrackResult();
			byte[] readMsr1 = null,readMsr2 = null,readMsr3 = null;
			String[] readCard = new String[3];
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
								readCard[0] = new String(readMsr3);
								allCardlistener.onReadResult(readCard);
								cardStatus = true;
							}else{
								readCard[0] = new String(new String(readMsr2));
								allCardlistener.onReadResult(readCard);
								cardStatus = true;
							}
						}else{
							readCard[0] = new String(new String(readMsr1));
							allCardlistener.onReadResult(readCard);
							cardStatus = true;
						}
					}else{
						allCardlistener.onError("X2", "一磁信息错误");
						Log.i("dj","X2磁卡设备打开失败!");
						return;
					}
					
				}else if(rc == MagCard.CARD_ERR_NO_CARD ){
					Log.i("test","no card");
				}else{
					allCardlistener.onError("X2", "读磁道信息错误!");
					Log.i("dj","X21磁卡设备打开失败!");
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
