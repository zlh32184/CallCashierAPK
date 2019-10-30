package com.sssoft.drivers.pos.bank.ccb.impl;

import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;

import com.ccb.deviceservice.aidl.scanner.Constant;
import com.ccb.deviceservice.aidl.scanner.IScanner;
import com.ccb.deviceservice.aidl.scanner.OnScanListener;
import com.sssoft.drivers.pos.aidl.ScannerListener;
import com.sssoft.drivers.pos.service.BankService;

/**
 * 使用摄像头进行扫码。A8终端有前后置摄像可进行前后置不同的扫码，对于诸如A7/W280P/C10等终端，
 * 因只有后置，故只能使用后置扫码功能。另外可进行扫码的前提条件是终端已集成扫码库。
 */

public class CcbCameraScannerImpl{
	private ScannerListener mListener;
	private IScanner scanner;
    public CcbCameraScannerImpl() {
        try {
			scanner = IScanner.Stub.asInterface(BankService.getDeviceService().getScanner(Constant.CameraID.BACK));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }


    public void startScan(int scanType, int timeout, final com.sssoft.drivers.pos.aidl.ScannerListener listener){
		try {
			mListener = listener;
			Bundle bundle = new Bundle();
			bundle.putLong("timeout", timeout);
			bundle.putString("title", "请对准二维码或条码进行扫描");
			scanner.startScan(bundle,new OnScanListener.Stub() {
				@Override
				public void onSuccess(Bundle bundle) throws RemoteException {
					mListener.onScanResult(bundle.getString("barcode"));			
				}

				@Override
				public void onError(int error, String message) throws RemoteException {
					if(mListener!=null)
						mListener.onError(""+error, message);		
				}

				@Override
				public void onTimeout() throws RemoteException {
					if(mListener!=null)
						mListener.onError("X2", "扫码超时");		
				}

				@Override
				public void onCancel() throws RemoteException {
					if(mListener!=null)
						mListener.onError("X1", "取消扫描");	
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
