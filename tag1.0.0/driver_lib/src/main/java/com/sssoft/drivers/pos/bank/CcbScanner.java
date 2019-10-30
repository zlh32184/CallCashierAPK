/**
 * Copyright  2008 SilverStone Computer System Co.,Ltd
 *
 * History:
 *   2017-3-29 上午10:22:53 Created by dingwm
 */
package com.sssoft.drivers.pos.bank;

import android.os.RemoteException;

import com.sssoft.drivers.pos.aidl.Scanner;
import com.sssoft.drivers.pos.aidl.ScannerListener;
import com.sssoft.drivers.pos.bank.ccb.impl.CcbCameraScannerImpl;

/**
 * 类说明
 * @author <a href="mailto:dingwm@ss-soft.com">dingwm</a>
 * @version 1.0  2017-3-29 上午10:22:53
 */
public class CcbScanner extends Scanner.Stub{
	private CcbCameraScannerImpl scannerImpl;

	public CcbScanner(){
		scannerImpl = new CcbCameraScannerImpl();
	}
	
	@Override
	public void startScan(int scanType, int timeout, ScannerListener listener)
			throws RemoteException {
		scannerImpl.startScan(scanType,timeout,listener);
	}

	@Override
	public void stopScan() throws RemoteException {
		scannerImpl.stopScan();;
	}
}
