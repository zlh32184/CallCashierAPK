/**
 * Copyright  2008 SilverStone Computer System Co.,Ltd
 *
 * History:
 *   2017-3-29 上午10:22:53 Created by dingwm
 */
package com.sssoft.drivers.pos.impl.aisino.A90;

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
public class AisinoA90Printer extends Printer.Stub{
	private APrinterSample arinterSample;

	public AisinoA90Printer(){
		try {
			arinterSample =  new APrinterSample();
		} catch (RemoteException e) {
			
		}
	}
	
	@Override
	public void addText(Bundle format, String text)
			throws RemoteException {		
		arinterSample.addText(format,text);
	}

	@Override
	public void addPicture(Bundle format, Bitmap bitmap)
			throws RemoteException {
		arinterSample.addPicture(format,bitmap);
	}

	@Override
	public void addBarCode(Bundle format, String barCode)
			throws RemoteException {
		arinterSample.addBarCode(format,barCode);
	}

	@Override
	public void addQrCode(Bundle format, String qrCode)
			throws RemoteException {
		arinterSample.addQrCode(format,qrCode);
	}

	@Override
	public void paperSkip(int line) throws RemoteException {
		arinterSample.paperSkip(line);
	}

	@Override
	public String getStatus() throws RemoteException {
		return arinterSample.getStatus();		
	}

	@Override
	public void startPrinter(PrinterListener printerListener)
			throws RemoteException {
		arinterSample.startPrinter(printerListener);		
	}
}
