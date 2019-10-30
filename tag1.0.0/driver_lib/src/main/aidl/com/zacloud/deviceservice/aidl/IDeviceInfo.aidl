package com.zacloud.deviceservice.aidl;

/**
 * 终端设备信息对象
 */
interface IDeviceInfo {

	/**
	 * 获取终端序列号
	 * @return 终端设备序列号
	 */
	String getSerialNo();

	/**
	 * 获取终端IMSI号
	 * @return 终端IMSI号
	 */
	String getIMSI();

	/**
	 * 获取终端IMEI号
	 * @return 终端IMEI号
	 */
	String getIMEI();

	/**
	 * 获取SIM卡ICCID
	 * @return 终端SIM卡ICCID
	 */
	String getICCID();

	/**
	 * 获取厂商名称
	 * @return 终端厂商名称
	 */
	String getManufacture();

	/**
	 * 获取终端设备型号
	 * @return 终端设备型号
	 */
	String getModel();

	/**
	 * 获取终端Android操作系统版本
	 * @return 终端Android系统版本
	 */
	String getAndroidOSVersion();

	/**
	 * 获取终端Android内核版本
	 * @return 终端Android内核版本
	 */
	String getAndroidKernelVersion();

	/**
	 * 获取终端ROM版本
	 * @return 终端系统ROM版本
	 */
	String getROMVersion();

	/**
	 * 获取终端固件版本
	 * @return 终端固件版本
	 */
	String getFirmwareVersion();

	/**
	 * 获取终端硬件版本
	 * @return 终端硬件版本
	 */
	String getHardwareVersion();

	/**
	 * 更新终端系统时间
	 * @param date - 日期，格式yyyyMMdd
	 * @param time - 时间，格式HHmmss
	 * @return 更新成功返回true，失败返回false
	 **/
	boolean updateSystemTime(String date, String time);

}
