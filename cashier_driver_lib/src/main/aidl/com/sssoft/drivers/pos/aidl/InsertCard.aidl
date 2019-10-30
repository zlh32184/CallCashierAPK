package com.sssoft.drivers.pos.aidl;
import com.sssoft.drivers.pos.aidl.InsertCardListener;
interface InsertCard
{
	void read(int timeout, in InsertCardListener listener);
	void cancel();
}