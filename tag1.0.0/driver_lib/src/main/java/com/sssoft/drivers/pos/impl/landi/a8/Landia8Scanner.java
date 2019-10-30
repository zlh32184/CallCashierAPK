/**
 * Copyright  2008 SilverStone Computer System Co.,Ltd
 *
 * History:
 *   2017-3-29 上午10:22:53 Created by dingwm
 */
package com.sssoft.drivers.pos.impl.landi.a8;

import android.os.RemoteException;
import android.util.Log;

import com.sssoft.drivers.pos.aidl.ScannerListener;
import com.sssoft.drivers.pos.impl.landi.a8.impl.CameraScannerImpl;
import com.sssoft.drivers.pos.service.AIDLService;
import com.sssoft.drivers.pos.aidl.Scanner;

/**
 * 类说明
 * @author <a href="mailto:dingwm@ss-soft.com">dingwm</a>
 * @version 1.0  2017-3-29 上午10:22:53
 */
public class Landia8Scanner extends Scanner.Stub{
	private CameraScannerImpl scannerImpl;

	public Landia8Scanner(){
		Log.i("cheny", "3333");
		scannerImpl = new CameraScannerImpl(AIDLService.getmContext());
	}
	
	@Override
	public void startScan(int scanType, int timeout, ScannerListener listener)
			throws RemoteException {
		scannerImpl.startScan(scanType,timeout,listener);
	}

	@Override
	public void stopScan() throws RemoteException {
		scannerImpl.close();
	}
}
