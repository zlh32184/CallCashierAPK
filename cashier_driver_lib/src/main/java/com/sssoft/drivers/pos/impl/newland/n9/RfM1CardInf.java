/**
 * Copyright  2008 SilverStone Computer System Co.,Ltd
 *
 * History:
 *   2017-3-29 上午10:22:53 Created by dingwm
 */
package com.sssoft.drivers.pos.impl.newland.n9;

import android.os.RemoteException;

import com.sssoft.drivers.pos.aidl.RfM1CardListener;
import com.sssoft.drivers.pos.aidl.RfM1Card;
/**
 * 类说明
 * @author <a href="mailto:dingwm@ss-soft.com">dingwm</a>
 * @version 1.0  2017-3-29 上午10:22:53
 */
public class RfM1CardInf extends RfM1Card.Stub{
	private RfM1CardSample cardSample;
	
	public RfM1CardInf(){
		cardSample =  new RfM1CardSample();
	}
	
	@Override
	public void read(int timeout,String b0auk,String b4auk ,RfM1CardListener rfCardlistener) throws RemoteException {
		// TODO Auto-generated method stub
		cardSample.searchCard(timeout,b0auk,b4auk,rfCardlistener);
	}

	@Override
	public void cancel() throws RemoteException {
		// TODO Auto-generated method stub
		cardSample.cancel();
	}


}
