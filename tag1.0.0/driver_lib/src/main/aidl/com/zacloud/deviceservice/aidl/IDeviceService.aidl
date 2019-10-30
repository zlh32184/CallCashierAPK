package com.zacloud.deviceservice.aidl;

import com.zacloud.deviceservice.aidl.IBeeper;
import com.zacloud.deviceservice.aidl.ILed;
import com.zacloud.deviceservice.aidl.ISerialPort;
import com.zacloud.deviceservice.aidl.IScanner;
import com.zacloud.deviceservice.aidl.IMagCardReader;
import com.zacloud.deviceservice.aidl.IInsertCardReader;
import com.zacloud.deviceservice.aidl.IRFCardReader;
import com.zacloud.deviceservice.aidl.IPinpad;
import com.zacloud.deviceservice.aidl.IPrinter;
import com.zacloud.deviceservice.aidl.IPBOC;
import com.zacloud.deviceservice.aidl.IDeviceInfo;


/**
 * 设备服务对象，提供终端各外设对象的服务接口
 */
interface IDeviceService {

    /**
     * 获取蜂鸣器操作对象
     * @return IBeeper对象，参见IBeeper.aidl类
     */
    IBinder getBeeper();

    /**
     * 获取LED操作对象
     * @return ILed对象，参见ILed.aidl类
     */
    IBinder getLed();

    /**
     * 获取串口操作对象
     * @return ISerialPort对象，参见ISerialPort.aidl类
     */
    IBinder getSerialPort();

    /**
     * 获取扫码器操作对象
     * @param cameraId - 0 后置扫码器，1 前置扫码器
     * @return IScanner对象，参见IScanner.aidl类
     */
    IBinder getScanner(int cameraId);

    /**
     * 获取磁条卡操作对象
     * @return IMagCardReader对象，参见IMagCardReader.aidl类
     */
    IBinder getMagCardReader();

    /**
     * 获取IC卡操作对象
     * @return IInsertCardReader对象，参见IInsertCardReader.aidl类
     */
    IBinder getInsertCardReader();
    
    /**
     * 获取RF卡操作对象
     * @return IRFCardReader对象，参见IRFCardReader.aidl类
     */
    IBinder getRFCardReader();
    
    /**
     * 获取密码键盘操作对象
     * @param kapId - 密码键盘索引，每个kapId对应一个逻辑密码键盘
     * @return IPinpad对象，参见IPinpad.aidl类
     */
    IBinder getPinpad(int kapId);
    
    /**
     * 获取打印机操作对象
     * @return IPrinter对象，参见IPrinter.aidl类
     */
    IBinder getPrinter();
    
    /**
     * 获取PBOC(EMV)流程操作对象
     * @return IPBOC对象，参见IPBOC.aidl类
     */
    IBinder getPBOC();

    /**
     * 获取设备信息操作对象
     * @return IDeviceInfo对象，参见IDeviceInfo.aidl类
     */
    IBinder getDeviceInfo();
}
