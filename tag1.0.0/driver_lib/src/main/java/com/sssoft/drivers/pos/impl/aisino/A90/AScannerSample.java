package com.sssoft.drivers.pos.impl.aisino.A90;

import android.os.Bundle;
import android.os.RemoteException;

import com.icbc.smartpos.deviceservice.aidl.IScanner;
import com.icbc.smartpos.deviceservice.aidl.ScannerListener;
import com.sssoft.drivers.pos.service.AIDLService;

public class AScannerSample {
	private IScanner scanner;
	private com.sssoft.drivers.pos.aidl.ScannerListener scannerlistener = null;
	public void startScan(int scanType, int timeout, final com.sssoft.drivers.pos.aidl.ScannerListener listener){
		try {
			scannerlistener = listener;
			scanner = IScanner.Stub.asInterface(AIDLService.getDeviceServiceA90().getScanner(scanType));
			Bundle param = new Bundle();
			param.putString("upPromptString", "将二维码放入框内, 即可自动扫描");
			scanner.startScan(param, timeout, new ScannerListener.Stub() {
				
				@Override
				public void onTimeout() throws RemoteException {
					scannerlistener.onFinish();
				}
				
				@Override
				public void onSuccess(String barcode) throws RemoteException {
					scannerlistener.onScanResult(barcode);
					scannerlistener.onFinish();
				}
				
				@Override
				public void onError(int error, String message) throws RemoteException {
					scannerlistener.onError(error+"", "扫描失败:"+message);
				}
				
				@Override
				public void onCancel() throws RemoteException {
					if(scannerlistener!=null)
						scannerlistener.onError("X1", "取消扫描");
					
				}
			});
		} catch (RemoteException e) {
			
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