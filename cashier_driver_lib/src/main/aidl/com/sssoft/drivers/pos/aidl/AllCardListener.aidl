package com.sssoft.drivers.pos.aidl;

interface AllCardListener
{
	void onReadResult(in String cardInfo);
	void onError(String code,String detail);
	void onFinish();

}