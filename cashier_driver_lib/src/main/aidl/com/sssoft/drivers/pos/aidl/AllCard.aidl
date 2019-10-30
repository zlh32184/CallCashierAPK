package com.sssoft.drivers.pos.aidl;
import com.sssoft.drivers.pos.aidl.AllCardListener;
interface AllCard
{
	void read(int timeout, in AllCardListener listener);
	void setM1Key(String Key); 
	void cancel();
}