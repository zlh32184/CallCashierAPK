package com.zacloud.deviceservice.aidl;

/**
 * 打印结果监听器
 */
interface PrinterListener {

	/**
	 * 打印成功回调
	 */
	void onFinish();

	/**
	 * 打印失败回调
	 * @param error - 错误码
	 */
	void onError(int error);

}
