/**
 * Copyright  2008 SilverStone Computer System Co.,Ltd
 *
 * History:
 *   2017-3-29 上午10:22:53 Created by dingwm
 */
package com.sssoft.drivers.pos.impl.newland.n9;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.RemoteException;

import com.sssoft.drivers.pos.aidl.Printer;
import com.sssoft.drivers.pos.aidl.PrinterListener;

/**
 * 类说明
 * @author <a href="mailto:dingwm@ss-soft.com">dingwm</a>
 * @version 1.0  2017-3-29 上午10:22:53
 */
public class Newlandn9Printer extends Printer.Stub{
	private NPrinterSample printerSample;

	public Newlandn9Printer(){
		printerSample =  new NPrinterSample();
	}
	
	@Override
	public void addText(Bundle format, String text)
			throws RemoteException {		
		printerSample.addText(format,text);
	}

	@Override
	public void addPicture(Bundle format, Bitmap bitmap)
			throws RemoteException {
		printerSample.addPicture(format,bitmap);
	}

	@Override
	public void addBarCode(Bundle format, String barCode)
			throws RemoteException {
		printerSample.addBarCode(format,barCode);
	}

	@Override
	public void addQrCode(Bundle format, String qrCode)
			throws RemoteException {
		printerSample.addQrCode(format,qrCode);
	}

	@Override
	public void paperSkip(int line) throws RemoteException {
		printerSample.paperSkip(line);
	}

	@Override
	public String getStatus() throws RemoteException {
		return printerSample.getStatus();		
	}

	@Override
	public void startPrinter(PrinterListener printerListener)
			throws RemoteException {
		printerSample.startPrinter(printerListener);		
	}
}
