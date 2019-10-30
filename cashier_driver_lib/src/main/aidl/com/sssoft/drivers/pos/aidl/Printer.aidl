package com.sssoft.drivers.pos.aidl;
import android.graphics.Bitmap;
import android.os.Bundle;
import com.sssoft.drivers.pos.aidl.PrinterListener;
interface Printer
{
	void addText(in Bundle format,in String text);
	void addPicture(in Bundle format,in Bitmap bitmap);
	void addBarCode(in Bundle format,in String barCode);
	void addQrCode(in Bundle format,in String qrCode);
	void paperSkip(int line);
	void startPrinter(in PrinterListener printerListener);
	String getStatus();
}