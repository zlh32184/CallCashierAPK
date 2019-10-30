/**
 * Copyright  2008 SilverStone Computer System Co.,Ltd
 *
 * History:
 *   2017-3-29 上午10:22:53 Created by dingwm
 */
package com.sssoft.drivers.pos.impl.aisino.A90;

import android.os.RemoteException;

import com.sssoft.drivers.pos.aidl.Card;
import com.sssoft.drivers.pos.aidl.CardListener;

/**
 * 类说明
 * @author <a href="mailto:dingwm@ss-soft.com">dingwm</a>
 * @version 1.0  2017-3-29 上午10:22:53
 */
public class AisinoMagCard extends Card.Stub{
	private AisinoMagCardSample cardSample;
	
	public AisinoMagCard(){
		try {
			cardSample =  new AisinoMagCardSample();
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
