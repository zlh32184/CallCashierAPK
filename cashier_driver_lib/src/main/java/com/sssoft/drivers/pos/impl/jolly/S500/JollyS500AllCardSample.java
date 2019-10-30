package com.sssoft.drivers.pos.impl.jolly.S500;


import android.os.Message;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;

import com.hisense.pos.ic.IcCard;
import com.hisense.pos.magic.MagCard;
import com.hisense.pos.magic.TrackResult;
import com.hisense.pos.picc.PiccCard;
import com.hisense.pos.util.BytesArray;
import com.hisense.pos.util.BytesUtil;
import com.jl.Ddi;
import com.sssoft.common.util.ConvertUtil;
import com.sssoft.common.util.SSDes;
import com.sssoft.drivers.pos.aidl.AllCardListener;
import com.sssoft.drivers.pos.util.Base16EnDecoder;

public class JollyS500AllCardSample{
	private String cardTrk;
	private String M1Key = "3131313131313131";
	private boolean cardStatus = false;
	private boolean cancelStatus = false;
	private MagCard magCardReader;
	private IcCard icCardReader;
	private PiccCard piccCardReader;
	private Ddi allCardModule;
	private byte[] keyA_0 = {(byte)0x31, (byte)0x31, (byte)0x31, (byte)0x31, (byte)0x31, (byte)0x31};
	public JollyS500AllCardSample(Ddi ddi){
		allCardModule = ddi;
	}
	
	private void magCardSearch(AllCardListener allCardlistener) throws RemoteException{
		//读取数据前清除之前数据
		TrackResult result = new TrackResult();
		byte[] readMsr1 = null,readMsr2 = null,readMsr3 = null;
		readMsr1 = null;readMsr2 = null;readMsr3 = null;
		int rc = magCardReader.Mag_Read(result);
		if( rc == 0 ){
				if(result.isTrackValid(2)){
					readMsr2 = result.getTrackData(2);
					this.cardTrk = new String(readMsr2);
					allCardlistener.onReadResult(new String(readMsr2));
					cardStatus = true;
					
				}else if(result.isTrackValid(3)){
					readMsr3 = result.getTrackData(3);
					this.cardTrk = new String(readMsr3);
					allCardlistener.onReadResult(new String(readMsr3));
					cardStatus = true;
				}else{
					allCardlistener.onError("X2", "Read card error");
					cancelStatus = true;
				}
			
		}else if(rc == MagCard.CARD_ERR_NO_CARD ){
			return;
		}else{
			allCardlistener.onError("X2", "Read card error");
			cancelStatus = true;
		}
		return;
	}
	
	private void icCardSearch(AllCardListener allCardlistener) throws RemoteException{
		//读取数据前清除之前数据
		int rc = icCardReader.Icc_Detect((byte)0, (byte) 0);
		Log.i("read", String.valueOf(rc));
		if( rc == IcCard.ICC_OK ){
			byte[]ATR = new byte[200];
			int ret = icCardReader.Icc_Init((byte)0, (byte) 0, ATR);
			if(ret != IcCard.ICC_OK){
				Log.i("test",String.valueOf(ret));
				allCardlistener.onError("X2", "Ic_Init Error, Retry");
				cancelStatus = true;
			}else{
				allCardlistener.onReadResult("Icc_Init OK");
				cardStatus = true;
			}
		}else if(rc == IcCard.ICC_FAIL_REMOVE ){
			Log.i("test","no card");
		}else{
			allCardlistener.onError("X2", "IC Card Detect Error");
			cancelStatus = true;
		}
		return;
	}
	
	private void piccCardSearch(AllCardListener allCardlistener) throws Exception{
		long starttime = System.currentTimeMillis() ;
		byte[] cardType = new byte[8];
		byte[] SerialInfo = new byte[128];
		byte[] CID = new byte[128];
		byte[] Other = new byte[128];
		int rc = piccCardReader.piccDetect((byte)0x00, cardType,
				 SerialInfo, CID, Other);
		if (rc == PiccCard.PICC_FAIL_FOUNDCARD) 
			rc = piccCardReader.piccDetect((byte)'m', cardType, SerialInfo, CID, Other);
		
		//时间过短放弃
		if (rc != PiccCard.PICC_OK)
		{
			long endTime = System.currentTimeMillis();
			//Log.i("time",String.valueOf(endTime)+"|"+String.valueOf(starttime));
			if(endTime-starttime<500)
			{
				Log.i("picc read","time");
				return;
			}
		}
		
		//detect成功,读卡
		if (rc == PiccCard.PICC_OK)
		{
			Log.i("dj","xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
			//第234位为1为M1卡
			if( (cardType[0]&(byte)0x70) != (byte)0x00 ){
				Log.i("dj",String.valueOf(cardType[0]^(byte)0x70));
				Log.i("dj","111111111111111111111111111111111111111111111111111");
				rc = readM1Card(piccCardReader, SerialInfo);
			}
			else
				rc = piccCardRead();
		}
		
		//读卡成功
		if( rc == PiccCard.PICC_OK ){
			//验证失败
			if( cancelStatus && !cardStatus)
				allCardlistener.onReadResult("M1 card vertify failed");
			else
				allCardlistener.onReadResult(cardTrk);
		}else if( rc == PiccCard.PICC_FAIL_FOUNDCARD ){
			return;
		}else{
//			可能是读取或刷卡不稳定
//			allCardlistener.onError("X2", "Card_Init Error");
//			cancelStatus = true;
		}
		return;
	}
	
	private int piccCardRead(){
		Log.i("dj","xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		this.cardTrk = "get card num";
		//allCardlistener.onReadResult(cardStr.substring(2, 2+cardLen));
		cardStatus = true;
		return 0;
	}
	private int readM1Card(PiccCard piccCardReader, byte[]SerialInfo) throws Exception{

		byte[] keyA = {(byte)0x31, (byte)0x31, (byte)0x31, (byte)0x31, (byte)0x31, (byte)0x31};
		byte keyFlag = 0x00;
		SerialInfo = BytesUtil.subBytes(SerialInfo, 1, 0xff & SerialInfo[0]);
		byte blockAddr = 0x03;
		int rc = piccCardReader.mfAuthBlock(keyFlag, blockAddr, keyA, SerialInfo);
		if( rc == PiccCard.PICC_OK ){
			BytesArray blockdata2=new BytesArray();
			BytesArray blockdata1=new BytesArray();
			blockAddr = 0x02;
			rc = piccCardReader.mfReadBlock(blockAddr, blockdata2);
			if( rc == PiccCard.PICC_OK ){
				blockAddr = 0x01;
				rc = piccCardReader.mfReadBlock(blockAddr, blockdata1);
			}
			
			if( rc == PiccCard.PICC_OK ){
				String encStr= SSDes.encHexStr("0000000000000000",this.M1Key);
				if(new String(blockdata2.getData()).equals(encStr)){
					String keyStr = SSDes.encHexStr(new String(blockdata1.getData()), this.M1Key);
					String keyA1 = keyStr.substring(0, 12);
					//1扇区读写
					rc = readM1Section1( piccCardReader,SerialInfo, keyA1 );
				}else{
					cancelStatus = true;
				}
			}
		}
		return rc;
	}
	
	public int readM1Section1(PiccCard piccCardReader, 
				byte[]SerialInfo, String keyA1) throws Exception{
		byte keyFlag = 0x00;
		byte blockAddr = 0x07;
		int rc = piccCardReader.mfAuthBlock(keyFlag, blockAddr, ConvertUtil.hex2AscBytes(keyA1), SerialInfo);
		if( rc == PiccCard.PICC_OK ){
			BytesArray blockdata4=new BytesArray();
			BytesArray blockdata5=new BytesArray();
			BytesArray blockdata6=new BytesArray();
			
			blockAddr = 0x04;
			rc = piccCardReader.mfReadBlock(blockAddr, blockdata4);
			if( rc == PiccCard.PICC_OK ){
				blockAddr = 0x05;
				rc = piccCardReader.mfReadBlock(blockAddr, blockdata5);
				if( rc == PiccCard.PICC_OK ){
					blockAddr = 0x06;
					rc = piccCardReader.mfReadBlock(blockAddr, blockdata6);
					
				}
			}
			
			if(rc == PiccCard.PICC_OK){
				String cardStr = ConvertUtil.hex2AscStr(blockdata4.toHexString()
						+ blockdata5.toHexString() + blockdata6.toHexString(), "GBK");
				int cardLen = Integer.parseInt(cardStr.substring(0, 2));
				this.cardTrk = cardStr.substring(2, 2+cardLen);
				//allCardlistener.onReadResult(cardStr.substring(2, 2+cardLen));
				cardStatus = true;
			}
		}
		return rc;
	}
	
	private void m1CardSearch(AllCardListener allCardlistener) throws RemoteException{
		int ret = allCardModule.ddi_rf_get_status();
        if (ret >= 5) {
        	cardStatus = true;
        	byte[] uid = new byte[128];
        	ret = allCardModule.ddi_rf_getUid(uid);
        	if (ret != 0) {
        		allCardlistener.onError("X1", "取M1卡UID 失败");
        		cancelStatus = true;
                return;
            }
            byte[] ruid = new byte[uid[0]];
            System.arraycopy(uid, 1, ruid, 0, uid[0]);
            
            //认证0扇区块3 keyA
            int block = 3;
            int authMode = 0x60;
            ret = allCardModule.ddi_m1_auth(block, authMode, keyA_0, ruid);
            if (ret != 0)
            {
            	allCardlistener.onError("X1", "卡片keyA0密钥验证 失败");
            	cancelStatus = true;
                return;
            }
            
            //读取块1、2内容
            byte[] data1 = new byte[16];
            byte[] data2 = new byte[16];
            readM1Block(1, allCardlistener, data1);
            readM1Block(2, allCardlistener, data2);
            if( cancelStatus )
            	return;
            
            //Auk校验，获得1扇区7块 keyA
            byte[] keyA_1 = null;
            try {
                String encStr = SSDes.encHexStr("0000000000000000", M1Key);
				if(new String(data2,"utf-8").equals(encStr)){
					String keyStr = SSDes.encHexStr(new String(data1, "utf-8"), M1Key);
					String keyAStr = keyStr.substring(0, 12);
					keyA_1 = Base16EnDecoder.Decode(keyAStr);
				}else{
					allCardlistener.onError("X1", "卡片AUK密钥验证 失败");
					cancelStatus = true;
	                return;
				}
            }catch (Exception e) {
				e.printStackTrace();
				allCardlistener.onError("X1", "密钥转换异常");
				cancelStatus = true;
                return;
			}
			
            if(keyA_1 != null){
            	block = 7;
            	ret = allCardModule.ddi_m1_auth(block, authMode, keyA_1, ruid);
            	if (ret != 0)
            	{
            		allCardlistener.onError("X1", "卡片keyA1密钥验证 失败");
            		cancelStatus = true;
            		return;
            	}
            	
            	//读取块4、5、6内容
                byte[] data4 = new byte[16];
                byte[] data5 = new byte[16];
                byte[] data6 = new byte[16];
                readM1Block(4, allCardlistener, data4);
                readM1Block(5, allCardlistener, data5);
                readM1Block(6, allCardlistener, data6);
                if( cancelStatus )
                	return;
                
                try {
	                String cardStr = new String(data4,"utf-8") + new String(data5,"utf-8")
	                	+ new String(data6,"utf-8");
					int cardLen = Integer.parseInt(cardStr.substring(0, 2));
					this.cardTrk = cardStr.substring(2, 2+cardLen);
					allCardlistener.onReadResult(this.cardTrk);
					cardStatus = true;
					return;
                }catch (Exception e) {
    				e.printStackTrace();
    				allCardlistener.onError("X1", "获取卡号异常");
    				cancelStatus = true;
                    return;
    			}
            }
        }
	}
	
	private boolean readM1Block(int block, AllCardListener allCardlistener, byte[] data) throws RemoteException{
	    byte[] odata = new byte[17];
        int ret = allCardModule.ddi_m1_readRaw(block, odata);
        if (ret != 0)
        {
        	allCardlistener.onError("X1", "读取块"+block+"失败");
        	cancelStatus = true;
            return false;
        }else{
        	System.arraycopy(odata, 1, data, 0, 16);
        	return true;
        }
	}
	//磁条卡寻卡
	public void searchCard(int timeout,AllCardListener allCardlistener) throws RemoteException {
		
		int ret = allCardModule.ddi_rf_open();
		if (ret != 0) {
			allCardlistener.onError("X1", "打开RFID模块失败");
			return;
		}
		
		ret = allCardModule.ddi_rf_poweron(7);
		if (ret != 0) {
			allCardModule.ddi_rf_close();
			allCardlistener.onError("X1", "上电（0xFF）RFID模块失败");
			return;
        }
		
		cardStatus = false;
		cancelStatus = false;

			// 刷卡完成后立即停止读卡，未刷卡等待超时时间,读卡错误立即停止读卡			
			for (int i = 0; i < timeout*5; i++){
				if (cardStatus == true || cancelStatus == true)
					break;
				m1CardSearch(allCardlistener);
				SystemClock.sleep(200);
				//Thread.sleep(200);
			}

			if( cancelStatus == false && cardStatus == false )
				allCardlistener.onError("X0", "读卡超时");
			allCardModule.ddi_rf_poweroff();
			allCardModule.ddi_rf_close();

	}
		
	//取消寻卡
	public void cancel() {
		cancelStatus = true;
	}
	
	public void setM1Key(String key){
		this.M1Key = key;
	}
}
