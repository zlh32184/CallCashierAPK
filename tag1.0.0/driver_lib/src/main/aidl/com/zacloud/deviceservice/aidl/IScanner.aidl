package com.zacloud.deviceservice.aidl;

import com.zacloud.deviceservice.aidl.ScannerListener;

/**
 * 扫码器对象
 * 实现设备对条码扫码功能。
 */
interface IScanner {

	/**
	 * 启动扫码
	 * @param timeout - 超时时间，单位ms
	 * @param listener - 扫码结果监听
	 */
	void startScan(long timeout, ScannerListener listener);

	/**
	 * 停止扫码
	 */
	void stopScan();

}
