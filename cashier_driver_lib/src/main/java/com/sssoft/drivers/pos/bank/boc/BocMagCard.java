/**
 * Copyright  2008 SilverStone Computer System Co.,Ltd
 *
 * History:
 *   2017-3-29 上午10:22:53 Created by dingwm
 */
package com.sssoft.drivers.pos.bank.boc;

import android.os.RemoteException;

import com.sssoft.drivers.pos.aidl.Card;
import com.sssoft.drivers.pos.aidl.CardListener;
import com.sssoft.drivers.pos.bank.ccb.impl.CcbMagCardImpl;

/**
 * 类说明
 * @author <a href="mailto:dingwm@ss-soft.com">dingwm</a>
 * @version 1.0  2017-3-29 上午10:22:53
 */
public class BocMagCard extends Card.Stub{
	private CcbMagCardImpl cardSample;
	
	public BocMagCard(){
		try {
			cardSample =  new CcbMagCardImpl();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void read(int timeout, CardListener allCardlistener) throws RemoteException {
		// TODO Auto-generated method stub
		cardSample.searchCard(timeout,allCardlistener);
	}

	@Override
	public void cancel() throws RemoteException {
		// TODO Auto-generated method stub
		cardSample.cancel();
	}


}
