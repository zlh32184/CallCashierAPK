package com.sssoft.drivers.pos.impl.newland.n9;


import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import android.os.RemoteException;

import com.newland.mtype.ModuleType;
import com.newland.mtype.module.common.rfcard.K21RFCardModule;
import com.newland.mtype.module.common.rfcard.RFCardType;
import com.newland.mtype.module.common.rfcard.RFKeyMode;
import com.newland.mtype.module.common.rfcard.RFResult;
import com.newland.mtype.util.ISOUtils;
import com.sssoft.common.util.SSDes;
import com.sssoft.drivers.pos.aidl.RfM1CardListener;
import com.sssoft.drivers.pos.service.AIDLService;

public class RfM1CardSample{
	private RfM1CardListener cardlistener;
	private int flag = 0;
	private int mTimeout;
	private Timer timer;
	private K21RFCardModule rfCardModule;
	private String mb0Auk;
	private String mb4Auk;
	
	public RfM1CardSample(){
		rfCardModule = (K21RFCardModule) AIDLService.getDevice().getStandardModule(ModuleType.COMMON_RFCARDREADER);
	}
	
	//磁条卡寻卡
	public void searchCard(int timeout,String b0auk,String b4auk,RfM1CardListener rfCardlistener) {
		cardlistener = rfCardlistener;
		mTimeout = timeout;
		mb0Auk = b0auk;
		mb4Auk = b4auk;
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
			        int count = 0 ;
			        String snrNo = "";
			        for (int i = 0; i < mTimeout; i++) {
			        	try {
			        		List<RFCardType> cardTypeList=new ArrayList<RFCardType>();
			                cardTypeList.add(RFCardType.M1CARD);
			                RFResult qPResult = rfCardModule.powerOn(cardTypeList.toArray(new RFCardType[cardTypeList.size()]),1, TimeUnit.SECONDS);
					        if(qPResult.getCardSerialNo()!=null){
					        	snrNo = ISOUtils.hexString(qPResult.getCardSerialNo());
					        	break;
					        }
			        	} catch (Exception e) {
			        		count ++;
							continue;
						}
					}
			        if(count==mTimeout){
			        	flag = 1;
						cardlistener.onError("XX", "读卡超时");
						timer.cancel();
			        }else{
			        	//外部认证0块
			        	byte snr[] = ISOUtils.hex2byte(snrNo);
						byte key[] = ISOUtils.hex2byte(mb0Auk);
			        	rfCardModule.authenticateByExtendKey(RFKeyMode.KEYA_0X60, snr, 0, key);
			        	//读1块
			        	byte output[] = rfCardModule.readDataBlock(1);
			        	String last16Card = convertHexToString(ISOUtils.hexString(output));
			        	//计算4块秘钥
			        	String[] miyao = ssGetKeys(last16Card, mb4Auk);
			        	//外部认证4块
			        	byte snr4[] = ISOUtils.hex2byte(snrNo);
						byte key4[] = ISOUtils.hex2byte(miyao[0]);
			        	rfCardModule.authenticateByExtendKey(RFKeyMode.KEYA_0X60, snr4, 4, key4);
			        	//读4,5,6块
			        	byte output4[] = rfCardModule.readDataBlock(4);
			        	byte output5[] = rfCardModule.readDataBlock(5);
			        	byte output6[] = rfCardModule.readDataBlock(6);
			        	String cardInfo = convertHexToString(ISOUtils.hexString(output4)+ISOUtils.hexString(output5)+ISOUtils.hexString(output6));
			        	Integer length = new Integer(cardInfo.substring(0, 2));
			        	String trk2 = cardInfo.substring(2, 2+length);
			        	String[] pans = trk2.split("=");
			        	String[] cardInfos = {pans[0],trk2}; 
			        	flag = 1;
						try {
							cardlistener.onReadResult(cardInfos);
						} catch (RemoteException e) {
						}	
						timer.cancel();
			        }
			        
			        
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
	public static String convertHexToString(String hex){
		 
		  StringBuilder sb = new StringBuilder();
		  StringBuilder temp = new StringBuilder();
	 
		  //49204c6f7665204a617661 split into two characters 49, 20, 4c...
		  for( int i=0; i<hex.length()-1; i+=2 ){
	 
		      //grab the hex in pairs
		      String output = hex.substring(i, (i + 2));
		      //convert hex to decimal
		      int decimal = Integer.parseInt(output, 16);
		      //convert the decimal to character
		      sb.append((char)decimal);
	 
		      temp.append(decimal);
		  }
		 
		  return sb.toString();
	}
	public static String[]  ssGetKeys(String pan, String key) {
		String str = "";
		String[] Keys = new String[2];
		try {
			str = SSDes.encHexStr3(pan, key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Keys[0] = str.substring(0, 12);
		Keys[1] = str.substring(4, 16);
		return Keys;
	}
	//取消寻卡
	public void cancel() {
		flag = 0;
		rfCardModule.powerOff(60);
		timer.cancel();
	}
}
