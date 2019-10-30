package com.sssoft.drivers.pos.bank.ccb.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.RemoteException;

import com.ccb.deviceservice.aidl.printer.IPrinter;
import com.ccb.deviceservice.aidl.printer.OnPrintListener;
import com.sssoft.drivers.pos.service.BankService;

/**
 * 针对无打印机终端需使用外接打印机，如蓝牙打印机等。该示例针对内置打印机。
 */
@SuppressLint("NewApi")
public class CcbPrinterImpl{
    private IPrinter iPrinter;
    private HashMap<String, Integer> alginMap = new HashMap<String, Integer>();
	private HashMap<String, Integer> fontMap = new HashMap<String, Integer>();
	private HashMap<Integer, String> errMap = new HashMap<Integer, String>();
    public CcbPrinterImpl() {
    	try {
			iPrinter = IPrinter.Stub.asInterface(BankService.getDeviceService().getPrinter());
			iPrinter.setGray(8);
		} catch (RemoteException e) {
		}
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
  		String align = format.getString("align","left");
  		int height = format.getInt("height",64);
  		if(align.equals("left")){
  			formateTmp.putInt("offset", 10);
  		}else if(align.equals("center")){
  			formateTmp.putInt("offset", 100);
  		}else{
  			formateTmp.putInt("offset", 160);
  		}
  		format.putInt("width", 296);
  		formateTmp.putInt("height", height);
  		iPrinter.addBarCode(formateTmp, barCode);
  	}

  	//添加二维码
  	public void addQrCode(Bundle format, String qrCode) throws RemoteException{
  		Bundle formatTmp = new Bundle();
  		String align = format.getString("align","left");
  		int height = format.getInt("height",200);
  		if(align.equals("left")){
  			formatTmp.putInt("offset", 10);
  		}else if(align.equals("center")){
  			formatTmp.putInt("offset", 100);
  		}else{
  			formatTmp.putInt("offset", 160);
  		}
  		formatTmp.putInt("expectedHeight", height);
  		iPrinter.addQrCode(formatTmp, qrCode);
  	}

  	//走纸
  	public void paperSkip(int line) throws RemoteException {
  		iPrinter.feedLine(line);
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
		iPrinter.startPrint(new OnPrintListener.Stub() {
			@Override
			public void onFinish() throws RemoteException {
				printerLis.onFinish();
			}
			
			@Override
			public void onError(int error) throws RemoteException {
				printerLis.onError(error+"", "打印错误,错误码:" + errMap.get(error));
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
