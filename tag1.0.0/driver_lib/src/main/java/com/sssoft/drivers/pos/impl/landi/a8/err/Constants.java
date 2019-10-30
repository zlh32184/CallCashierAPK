package com.sssoft.drivers.pos.impl.landi.a8.err;

import com.landicorp.android.eptapi.device.RFCardReader;

/**
 * Created by Czl on 2017/7/27.
 */

public interface Constants {
    interface Device {
        /** 设备模块类型 */
        int DEVICE_MODULE_MAG_CARD = 0x00; // 磁条卡读卡器
        int DEVICE_MODULE_IC_CPU_CARD = 0x01; // 接触式cpu卡读卡器
        int DEVICE_MODULE_SYNC_CARD = 0x02; // 同步卡读卡器
        int DEVICE_MODULE_PSAM_CARD = 0x03; // psam卡读卡器
        int DEVICE_MODULE_RF_CPU_CARD = 0x04; // 非接触式cpu卡读卡器
        int DEVICE_MODULE_RF_M1_CARD = 0x05; // 非接触式m1卡读卡器
        int DEVICE_MODULE_ID_CARD = 0x06; // 二代证读卡器
        int DEVICE_MODULE_PRINTER = 0x07; // 打印机
        int DEVICE_MODULE_PINPAD = 0x08; // 密码键盘
        int DEVICE_MODULE_SERIALPORT = 0x09; // 串口
        int DEVICE_MODULE_CAMERA_SCANNER = 0x0A; // 摄像头扫码器
        int DEVICE_MODULE_INNERSCANNER = 0x0B; // 内置扫码器
        int DEVICE_MODULE_LED = 0x0C; // led灯
        int DEVICE_MODULE_BEEPER = 0x0D; // 蜂鸣器
        int DEVICE_MODULE_CASHBOX = 0x0E; // 钱箱
        int DEVICE_MODULE_MODEM = 0x0F; // modem
        int DEVICE_MODULE_C10_SUBSCREEN_DEVICE = 0x10; // C10双屏客屏操作接口
        int DEVICE_MODULE_SCANNER = 0x11; // 外接扫码枪
        int DEVICE_MODULE_ALGORITHM = 0x12; // 算法
        int DEVICE_MODULE_SYSTEM = 0x13; // 系统接口
        int DEVICE_MODULE_C10_DIGLED_DEVICE = 0x14; // C10单屏数码管操作接口
        int DEVICE_MODULE_PAR = 0x15; // 终端par参数文集按操作
        int DEVICE_MODULE_SIGNPANEL = 0x16; // 终端签名板操作接口
        int DEVICE_MODULE_FELICA_CARD = 0x17; // Felica卡读卡器

        /** 同步卡类型 */
        int MODULE_CARD_AT1604 = 0x20; // AT1604类型卡（包括AT101/AT102/AT1601/AT1604）
        int MODULE_CARD_AT1608 = 0x21; // AT1608卡
        int MODULE_CARD_AT24Cxx = 0x22; // AT24Cxx类型卡（包括AT101/AT102/AT1601/AT1604）
        int MODULE_CARD_SIM4428 = 0x23; // SIM4428卡
        int MODULE_CARD_SIM4442 = 0x24; // SIM4442卡（包括AT24C01/AT24C02/AT24C04/AT24C08/AT24C16/AT24C32/AT24C64）
    }

    interface Pinpad {
        String DEVICE_INNER = "IPP";
        String DEVICE_EXTERNAL = "COM_EPP";

        int REGION_ID = 0;
        int KAP_ID = 0;
        
        int MAIN_KEY_INDEX = com.landicorp.android.eptapi.pinpad.Pinpad.KEYOFFSET_MAINKEY;
        int MAC_KEY_INDEX = com.landicorp.android.eptapi.pinpad.Pinpad.KEYOFFSET_MACKEY;
        int PIN_KEY_INDEX = com.landicorp.android.eptapi.pinpad.Pinpad.KEYOFFSET_PINKEY;
        int TD_KEY_INDEX = com.landicorp.android.eptapi.pinpad.Pinpad.KEYOFFSET_TDKEY;
        int ENC_DEC_KEY_INDEX = TD_KEY_INDEX + 1;

        int KEYTYPE_MAIN_KEY = com.landicorp.android.eptapi.pinpad.Pinpad.KEYTYPE_MAIN_KEY;
        int KEYTYPE_MAC_KEY = com.landicorp.android.eptapi.pinpad.Pinpad.KEYTYPE_MAC_KEY;
        int KEYTYPE_PIN_KEY = com.landicorp.android.eptapi.pinpad.Pinpad.KEYTYPE_PIN_KEY;
        int KEYTYPE_TD_KEY = com.landicorp.android.eptapi.pinpad.Pinpad.KEYTYPE_TD_KEY;
        int KEYTYPE_ENC_DEC_KEY = com.landicorp.android.eptapi.pinpad.Pinpad.KEYTYPE_ENC_DEC_KEY;
    }

    interface SyncCard {
        /** 卡类型 */
        int CARD_TYPE_AT101 = 0x01;
        int CARD_TYPE_AT102 = 0x02;
        int CARD_TYPE_AT1601 = 0x03;
        int CARD_TYPE_AT1604 = 0x04;
        int CARD_TYPE_AT153 = 0x05;
        int CARD_TYPE_AT1608 = 0x06;
        int CARD_TYPE_AT24C01 = 0x07;
        int CARD_TYPE_AT24C02 = 0x08;
        int CARD_TYPE_AT24C04 = 0x09;
        int CARD_TYPE_AT24C08 = 0x0A;
        int CARD_TYPE_AT24C16 = 0x0B;
        int CARD_TYPE_AT24C32 = 0x0C;
        int CARD_TYPE_AT24C64 = 0x0D;
        int CARD_TYPE_SIM4428 = 0x0E;
        int CARD_TYPE_SIM4442 = 0x0F;
    }

    interface Scanner {
        int CAMERA_FRONT = 0x00;
        int CAMERA_BACK = 0x01;
    }

    interface SerialPort {
        String DEVICE_USBD = "USBD";
        String DEVICE_TTYACM = "ttyACM0";
    }

    interface RFCard {
        String DEVICE_INNER = "inner";
        String DEVICE_EXTERNAL = "external";

        String DRIVER_NAME_PRO = "PRO";
        String DRIVER_NAME_S50 = "S50";
        String DRIVER_NAME_S70 = "S70";
        String DRIVER_NAME_CPU = "CPU";

        /** 强制设置卡片类型 */
        int CARD_TYPE_S50 = RFCardReader.OnSearchListener.S50_CARD;
        int CARD_TYPE_S70 = RFCardReader.OnSearchListener.S70_CARD;
        int CARD_TYPE_PRO = RFCardReader.OnSearchListener.PRO_CARD;
    }

    interface Felica {
        String DEVICE_INNER = "USERCARD";
    }

    interface Led {
        String DEVICE_INNER = "inner";
        String DEVICE_EXTERNAL = "external";
    }

    interface DualScreen {
        /** 客屏不可点击 */
        int SCREEN_UNTOUCHABLE = 0;
        /** 客屏可点击 */
        int SCREEN_TOUCHABLE = 1;
        /** 客屏按钮已启用 */
        int BUTTON_ENABLE = 1;
        /** 客屏按钮已禁用 */
        int BUTTON_DISABLE = 0;
        /** 客屏没有焦点 */
        int SCREEN_UNFOCUS = 0;
        /** 客屏有聚焦 */
        int SCREEN_FOCUS = 1;
    }

    interface TerminalType {
        String MODEL_W280P = "W280V2";
        String MODEL_W280PV2 = "W280P";
        String MODEL_W280PV3 = "W280PV3";
        String MODEL_P950 = "P950";
        String MODEL_P960 = "P960";
        String MODEL_P990 = "P990";
        String MODEL_P990V2 = "P990V2";
        String MODEL_APOS_A8 = "APOS A8";
        String MODEL_AECR_C10 = "AECR C10";
    }

    interface ModelSupport {
        int SUPPORT_MODEL_ALL = 0xFFFF;
        int SUPPORT_MODEL_NONE = 0x0000;
        int SUPPORT_MODEL_W280P = 0x0001;
        int SUPPORT_MODEL_W280PV2 = 0x0002;
        int SUPPORT_MODEL_W280PV3 = 0x0004;
        int SUPPORT_MODEL_P950 = 0x0008;
        int SUPPORT_MODEL_P960 = 0x0010;
        int SUPPORT_MODEL_P990 = 0x0020;
        int SUPPORT_MODEL_P990V2 = 0x0040;
        int SUPPORT_MODEL_APOS_A8 = 0x0080;
        int SUPPORT_MODEL_AECR_C10 = 0x0100;
    }
}
