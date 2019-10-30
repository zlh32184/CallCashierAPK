package com.sssoft.drivers.pos.impl.newland.n9;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.RemoteException;

import com.newland.mtype.ModuleType;
import com.newland.mtype.module.common.printer.FontSettingScope;
import com.newland.mtype.module.common.printer.FontType;
import com.newland.mtype.module.common.printer.LiteralType;
import com.newland.mtype.module.common.printer.PrintContext;
import com.newland.mtype.module.common.printer.Printer;
import com.newland.mtype.module.common.printer.PrinterResult;
import com.newland.mtype.module.common.printer.PrinterStatus;
import com.newland.mtype.module.common.printer.WordStockType;
import com.sssoft.drivers.pos.aidl.PrinterListener;
import com.sssoft.drivers.pos.service.AIDLService;
import com.sssoft.drivers.pos.util.Arith;

@SuppressLint("NewApi")
public class NPrinterSample {
	private Printer printerModule;
	private PrinterResult printerResult;
	private String err = "";
	private static String scriptStr = "";
	private static Map<String,Bitmap> map=new HashMap<String, Bitmap>();
	public NPrinterSample(){
		printerModule=(Printer) AIDLService.getDevice().getStandardModule(ModuleType.COMMON_PRINTER);
		printerModule.init();
		printerModule.setLineSpace(2);
	}

	//添加文本
	public void addText(Bundle format, String text){
		setFontType(format);
		text = setAlgin(format, "text")+text+"\n";
		scriptStr = scriptStr + text;
		//printerResult = printerModule.print(text, 30, TimeUnit.SECONDS);
	}
	
	//添加图片
	public void addPicture(Bundle format, Bitmap bitmap){
		String fontTmp = "";
		String font = format.getString("font","");
		double wb = Arith.div(bitmap.getWidth(), 400);
		if(font.equals("small")){
			fontTmp = (int)(Arith.div(Arith.div(bitmap.getWidth(), wb), 4))+"*"+(int)(Arith.div(Arith.div(bitmap.getHeight(), wb), 4));
		}else if(font.equals("large")){
			fontTmp = (int)(Arith.div(bitmap.getWidth(), wb))+"*"+(int)(Arith.div(bitmap.getHeight(), wb));
		}else{
			fontTmp = (int)(Arith.div(Arith.div(bitmap.getWidth(), wb), 2))+"*"+(int)(Arith.div(Arith.div(bitmap.getHeight(), wb), 2));
		}
		map.put("logo", bitmap);
		scriptStr = scriptStr + setAlgin(format, "image")+fontTmp+" path:logo\n"; 
	}
	
	//添加条码
	public void addBarCode(Bundle format, String barCode){
		int height =  format.getInt("height",64);
		int width =  format.getInt("width",2);
		barCode = "!barcode "+width+" "+height+"\n "+setAlgin(format, "barcode")+barCode+"\n";
//		try {
//			printerResult = printerModule.printByScript(PrintContext.defaultContext(),barCode.getBytes("GBK"), 60, TimeUnit.SECONDS);
//		} catch (UnsupportedEncodingException e) {
//			err = "print err:"+e.getLocalizedMessage();
//		}
		scriptStr = scriptStr + barCode;
	}

	//添加二维码
	public void addQrCode(Bundle format, String qrCode){
		int height =  format.getInt("height",100);
		qrCode = "!qrcode "+height+" 2\n "+setAlgin(format, "qrcode")+qrCode+"\n";
//		try {
//			
//			printerResult = printerModule.printByScript(PrintContext.defaultContext(), qrCode.getBytes("GBK"), 60, TimeUnit.SECONDS);
//		} catch (UnsupportedEncodingException e) {
//			err = "print err:"+e.getLocalizedMessage();
//		}
		scriptStr = scriptStr + qrCode;
	}

	//走纸
	public void paperSkip(int line) throws RemoteException {
//		Bundle format = new Bundle();
//		format.putString("font", "large");
//		format.putString("align", "center");
//		setFontType(format);
//		String kg = "           ";
//		String text = "";
//		for (int i = 0; i < line; i++) {
//			text =text + setAlgin(format, "text")+kg+"\n";
//		}
//		try {
//			printerResult = printerModule.printByScript(PrintContext.defaultContext(),text.getBytes("GBK"), 60, TimeUnit.SECONDS);
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		Bundle format = new Bundle();
		format.putString("font", "large");
		format.putString("align", "center");
		setFontType(format);
		String kg = "           ";
		String text = "";
		for (int i = 0; i < line; i++) {
			text =text + setAlgin(format, "text")+kg+"\n";
		}
		scriptStr = scriptStr + text;
	}

	//获取状态
	public String getStatus() throws RemoteException {
		String rc = "";
		PrinterStatus status = printerModule.getStatus();
		if(PrinterStatus.NORMAL.equals(status)){
			rc = "00";
		}else if(PrinterStatus.OUTOF_PAPER.equals(status)){
			rc = "02";
		}else if(PrinterStatus.HEAT_LIMITED.equals(status)){
			rc = "03";
		}else if(PrinterStatus.BUSY.equals(status)){
			rc = "04";
		}else{
			rc = "05";
		}
		return rc;
	}
	
	//开始打印
	public void startPrinter(final PrinterListener printerLis) throws RemoteException {
		if(!scriptStr.equals("")){
			try {
				printerResult = printerModule.printByScript(PrintContext.defaultContext(),scriptStr.getBytes("GBK"),map, 60, TimeUnit.SECONDS);
			} catch (UnsupportedEncodingException e) {
				printerLis.onError("05", e.getLocalizedMessage());
			}
		}
		scriptStr = "";
		if(PrinterResult.SUCCESS.equals(printerResult)){
			printerLis.onFinish();
		}else if(PrinterResult.OUTOF_PAPER.equals(printerResult)){
			printerLis.onError("02", printerResult.toString());
		}else if(PrinterResult.HEAT_LIMITED.equals(printerResult)){
			printerLis.onError("03", printerResult.toString());
		}else if(PrinterResult.BUSY.equals(printerResult)){
			printerLis.onError("04", printerResult.toString());
		}else{
			printerLis.onError("05", printerResult.toString());
		}
	}
	public void setFontType(Bundle format){
		String font = format.getString("font","");
		if(font.equals("small")){
			scriptStr = scriptStr + "!hz sn\n !asc sn\n !gray 5\n!yspace 20\n";
		}else if(font.equals("large")){
			scriptStr = scriptStr + "!hz l\n !asc l\n !gray 10\n";
		}else{
			scriptStr = scriptStr + "!hz n\n !asc n !gray 1\n!yspace 6\n";
		}
	}
	public String setAlgin(Bundle format,String type){
		String align = format.getString("align","");
		String alginStr = "";
		if(align.equals("center")){
			alginStr = "*"+type+" c ";
		}else if(align.equals("right")){
			alginStr = "*"+type+" r ";
		}else{
			alginStr = "*"+type+" l ";
		}
		return alginStr;
	}
}