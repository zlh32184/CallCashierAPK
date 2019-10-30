package com.zacloud.deviceservice.aidl;

import com.zacloud.deviceservice.aidl.PinInputListener;
import com.zacloud.deviceservice.aidl.TusnData;

/**
 * 密码键盘对象
 * 实现金融交易过程中密钥管理、数据加密和PIN输入处理过程。
 */
interface IPinpad {

	/**
	 * 判断密钥是否存在(密钥已下载)
	 * @param keyType - 密钥类型
	 * <ul>
	 * <li>0-主密钥</li>
	 * <li>1-MAC密钥</li>
	 * <li>2-PIN密钥</li>
	 * <li>3-TD密钥</li>
	 * </ul>
	 * @param keyId - 密钥ID
	 * @return 存在返回true，不存在返回false
	 */
	boolean isKeyExist(int keyType, int keyId);

    /**
	 * 下装TEK密钥
	 * TEK是加密主密钥的密钥
	 * @param key - 密钥
	 * @param checkValue - 校验值
	 * @return 成功true，失败false
	 */
	boolean loadTEK(in byte[] key, in byte[] checkValue);

	/**
	 * 下装密文主密钥
	 * @param keyId - 下装密钥存储ID
	 * @param key - 密钥
	 * @param checkValue - 校验值
	 * @return 成功true，失败false
	 */
	boolean loadEncryptMainKey(int keyId, in byte[] key, in byte[] checkValue);

	/**
	 * 下装明文主密钥
	 * @param keyId - 下装密钥存储ID
	 * @param key - 密钥
	 * @param checkValue - 校验值
	 * @return 成功true，失败false
	 */
	boolean loadMainKey(int keyId, in byte[] key, in byte[] checkValue);

	/**
	 * 下装工作密钥
	 * @param keyType - 下装工作密钥类型，1-MAC密钥，2-PIN密钥，3-TD密钥
	 * @param mkId - 解密工作密钥的主密钥ID
	 * @param wkId - 下装密钥存储ID
	 * @param key - 密钥
	 * @param checkValue - 校验值
	 * @return 成功true，失败false
	 */
	boolean loadWorkKey(int keyType, int mkId, int wkId, in byte[] key, in byte[] checkValue);

	/**
	 * 计算MAC值
	 * @param keyId - MAC密钥索引
	 * @param data - 计算MAC的数据
	 * @return 成功返回mac值，失败返回null
	 */
	byte[] calcMAC(int keyId, in byte[] data);

	/**
	 * 加密磁道数据
	 * @param mode - 加密模式，0-ECB，1-CBC
	 * @param keyId - TDK密钥索引
	 * @param trkData - 待加密磁道数据
	 * @return 成功返回加密后的磁道数据，失败返回null
	 */
	byte[] encryptTrackData(int mode, int keyId, in byte[] trkData);

	/**
     * 获取终端唯一序列号及其密文
     * @param mode - 模式，预留参数，固定填0
     * @param input - 加密随机因子，允许范围4-10字节
     * @return 成功返回TUSN数据，失败返回null
     */
	TusnData getTUSN(int mode, in byte[] input);

    /**
     * 启动PIN输入
     * @param keyId - PIN密钥索引
     * @param param - PIN输入配置参数
     * <ul>
     * <li>pinLimit(byte[]) - 允许输入密码的长度</li>
     * <li>timeout(int) - 输入超时时间，单位秒</li>
     * <li>isOnline(boolean) - 是否联机PIN</li>
     * <li>panBlock(byte[]) - 用于加密联机PIN的PAN BLOCK</li>
     * </ul>
     * @param listener - PIN输入过程监听器
     */
	void startPinInput(int keyId, in Bundle param, PinInputListener listener);

    /**
	 * 取消PIN输入过程
	 */
    void stopPinInput();

    /**
     * 获取最近一次操作的错误
     * @return 错误描述
     */
	String getLastError();

    /**
     * 设置密钥的算法类型
     * @param keyAlgorithm - 算法类型，见KeyAlgorithm常量定义
     * @see KeyAlgorithm定义
     */
    void setKeyAlgorithm(char keyAlgorithm);

    /**
     * 是否使用国密算法
     * @return 是返回true，否返回false
     */
    boolean isSM4Enabled();

}
