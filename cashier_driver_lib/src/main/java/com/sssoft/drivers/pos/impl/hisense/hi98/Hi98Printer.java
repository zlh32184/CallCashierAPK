package com.sssoft.drivers.pos.impl.hisense.hi98;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;

import com.sssoft.drivers.pos.aidl.Printer;
import com.sssoft.drivers.pos.aidl.PrinterListener;
import com.sssoft.drivers.pos.service.AIDLService;


public class Hi98Printer  extends Printer.Stub{

	private Hi98PrinterSample printerSample;
	
	public Hi98Printer() {
		this.printerSample = AIDLService.getHiPrinterSample();
	}

	@Override
	public void addText(Bundle format, String text) throws RemoteException {
		// TODO Auto-generated method stub
		printerSample.addText(format,text);
	}

	@Override
	public void addPicture(Bundle format, Bitmap bitmap) throws RemoteException {
		// TODO Auto-generated method stub
		printerSample.addPicture(format,bitmap);
	}

	@Override
	public void addBarCode(Bundle format, String barCode)
			throws RemoteException {
		// TODO Auto-generated method stub
		printerSample.addBarCode(format,barCode);
	}

	@Override
	public void addQrCode(Bundle format, String qrCode) throws RemoteException {
		// TODO Auto-generated method stub
		printerSample.addQrCode(format,qrCode);
	}

	@Override
	public void paperSkip(int line) throws RemoteException {
		// TODO Auto-generated method stub
		printerSample.paperSkip(line);
	}

	@Override
	public void startPrinter(PrinterListener printerListener)
			throws RemoteException {
		// TODO Auto-generated method stub
		printerSample.startPrinter(printerListener);	
	}

	@Override
	public String getStatus() throws RemoteException {
		// TODO Auto-generated method stub
		return printerSample.getStatus();	
	}

}
