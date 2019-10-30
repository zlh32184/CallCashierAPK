package com.sssoft.drivers.pos.impl.hisense.hi98;

import java.util.HashMap;
import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.RemoteException;
import com.hisense.pos.spiprinter.SpiPrinter;
import com.sssoft.drivers.pos.aidl.PrinterListener;
import com.sssoft.drivers.pos.util.Arith;
import com.sssoft.drivers.pos.util.BarCodeUtil;
import com.sssoft.drivers.pos.util.QRCodeUtil;

public class Hi98PrinterSample {


	private SpiPrinter printerModule;
	private HashMap<Integer,String> RCMap = new HashMap<Integer, String>();
	private HashMap<String,Integer> fontMap = new HashMap<String, Integer>();
	private HashMap<String,Integer> alignMap = new HashMap<String, Integer>();
	private HashMap<String,Integer> wAttrMap = new HashMap<String, Integer>();
	private Bitmap barCodeImage;
	private Bitmap qrCodeImage;
	private int barCodeAlign = SpiPrinter.PRN_CENTER;
	private int qrCodeAlign = SpiPrinter.PRN_CENTER;
	public Hi98PrinterSample(){
		//printerModule = AIDLService.getHiPrinterSample();
		printerModule = new SpiPrinter();
		printerModule.Printer_init();
		fontMap.put("large", SpiPrinter.FONT_SIZE_LARGE);
		fontMap.put("normal",SpiPrinter.FONT_SIZE_MEDIUM);
		fontMap.put("small", SpiPrinter.FONT_SIZE_SMALL);
		alignMap.put("left", SpiPrinter.PRN_LEFT);
		alignMap.put("center", SpiPrinter.PRN_CENTER);
		alignMap.put("right", SpiPrinter.PRN_RIGHT);
		wAttrMap.put("bold", SpiPrinter.FONT_ATTR_BOLD);
		wAttrMap.put("normal", SpiPrinter.FONT_ATTR_NORMAL);

		RCMap.put(SpiPrinter.PRINTER_OK,"Success");
		RCMap.put(SpiPrinter.PRINTER_ERROR,"Fail");
		RCMap.put(SpiPrinter.PRINTER_ERROR_NOPAPER,"Printer is out of paper");
		RCMap.put(SpiPrinter.PRINTER_ERROR_HOT,"Printer overheating");
		RCMap.put(SpiPrinter.PRINTER_ERROR_BUSY,"Printer busy");
		RCMap.put(SpiPrinter.PRINTER_ERROR_LOWPOWER,"Low power");
		RCMap.put(SpiPrinter.PRINTER_ERROR_FEEDPAPER_PARA,"Wrong parameter");
		RCMap.put(SpiPrinter.PRINTER_ERROR_MEMORYOUT,"Out of memory");
		RCMap.put(SpiPrinter.PRINTER_ERROR_FONTSIZE,"Wrong font size parameter");
		RCMap.put(SpiPrinter.PRINTER_ERROR_BOLDFONT,"Wrong font size parameter");
		RCMap.put(SpiPrinter.PRINTER_ERROR_GRAYPARA,"Wrong parameter");
		RCMap.put(SpiPrinter.PRINTER_ERROR_PATTERN,"Alignment parameter error");
	}

	//添加文本
	public void addText(Bundle format, String text) throws RemoteException {
		int font = SpiPrinter.FONT_SIZE_MEDIUM;
		int align = SpiPrinter.PRN_LEFT;
		int wattr = SpiPrinter.FONT_ATTR_NORMAL;

		if(format!=null && format.containsKey("font")==true)
			if(format.get("font").equals("large")||format.get("font").equals("small"))
				font = fontMap.get(format.get("font"));
		if(format!=null && format.containsKey("align")==true)
			if(format.get("align").equals("center")||format.get("align").equals("right"))
				align = alignMap.get(format.get("align"));
		if(format!=null && format.containsKey("wattr")==true)
			if(format.get("wattr").equals("bold"))
				wattr = wAttrMap.get(format.get("wattr"));

		printerModule.Printer_TextStr(text, font, wattr, align);
	}

	//添加图片
	public void addPicture(Bundle format, Bitmap bitmap) throws RemoteException {
		int align = SpiPrinter.PRN_LEFT;
		if(format!=null && format.containsKey("align")==true)
			if(format.get("align").equals("center")||format.get("align").equals("right"))
				align = alignMap.get(format.get("align"));
		printerModule.Printer_Image(bitmap, align);

	}

	//添加条码
	public void addBarCode(Bundle format, String barCode) throws RemoteException {
		int align = SpiPrinter.PRN_CENTER;
		int widthPix = 200;
		int heightPix = 80;
		if(format!=null && format.containsKey("align")==true)
			if(format.get("align").equals("left")||format.get("align").equals("right"))
				align = alignMap.get(format.get("align"));

//		if(format!=null && format.containsKey("height")==true)
//			heightPix = format.getInt("height");
//		if(format!=null && format.containsKey("width")==true)
//			widthPix = Math.max(format.getInt("width"), 100);//宽度定义不小于100
		barCodeImage = BarCodeUtil.creatBarCode(barCode, widthPix, heightPix, null);
		//printerModule.Printer_Image(, align);
		barCodeAlign = align;
	}

	//添加二维码
	@SuppressLint("NewApi")
	public void addQrCode(Bundle format, String qrCode) throws RemoteException {
		int align = SpiPrinter.PRN_CENTER;
		int widthPix = 255;
		int heightPix = 325;
		if(format!=null && format.containsKey("align")==true)
			if(format.get("align").equals("left")||format.get("align").equals("right"))
				align = alignMap.get(format.get("align"));

		String font = format.getString("font","");
		if(font.equals("small")){
			heightPix = 125;
		}else if(font.equals("large")){
			heightPix = 525;
		}else{
			heightPix = 325;
		}
		qrCodeImage = QRCodeUtil.createQRBmp(qrCode, widthPix, heightPix, null);
		qrCodeAlign = align;
		//printerModule.Printer_Image(QRCodeUtil.createQRBmp(qrCode, widthPix, heightPix, null), align);
	}

	//走纸
	public void paperSkip(int line) throws RemoteException {
		int font = SpiPrinter.FONT_SIZE_MEDIUM;
		int align = SpiPrinter.PRN_LEFT;
		int wattr = SpiPrinter.FONT_ATTR_NORMAL;
		for (int i = 0; i < line; i++) {
			printerModule.Printer_TextStr("             ", font, wattr, align);
		}
	}

	//获取状态
	public String getStatus() throws RemoteException {
		return RCMap.get(printerModule.Printer_getStatus());
	}

	//开始打印
	public void startPrinter(final PrinterListener printerLis) throws RemoteException {
		int status = printerModule.Printer_Start();
		if(barCodeImage!=null){
			printerModule.Printer_Image(barCodeImage, barCodeAlign);
			printerModule = new SpiPrinter();
			printerModule.Printer_init();
			paperSkip(4);
			printerModule.Printer_Start();
		}
		if(qrCodeImage!=null){
			printerModule.Printer_Image(qrCodeImage, qrCodeAlign);
			printerModule = new SpiPrinter();
			printerModule.Printer_init();
			paperSkip(4);
			printerModule.Printer_Start();
		}
		//Log.i("print_status",RCMap.get(status) );
		if (status == SpiPrinter.PRINTER_OK )
			printerLis.onFinish();
		else
			printerLis.onError(Integer.toString(status), RCMap.get(status));
		//清缓存
		printerModule = new SpiPrinter();
		printerModule.Printer_init();
		barCodeImage = null;
		qrCodeImage = null;
	}

}
