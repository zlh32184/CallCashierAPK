/**
 * Copyright  2008 SilverStone Computer System Co.,Ltd
 *
 * History:
 *   2017-3-29 上午10:22:53 Created by dingwm
 */
package com.sssoft.drivers.pos.bank.boc;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.RemoteException;

import com.sssoft.drivers.pos.aidl.Printer;
import com.sssoft.drivers.pos.aidl.PrinterListener;
import com.sssoft.drivers.pos.bank.boc.impl.BocPrinterImpl;

/**
 * 类说明
 * @author <a href="mailto:dingwm@ss-soft.com">dingwm</a>
 * @version 1.0  2017-3-29 上午10:22:53
 */
public class BocPrinter extends Printer.Stub{
	private BocPrinterImpl printerImpl;

	public BocPrinter(){
		printerImpl = new BocPrinterImpl();
	}
	
	@Override
	public void addText(Bundle format, String text) throws RemoteException {
		printerImpl.addText(format,text);
	}

	@Override
	public void addPicture(Bundle format, Bitmap bitmap)
			throws RemoteException {
		printerImpl.addPicture(format, bitmap);
	}

	@Override
	public void addBarCode(Bundle format, String barCode)
			throws RemoteException {
		printerImpl.addBarCode(format, barCode);
	}

	@Override
	public void addQrCode(Bundle format, String qrCode)
			throws RemoteException {
		printerImpl.addQrCode(format, qrCode);
	}

	@Override
	public void paperSkip(int line) throws RemoteException {
		printerImpl.paperSkip(line);
	}

	@Override
	public String getStatus() throws RemoteException {
		return printerImpl.getStatus();
	}

	@Override
	public void startPrinter(PrinterListener printerListener)
			throws RemoteException {
		try {
			printerImpl.startPrinter(printerListener);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
