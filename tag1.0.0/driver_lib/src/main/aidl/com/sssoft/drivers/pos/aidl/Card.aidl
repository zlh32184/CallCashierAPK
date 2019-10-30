package com.sssoft.drivers.pos.aidl;
import com.sssoft.drivers.pos.aidl.CardListener;
interface Card
{
	void read(int timeout, in CardListener listener);
	void cancel();
}