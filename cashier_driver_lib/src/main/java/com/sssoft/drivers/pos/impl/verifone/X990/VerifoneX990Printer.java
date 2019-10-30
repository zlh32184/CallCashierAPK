/**
 * Copyright  2008 SilverStone Computer System Co.,Ltd
 *
 * History:
 *   2017-3-29 上午10:22:53 Created by dingwm
 */
package com.sssoft.drivers.pos.impl.verifone.X990;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;

import com.sssoft.drivers.pos.aidl.Printer;
import com.sssoft.drivers.pos.aidl.PrinterListener;
import com.sssoft.drivers.pos.impl.newland.n9.NPrinterSample;

/**
 * 类说明
 * @author <a href="mailto:dingwm@ss-soft.com">dingwm</a>
 * @version 1.0  2017-3-29 上午10:22:53
 */
public class VerifoneX990Printer extends Printer.Stub{
	private XPrinterSample xrinterSample;

	public VerifoneX990Printer(){
		try {
			xrinterSample =  new XPrinterSample();
		} catch (RemoteException e) {
			
		}
	}
	
	@Override
	public void addText(Bundle format, String text)
			throws RemoteException {		
		xrinterSample.addText(format,text);
	}

	@Override
	public void addPicture(Bundle format, Bitmap bitmap)
			throws RemoteException {
		xrinterSample.addPicture(format,bitmap);
	}

	@Override
	public void addBarCode(Bundle format, String barCode)
			throws RemoteException {
		xrinterSample.addBarCode(format,barCode);
	}

	@Override
	public void addQrCode(Bundle format, String qrCode)
			throws RemoteException {
		xrinterSample.addQrCode(format,qrCode);
	}

	@Override
	public void paperSkip(int line) throws RemoteException {
		xrinterSample.paperSkip(line);
	}

	@Override
	public String getStatus() throws RemoteException {
		return xrinterSample.getStatus();		
	}

	@Override
	public void startPrinter(PrinterListener printerListener)
			throws RemoteException {
		xrinterSample.startPrinter(printerListener);		
	}
}
