/**
 * Copyright  2008 SilverStone Computer System Co.,Ltd
 *
 * History:
 *   2017-3-29 上午10:22:53 Created by dingwm
 */
package com.sssoft.drivers.pos.impl.landi.a8;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.RemoteException;

import com.sssoft.drivers.pos.aidl.Printer;
import com.sssoft.drivers.pos.aidl.PrinterListener;
import com.sssoft.drivers.pos.impl.landi.a8.impl.PrinterImpl;
import com.sssoft.drivers.pos.service.AIDLService;

/**
 * 类说明
 * @author <a href="mailto:dingwm@ss-soft.com">dingwm</a>
 * @version 1.0  2017-3-29 上午10:22:53
 */
public class Landia8Printer extends Printer.Stub{
	private PrinterImpl printerImpl;

	public Landia8Printer(){
		printerImpl = new PrinterImpl(AIDLService.getmContext()); 
		printerImpl.init();
	}
	
	@Override
	public void addText(Bundle format, String text) throws RemoteException {
		printerImpl.addText(format,text);
	}

	@Override
	public void addPicture(Bundle format, Bitmap bitmap)
			throws RemoteException {
		printerImpl.addBitmap(format, bitmap);
	}

	@Override
	public void addBarCode(Bundle format, String barCode)
			throws RemoteException {
		printerImpl.addBarcode(format, barCode);
	}

	@Override
	public void addQrCode(Bundle format, String qrCode)
			throws RemoteException {
		printerImpl.addQRcode(format, qrCode);
	}

	@Override
	public void paperSkip(int line) throws RemoteException {
		printerImpl.feedLine(line);
	}

	@Override
	public String getStatus() throws RemoteException {
		return printerImpl.getPrinterStatus()+"";
	}

	@Override
	public void startPrinter(PrinterListener printerListener)
			throws RemoteException {
		try {
			printerImpl.startPrint(printerListener);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
