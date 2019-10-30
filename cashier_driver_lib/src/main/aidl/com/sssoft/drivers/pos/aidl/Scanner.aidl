package com.sssoft.drivers.pos.aidl;
import com.sssoft.drivers.pos.aidl.ScannerListener;
interface Scanner
{
	void startScan(int scanType, int timeout, in ScannerListener listener);
	void stopScan();
}