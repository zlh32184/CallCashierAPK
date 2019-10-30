package com.sssoft.drivers.pos.bank.boc.impl;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.boc.aidl.printer.AidlPrinter;
import com.boc.aidl.printer.AidlPrinterListener;
import com.sssoft.drivers.pos.aidl.PrinterListener;
import com.sssoft.drivers.pos.service.AIDLService;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;

/**
 * 针对无打印机终端需使用外接打印机，如蓝牙打印机等。该示例针对内置打印机。
 */
@SuppressLint("NewApi")
public class BocPrinterImpl {
    private AidlPrinter iPrinter;
    private PrinterListener printerListener;
    private HashMap<String, Integer> alginMap = new HashMap<String, Integer>();
	private HashMap<String, Integer> fontMap = new HashMap<String, Integer>();
	private HashMap<Integer, String> errMap = new HashMap<Integer, String>();
    public BocPrinterImpl() {
    	try {
			iPrinter = AidlPrinter.Stub.asInterface(AIDLService.getBocDeviceService().getPrinter());
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
  		iPrinter.print(genPrintJsonMsg(text),null, new AidlPrinterListener.Stub(){

			@Override
			public void onError(int i, String s) throws RemoteException {
				printerListener.onError(i+"",s);
			}

			@Override
			public void onFinish() throws RemoteException {
				printerListener.onFinish();
			}
		});
  	}
  	
  	//添加图片
  	public void addPicture(Bundle format, Bitmap bitmap) throws RemoteException{
  		ByteArrayOutputStream baos = new ByteArrayOutputStream();  
  		bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);  
  		//byte[] datas = baos.toByteArray();
		iPrinter.printBitMap(0, bitmap, 100, new AidlPrinterListener.Stub(){

			@Override
			public void onError(int i, String s) throws RemoteException {
				printerListener.onError(i+"",s);
			}

			@Override
			public void onFinish() throws RemoteException {
				printerListener.onFinish();
			}
		});
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
  		//iPrinter.addBarCode(formateTmp, barCode);
		iPrinter.printBarCode( barCode, 300,100,0, 100, new AidlPrinterListener.Stub(){

			@Override
			public void onError(int i, String s) throws RemoteException {
				printerListener.onError(i+"",s);
			}

			@Override
			public void onFinish() throws RemoteException {
				printerListener.onFinish();
			}
		});
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
  		//iPrinter.addQrCode(formatTmp, qrCode);
		iPrinter.printQrCode(qrCode,300 ,0,0, 100, new AidlPrinterListener.Stub(){

			@Override
			public void onError(int i, String s) throws RemoteException {
				printerListener.onError(i+"",s);
			}

			@Override
			public void onFinish() throws RemoteException {
				printerListener.onFinish();
			}
		});
  	}

  	//走纸
  	public void paperSkip(int line) throws RemoteException {
  		iPrinter.paperSkip(line);
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
  	public void startPrinter(final PrinterListener printerLis) throws RemoteException {
    	printerListener = printerLis;
  	}
  	public Bundle setFormat(Bundle format){
  		String align = format.getString("align","left");
  		String font = format.getString("font","normal");
  		Bundle formatTmp = new Bundle();
  		formatTmp.putInt("align", alginMap.get(align));
  		formatTmp.putInt("font", fontMap.get(font));
  		return formatTmp;
  	}

  	private String getErrMsg(int i)
	{
		String errMsg = "打印错误";
		switch (i) {
		case 0x00:
			errMsg = "没有错误，一切正常";
			break;
		case 0x01:
			errMsg = "打印数据解析错误";
			break;
		case 0x02:
			errMsg = "缺纸";
			break;
		case 0x03:
			errMsg = "超温";
			break;
		case 0x04:
			errMsg = "打印机繁忙";
			break;
		case 0x05:
			errMsg = "硬件错误";
			break;
		case 0x06:
			errMsg = "卡纸";
			break;
		case 0xFF:
			errMsg = "其他错误";
			break;
		}

		return errMsg;
	}

	private static int getSize(String sizeStr) {
		int sizeInt = 1;
		switch (sizeStr) {
			case "small":
				sizeInt = 1;
				break;
			case "normal":
				sizeInt = 2;
				break;
			case "large":
				sizeInt = 3;
				break;
			default:
				sizeInt = 2;
				break;
		}
		return sizeInt;
	}

	private static String getPosion(String pos) {
		String posion = "left";
		switch (pos) {
			case "left":
				posion = "left";
				break;
			case "center":
				posion = "center";
				break;
			case "right":
				posion = "right";
				break;
			default:
				posion = "left";
				break;
		}
		return posion;
	}


	public static String formateText(String text,Integer length,Integer length2){
		if(text == null || text.length()<length*2+length2 || length==0){
			return text==null?"":text;
		}else{
			return text.substring(length, text.length()-length-length2);
		}
	}

	public static String genPrintJsonMsg(String textAll) throws RemoteException {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("{\"spos\":[");
		Log.e("doPrintJsonMsg", "textAll=" + textAll);
		String[] prints = textAll.replace("\n", "").replaceAll("\t", "").replaceAll("\r", "").split("<br>");
		for (int i = 0; i < prints.length; i++) {
			String text = prints[i];
			int length = 0;
			int length2 = 0;
			Bundle formatNL = new Bundle();
			if (text.contains("<center>")) {
				formatNL.putString("align", "center");
				length = length + 8;
				length2 = length2 + 1;
			} else if (text.contains("<left>")) {
				formatNL.putString("align", "left");
				length = length + 6;
				length2 = length2 + 1;
			} else if (text.contains("<right>")) {
				formatNL.putString("align", "right");
				length = length + 7;
				length2 = length2 + 1;
			} else {
				formatNL.putString("align", "left");
			}
			if (text.contains("<normal>")) {
				formatNL.putString("font", "normal");
				length = length + 8;
				length2 = length2 + 1;
			} else if (text.contains("<small>")) {
				formatNL.putString("font", "small");
				length = length + 7;
				length2 = length2 + 1;
			} else if (text.contains("<large>")) {
				formatNL.putString("font", "large");
				length = length + 7;
				length2 = length2 + 1;
			} else {
				formatNL.putString("font", "normal");
			}
			if (text.contains("<barCode>")) {//条形码
				length = length + 9;
				length2 = length2 + 1;
				text = formateText(text, length, length2);
				/*"content-type":"one-dimension",
				  "size":"2",
				  "height":"50",
				  "content":"891110847565611252"*/
				JSONObject jsonE = new JSONObject();
				jsonE.put("content-type", "one-dimension");
				//jsonE.put("size", getSize(formatNL.getString("font")));
				jsonE.put("content", text);
				jsonE.put("position", getPosion(formatNL.getString("align")));
				//jsonE.put("offset",0);
				//jsonE.put("bold",0);
				jsonE.put("height", 50);
//				Log.e("doPrintJsonMsg", "jsonBarCode.text=" + jsonE.toString());
				stringBuilder.append(jsonE.toString() + ",");

			} else if (text.contains("<qrCode>")) {//二维码
				length = length + 8;
				length2 = length2 + 1;
				text = formateText(text, length, length2);
				/*"content-type":"two-dimension",
				  "size":"150",
				  "content":"891110847565611252"*/

				JSONObject jsonE = new JSONObject();
				jsonE.put("content-type", "two-dimension");
				//jsonE.put("size", getSize(formatNL.getString("font")));
				jsonE.put("content", text);
				jsonE.put("position", getPosion(formatNL.getString("align")));
				//jsonE.put("offset",0);
				//jsonE.put("bold",0);
				//jsonE.put("height", 50);
//				Log.e("doPrintJsonMsg", "jsonQrCode.text=" + jsonE.toString());
				stringBuilder.append(jsonE.toString() + ",");

			} else {
				text = formateText(text, length, length2);
				JSONObject jsonE = new JSONObject();
				jsonE.put("content-type", "txt");
				jsonE.put("size", getSize(formatNL.getString("font")));
				jsonE.put("content", text);
				jsonE.put("position", getPosion(formatNL.getString("align")));
				jsonE.put("offset",0);
				jsonE.put("bold",0);
//				Log.e("doPrintJsonMsg", "jsonE.text=" + jsonE.toString());
				stringBuilder.append(jsonE.toString() + ",");
				//jsonArray.put(jsonE);
			}
		}
		stringBuilder.deleteCharAt(stringBuilder.toString().length()-1);
		stringBuilder.append("]}");
		Log.e("doPrintJsonMsg", "printInfo=" + stringBuilder.toString());
		return stringBuilder.toString();
	}

}
