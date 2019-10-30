package com.zacloud.deviceservice.aidl;

/**
 * 扫码器扫码结果监听器
 */
interface ScannerListener {

	/**
	 * 扫码成功回调
	 * @param barcode - 条码
	 */
    void onSuccess(String barcode);

    /**
     * 扫码出错
     * @param error - 错误码
     * <ul>
     * <li>ERROR_INIT_FAIL(1) - 初始化解码库失败</li>
     * <li>ERROR_ALREADY_INIT(2) - 已经初始化</li>
     * <li>ERROR_INIT_ENGINE(3) - 初始化扫码模组失败</li>
     * <li>ERROR_AUTH_LICENSE(4) - License认证失败</li>
     * <li>ERROR_OPEN_CAMERA(5) - 打开摄像头失败</li>
     * </ul>
     * @param message - 错误描述
     */
    void onError(int error, String message);

	/**
	 * 扫码超时回调
	 */
    void onTimeout();

    /**
     * 扫码取消回调
     */
    void onCancel();

}
