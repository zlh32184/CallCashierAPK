package com.zacloud.deviceservice.aidl;

/**
 * 串口设备对象
 * 实现串口数据收发功能。
 */
interface ISerialPort {

	/**
	 * 打开串口
	 * @return 成功true，失败false
	 */
	boolean open();

	/**
	 * 关闭串口
	 * @return 成功true，失败false
	 */
	boolean close();

	/**
	 * 初始化串口
	 * @param bps	- 波特率
	 * <ul>
     * <li>BPS_1200(0x01) - 1200波特率</li>
     * <li>BPS_2400(0x02) - 2400波特率</li>
     * <li>BPS_4800(0x03) - 4800波特率</li>
     * <li>BPS_9600(0x04) - 9600波特率</li>
     * <li>BPS_14400(0x05) - 14400波特率</li>
     * <li>BPS_28800(0x06) - 28800波特率</li>
     * <li>BPS_19200(0x07) - 19200波特率/li>
     * <li>BPS_57600(0x08) - 57600波特率</li>
     * <li>BPS_115200(0x09) - 115200波特率(缺省)</li>
     * <li>BPS_38400(0x0A) - 38400波特率</li>
     * </ul>
	 * @param par - 校验
	 * <ul>
     * <li>PAR_NOPAR('N') - 无校验(缺省)</li>
     * <li>PAR_EVEN('E') - 偶校验</li>
     * <li>PAR_ODD('O') - 奇校验</li>
     * </ul>
	 * @param dbs - 数据位
	 * <ul>
     * <li>DBS_7(0x07) - 7位数据位</li>
     * <li>DBS_8(0x08) - 8位数据位(缺省)</li>
     * </ul>
	 * @return 成功true，失败false
	 */
	boolean init(int bps, int par, int dbs);

	/**
	 * 读数据(接收)
	 * @param buffer - 缓冲区
	 * @param timeout - 超时时间，毫秒
	 * @return 返回实际读取的数据长度，失败返回-1
	 */
	int read(inout byte[] buffer, int timeout);

    /**
	 * 写数据(发送)
	 * @param data - 要发送的数据
	 * @param timeout - 超时时间，毫秒
	 * @return 返回实际写入的数据长度，失败返回-1
	 */
	int write(in byte[] data, int timeout);

	/**
	 * 清除接收缓冲区
	 * @return 成功true，失败false
	 */
	boolean clearInputBuffer();

	/**
	 * 查看缓冲区是否为空
	 * @param input - true为输入缓冲区，false为输出缓冲区
	 * @return 成功true，失败false
	 */
	boolean isBufferEmpty(boolean input);
}
