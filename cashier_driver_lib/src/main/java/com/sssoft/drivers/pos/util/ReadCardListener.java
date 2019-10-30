package com.sssoft.drivers.pos.util;

/**
 * Created by XC on 2018/2/26.
 */
/**
 * 读卡监听器
 */
public interface ReadCardListener {
    //读卡失败，通过回调监听返回错误码和错误信息
    void onError(int errorCode, String message);

    //读卡成功，通过回调监听返回卡号
    void onSuccess(String cardNo);
}
