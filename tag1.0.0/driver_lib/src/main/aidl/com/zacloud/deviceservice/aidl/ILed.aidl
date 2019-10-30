package com.zacloud.deviceservice.aidl;

/**
 * LED对象
 */
interface ILed {

	/**
	 * 点亮LED
	 * @param led - 灯标识
	 * <ul>
	 * <li>1 - 蓝灯</li>
	 * <li>2 - 橙灯</li>
	 * <li>3 - 黄灯</li>
	 * <li>4 - 红灯</li>
	 * </ul>
	 */
	void turnOn(int led);

	/**
	 * 关闭LED
	 * @param led - 灯标识
     * <ul>
     * <li>1 - 蓝灯</li>
     * <li>2 - 橙灯</li>
     * <li>3 - 黄灯</li>
     * <li>4 - 红灯</li>
     * </ul>
	 */
	void turnOff(int led);

}
