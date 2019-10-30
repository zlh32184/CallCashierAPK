package com.sssoft.drivers.pos.impl.aisino.A90;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.RemoteException;

import com.icbc.smartpos.deviceservice.aidl.IPrinter;
import com.icbc.smartpos.deviceservice.aidl.PrinterListener;
import com.sssoft.drivers.pos.service.AIDLService;

@SuppressLint("NewApi")
public class APrinterSample {
	private IPrinter iPrinter;
	private String err = "";
	private HashMap<String, Integer> alginMap = new HashMap<String, Integer>();
	private HashMap<String, Integer> fontMap = new HashMap<String, Integer>();
	private HashMap<Integer, String> errMap = new HashMap<Integer, String>();
	public APrinterSample() throws RemoteException{
		iPrinter = IPrinter.Stub.asInterface(AIDLService.getDeviceServiceA90().getPrinter());
		fontMap.put("small", 0);
		fontMap.put("normal", 1);
		fontMap.put("large", 2);
		alginMap.put("left", 0);
		alginMap.put("center", 1);
		alginMap.put("right", 2);
		errMap.put(240, "缺纸");
		errMap.put(243, "打印机过热");
		errMap.put(247, "打印机忙");
	}

	//添加文本
	public void addText(Bundle format, String text) throws RemoteException{
		iPrinter.addText(setFormat(format), text);
	}
	
	//添加图片
	public void addPicture(Bundle format, Bitmap bitmap) throws RemoteException{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();  
		bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);  
		byte[] datas = baos.toByteArray();
		iPrinter.addImage(setFormat(format), datas);
	}
	
	//添加条码
	public void addBarCode(Bundle format, String barCode) throws RemoteException{
		Bundle formateTmp = setFormat(format);
		formateTmp.putInt("height",64);
		formateTmp.putInt("width",300);
		iPrinter.addBarCode(formateTmp, barCode);
	}

	//添加二维码
	public void addQrCode(Bundle format, String qrCode) throws RemoteException{
		Bundle formateTmp = setFormat(format);
		formateTmp.putInt("height",100);
		iPrinter.addQrCode(formateTmp, qrCode);
	}

	//走纸
	public void paperSkip(int line) throws RemoteException {
		iPrinter.feedLine(line+1);
	}

	//获取状态
	public String getStatus() throws RemoteException {
		String rc = "";
		int status = iPrinter.getStatus();
		if(status ==0 ){
			rc = "00";
		}else if(status ==240){
			rc = "02";
		}else if(status ==243){
			rc = "03";
		}else if(status ==247){
			rc = "04";
		}else{
			rc = "05";
		}
		return rc;
	}
	
	//开始打印
	public void startPrinter(final com.sssoft.drivers.pos.aidl.PrinterListener printerLis) throws RemoteException {
		iPrinter.startPrint(new PrinterListener.Stub() {
			@Override
	        public void onError(int error) throws RemoteException
	        {
				printerLis.onError(error+"", "打印错误,错误码:" + errMap.get(error));
	        }

	        @Override
	        public void onFinish() throws RemoteException
	        {
	        	printerLis.onFinish();
	        }
		});
	}
	public Bundle setFormat(Bundle format){
		String align = format.getString("align","left");
		String font = format.getString("font","normal");
		Bundle formatTmp = new Bundle();
		formatTmp.putInt("align", alginMap.get(align));
		formatTmp.putInt("font", fontMap.get(font));
		return formatTmp;
	}
}