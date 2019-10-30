/**
 * Copyright  2008 SilverStone Computer System Co.,Ltd
 *
 * History:
 *   2017-3-29 上午9:55:13 Created by dingwm
 */
package com.sssoft.drivers.pos.service;

import android.os.IBinder;
import android.os.RemoteException;

import com.sssoft.drivers.pos.aidl.AllCard;
import com.sssoft.drivers.pos.aidl.Card;
import com.sssoft.drivers.pos.aidl.InsertCard;
import com.sssoft.drivers.pos.aidl.Printer;
import com.sssoft.drivers.pos.aidl.RfM1Card;
import com.sssoft.drivers.pos.aidl.Scanner;
import com.sssoft.drivers.pos.aidl.XposDriverInc;
import com.sssoft.drivers.pos.sdk.XposDriver;

/**
 * 类说明
 * @author <a href="mailto:dingwm@ss-soft.com">dingwm</a>
 * @version 1.0  2017-3-29 上午9:55:13
 */
public class XposDriverService extends XposDriverInc.Stub {
	
	@Override
	public Printer getPrinter() throws RemoteException {
		return XposDriver.getPrinter();
	}
	
	@Override
	public Card getCard() throws RemoteException {
		return XposDriver.getCard();
	}

	@Override
	public IBinder asBinder() {
		return null;
	}

	@Override
	public InsertCard getInsertCard() throws RemoteException {
		return XposDriver.getInsertCard();
	}

	@Override
	public AllCard getAllCard() throws RemoteException {
		return XposDriver.getAllCard();
	}

	@Override
	public Scanner getScanner() throws RemoteException {
		return XposDriver.getScanner();
	}
	@Override
	public RfM1Card getRfM1Card() throws RemoteException {
		return XposDriver.getRfM1Card();
	}
	
}
