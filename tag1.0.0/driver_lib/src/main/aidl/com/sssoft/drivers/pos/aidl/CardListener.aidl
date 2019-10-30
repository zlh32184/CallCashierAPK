package com.sssoft.drivers.pos.aidl;

interface CardListener
{
	void onReadResult(in String[] cardInfo);
	void onError(String code,String detail);
	void onFinish();

}