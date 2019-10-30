package com.zacloud.deviceservice.aidl;

import com.zacloud.deviceservice.aidl.RFSearchListener;

/**
 * 非接触式IC读卡器对象
 */
interface IRFCardReader {

	/**
	 * 非接寻卡上电
	 * @param listener - 寻卡监听器
	 */
	void searchCard(RFSearchListener listener);

	/**
	 * 停止寻卡操作
	 */
	void stopSearch();

	/**
	 * 卡片是否在位
	 * @return 卡在位true，卡不在位false。
	 */
	boolean isExist();

	/**
	 * 激活卡片
	 * @param driver - 驱动名称
	 * <ul>
     * <li>"S50" - S50卡</li>
     * <li>"S70" - S70卡</li>
     * <li>"CPU" - CPU卡</li>
     * <li>"PRO" - PRO、S50_PRO、S70_PRO卡</li>
	 * </ul>
	 * @param responseData - 激活卡片应答数据
	 * @return 返回0表示激活成功，其他失败。
	 */
	int activate(String driver, out byte[] responseData);

	/**
	 * 关闭设备，再次操作需从寻卡开始
	 */
	void halt();

	/**
	 * APDU数据通讯
	 * @param apdu - apdu数据
	 * @return 成功返回卡片应答数据。
	 */
	byte[] exchangeApdu(in byte[] apdu);

	/**
	 * 认证(根据块号)
	 * <ul>
	 * <li>对卡执行读写，修改的操作时必须先进行认证，而且同时只能认证一个扇区，在对不同的扇区进行操作时需要再做认证。</li>
	 * <li>该接口需要指定认证块的具体位置</li>
	 * </ul>
	 * @param blockNo - 块号，从0开始
	 * @param keyType - 密钥类型 KEY_A 或者 KEY_B
	 * @param key - 密钥，6字节长
	 * @return 成功0x00，其他失败
	 */
	int authBlock(int blockNo, int keyType, in byte[] key);

    /**
	 * 认证(根据扇区号)
	 * <ul>
	 * <li>对卡执行读写，修改的操作时必须先进行认证，而且同时只能认证一个扇区，在对不同的扇区进行操作时需要再做认证。</li>
	 * <li>该接口只需要指定要操作的扇区，会自动对该扇区的第3块(从0起)进行认证操作</li>
	 * </ul>
	 * @param sectorNo - 扇区号，从0开始
	 * @param keyType - 密钥类型 KEY_A 或者 KEY_B
	 * @param key - 密钥，6字节长
	 * @return 成功0x00，其他失败
	 */
	int authSector(int sectorNo, int keyType, in byte[] key);

	/**
	 * 读块数据
	 * @param blockNo - 块号
	 * @param data - 读取返回的块数据(16字节数据)
	 * @return 成功0x00，其他失败
	 */
	int readBlock(int blockNo, out byte[] data);

	/**
	 * 写块数据
	 * <ul>
	 * <li>必须是16个字节的数据，没有达到字节数将认为是错误。</li>
	 * </ul>
	 * @param blockNo - 块号
	 * @param data - 指定数据写到指定的块里
	 * @return 成功0x00，其他失败
	 */
	int writeBlock(int blockNo, in byte[] data);

	/**
	 * 加值
	 * 把指定的值加至一数值块中
	 * @param blockNo - 块号
	 * @param value - 数据
	 * @return 成功0x00，其他失败
	 */
	int increaseValue(int blockNo, int value);

	/**
	 * 减值
	 * 从一数值块中减去指定的值
	 * @param blockNo - 块号
	 * @param value - 数据
	 * @return 成功0x00，其他失败
	 */
	int decreaseValue(int blockNo, int value);

}
