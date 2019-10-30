/**
 * Copyright  2008 SilverStone Computer System Co.,Ltd
 *
 * History:
 *   2017-3-29 上午10:22:53 Created by dingwm
 */
package com.sssoft.drivers.pos.impl.hisense.hi98;

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
public class Hi98Card extends Card.Stub{
	private Hi98CardSample cardSample;
	
	public Hi98Card(){
		cardSample =  AIDLService.getHiCardSample();
		Log.i("Hi98Card","getcardsample");
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
