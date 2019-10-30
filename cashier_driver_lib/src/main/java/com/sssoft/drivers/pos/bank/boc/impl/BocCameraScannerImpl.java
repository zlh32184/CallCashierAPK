package com.sssoft.drivers.pos.bank.boc.impl;

import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;

import com.boc.aidl.scanner.AidlScanner;
import com.boc.aidl.scanner.AidlScannerListener;
import com.sssoft.drivers.pos.aidl.ScannerListener;
import com.sssoft.drivers.pos.service.AIDLService;

/**
 * 使用摄像头进行扫码。A8终端有前后置摄像可进行前后置不同的扫码，对于诸如A7/W280P/C10等终端，
 * 因只有后置，故只能使用后置扫码功能。另外可进行扫码的前提条件是终端已集成扫码库。
 */

public class BocCameraScannerImpl {
	private ScannerListener mListener;
	private AidlScanner scanner;
    public BocCameraScannerImpl() {
        try {
			scanner = AidlScanner.Stub.asInterface(AIDLService.getBocDeviceService().getScan());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }


    public void startScan(int scanType, int timeout, final ScannerListener listener){
		try {
			mListener = listener;
			Bundle bundle = new Bundle();
			bundle.putLong("timeout", timeout);
			bundle.putString("title", "请对准二维码或条码进行扫描");
			scanner.startScan(scanType-1,timeout*1000, new AidlScannerListener.Stub() {

				@Override
				public void onScanResult(String[] strings) throws RemoteException {
					mListener.onScanResult(strings[0]);
				}

				@Override
				public void onFinish() throws RemoteException {
					mListener.onError("00","扫码结束");
				}
			});
		} catch (RemoteException e) {
			Log.i("cheny", "arg5:");
		}
	}
	public void stopScan(){
		try {
			scanner.stopScan();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
