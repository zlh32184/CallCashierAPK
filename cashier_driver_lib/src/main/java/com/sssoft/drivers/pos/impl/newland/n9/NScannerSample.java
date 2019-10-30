package com.sssoft.drivers.pos.impl.newland.n9;

import android.content.Intent;
import android.util.Log;

import com.newland.mtype.ModuleType;
import com.newland.mtype.module.common.scanner.BarcodeScanner;
import com.newland.mtype.module.common.scanner.BarcodeScannerManager;
import com.sssoft.drivers.pos.service.AIDLService;
import com.sssoft.drivers.pos.service.view.ScanViewActivity;

public class NScannerSample {
	public static BarcodeScanner scanner = null;
	public static com.sssoft.drivers.pos.aidl.ScannerListener listener2 = null;
	public NScannerSample(){
		Log.i("cheny", AIDLService.getDevice()==null?"yes":"no");
		BarcodeScannerManager barcodeScannerManager=(BarcodeScannerManager) AIDLService.getDevice().getStandardModule(ModuleType.COMMON_BARCODESCANNER);
		scanner = barcodeScannerManager.getDefault();
	}

	public void startScan(int scanType, int timeout, final com.sssoft.drivers.pos.aidl.ScannerListener listener){
		listener2 = listener;
		Intent intent = new Intent(AIDLService.getmContext(), ScanViewActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
		intent.putExtra("timeout", timeout);
		AIDLService.getmContext().startActivity(intent);
	}
	public void stopScan(){
		try {
			scanner.stopScan();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}