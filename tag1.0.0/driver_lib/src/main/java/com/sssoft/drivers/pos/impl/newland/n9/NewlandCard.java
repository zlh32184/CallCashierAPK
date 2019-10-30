/**
 * Copyright  2008 SilverStone Computer System Co.,Ltd
 *
 * History:
 *   2017-3-29 上午10:22:53 Created by dingwm
 */
package com.sssoft.drivers.pos.impl.newland.n9;

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
public class NewlandCard extends Card.Stub{
	private NewlandCardSample cardSample;
	
	public NewlandCard(){
		cardSample =  new NewlandCardSample();
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
