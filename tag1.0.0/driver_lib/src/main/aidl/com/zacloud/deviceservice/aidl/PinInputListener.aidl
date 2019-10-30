package com.zacloud.deviceservice.aidl;

/**
 * PIN输入过程监听器
 */
interface PinInputListener {

    /**
     * 按键按压事件
     * @param len - 已输入密码长度
     * @param key - 当前的Key值(预留给物理键盘使用)
     */
    void onInput(int len, int key);

    /**
     * 确认PIN输入时调用
     * @param data - PIN密文，输入为空时为null
     * @param isNonePin - 输入为空的时候为true
     */
    void onConfirm(in byte[] data, boolean isNonePin);

    /**
     * 取消PIN输入时调用
     */
    void onCancel();

    /**
     * 错误时回调
     * @param errorCode - 错误码
     */
    void onError(int errorCode);

}
