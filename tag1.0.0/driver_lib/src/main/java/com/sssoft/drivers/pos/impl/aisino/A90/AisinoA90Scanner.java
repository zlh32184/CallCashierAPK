/**
 * Copyright  2008 SilverStone Computer System Co.,Ltd
 *
 * History:
 *   2017-3-29 上午10:22:53 Created by dingwm
 */
package com.sssoft.drivers.pos.impl.aisino.A90;

import android.os.RemoteException;

import com.sssoft.drivers.pos.aidl.Scanner;
import com.sssoft.drivers.pos.aidl.ScannerListener;

/**
 * 类说明
 * @author <a href="mailto:dingwm@ss-soft.com">dingwm</a>
 * @version 1.0  2017-3-29 上午10:22:53
 */
public class AisinoA90Scanner extends Scanner.Stub{
	private AScannerSample scannerSample;

	public AisinoA90Scanner(){
		scannerSample =  new AScannerSample();
	}

	@Override
	public void startScan(int scanType, int timeout, ScannerListener listener)
			throws RemoteException {
		scannerSample.startScan(scanType,timeout,listener);
	}

	@Override
	public void stopScan() throws RemoteException {
		scannerSample.stopScan();
	}
	
}
