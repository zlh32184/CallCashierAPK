package com.sssoft.drivers.pos.aidl;

interface RfM1CardListener
{
	void onReadResult(in String[] cardInfo);
	void onError(String code,String detail);
	void onFinish();

}