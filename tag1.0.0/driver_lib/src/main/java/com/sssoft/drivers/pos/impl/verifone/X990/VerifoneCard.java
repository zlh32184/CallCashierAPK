/**
 * Copyright  2008 SilverStone Computer System Co.,Ltd
 *
 * History:
 *   2017-3-29 上午10:22:53 Created by dingwm
 */
package com.sssoft.drivers.pos.impl.verifone.X990;

import android.os.RemoteException;
import android.util.Log;

import com.hisense.pos.magic.MagCard;
import com.sssoft.drivers.pos.aidl.AllCardListener;
import com.sssoft.drivers.pos.aidl.AllCard;
import com.sssoft.drivers.pos.aidl.Card;
import com.sssoft.drivers.pos.aidl.CardListener;
import com.sssoft.drivers.pos.service.AIDLService;

/**
 * 类说明
 * @author <a href="mailto:dingwm@ss-soft.com">dingwm</a>
 * @version 1.0  2017-3-29 上午10:22:53
 */
public class VerifoneCard extends AllCard.Stub{
	private VerifoneCardSample cardSample;
	
	public VerifoneCard(){
		try {
			cardSample =  new VerifoneCardSample();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void read(int timeout, AllCardListener allCardlistener) throws RemoteException {
		// TODO Auto-generated method stub
		cardSample.searchCard(timeout,allCardlistener);
	}

	@Override
	public void cancel() throws RemoteException {
		// TODO Auto-generated method stub
		cardSample.cancel();
	}

	@Override
	public void setM1Key(String Key) throws RemoteException {
		// TODO Auto-generated method stub
		
	}


}
