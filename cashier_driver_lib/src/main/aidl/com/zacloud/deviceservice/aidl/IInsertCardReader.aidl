package com.zacloud.deviceservice.aidl;

/**
 * 接触式IC读卡器对象
 */
interface IInsertCardReader {

	/**
	 * 卡上电
	 * @return 上电成功true，失败false。
	 */
	boolean powerUp();

	/**
	 * 卡下电
	 * @return 关闭成功true，失败false。
	 */
	boolean powerDown();

	/**
	 * 卡片是否在位
	 * @return 卡在位true，卡不在位false。
	 */
	boolean isCardIn();

	/**
	 * APDU数据通讯
	 * @param apdu - apdu数据
	 * @return 成功返回卡片应答数据，失败返回null。
	 */
	byte[] exchangeApdu(in byte[] apdu);
}
