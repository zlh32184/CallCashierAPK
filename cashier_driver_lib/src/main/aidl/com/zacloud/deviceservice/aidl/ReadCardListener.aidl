package com.zacloud.deviceservice.aidl;

/**
 * 读卡监听器
 */
interface ReadCardListener {
    //读卡失败，通过回调监听返回错误码和错误信息
	void onError(int errorCode, String message);

	//读卡成功，通过回调监听返回卡号
    void onSuccess(String cardNo);
}
