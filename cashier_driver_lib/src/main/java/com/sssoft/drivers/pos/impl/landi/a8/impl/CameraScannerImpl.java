package com.sssoft.drivers.pos.impl.landi.a8.impl;

import android.app.Activity;
import android.content.Context;
import android.os.RemoteException;
import android.util.Log;

import com.landicorp.android.scan.scanDecoder.ScanDecoder;
import com.sssoft.drivers.pos.aidl.ScannerListener;
import com.sssoft.drivers.pos.impl.landi.a8.err.CameraScannerError;
import com.sssoft.drivers.pos.impl.landi.a8.err.Constants;

/**
 * 使用摄像头进行扫码。A8终端有前后置摄像可进行前后置不同的扫码，对于诸如A7/W280P/C10等终端，
 * 因只有后置，故只能使用后置扫码功能。另外可进行扫码的前提条件是终端已集成扫码库。
 */

public class CameraScannerImpl{
	private Context mContext;
	private ScannerListener mListener;
    private ScanDecoder scanDecoder;
    private ScanDecoder.ResultCallback callback = new ScanDecoder.ResultCallback() {
        @Override
        public void onResult(String s) {
        	try {
				mListener.onScanResult(s);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	close();
        }

        @Override
        public void onCancel() {
        	try {
				mListener.onError("X0", "取消扫描");
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	close();
        }

        @Override
        public void onTimeout() {
        	try {
				mListener.onError("X0", "扫描超时");
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	close();
        }
    };

    public CameraScannerImpl(Context context) {
        scanDecoder = new ScanDecoder(context);
        mContext = context;
    }

    private int openCamera(int cameraId) {
        int id = ScanDecoder.CAMERA_ID_FRONT;
        if (cameraId == Constants.Scanner.CAMERA_BACK) {
            id = ScanDecoder.CAMERA_ID_BACK;
        }
        return scanDecoder.Create(id, callback);
    }

    public void startScan(int scanType, int timeout, final ScannerListener listener) {
    	mListener = listener;
    	int ret = openCamera(scanType-1);
        if (ret != CameraScannerError.SUCCESS) {
            //displayInfo("open camera fail: " + getDescribe(ret));
        	Log.i("cheny", getDescribe(ret)+"111");
        } else {
            ret = scanDecoder.startScanDecode((Activity)mContext, null);
            if (ret != CameraScannerError.SUCCESS) {
                String errorDes = getDescribe(ret);
                //displayInfo("start scan fail: " + errorDes);
                Log.i("cheny", errorDes);
            }
        }
    }

    public void close() {
        scanDecoder.Destroy();
    }

    private String getDescribe(int error) {
        switch (error) {
            case CameraScannerError.INIT_DECODER_FAIL:
                return "init decoder failed";
            case CameraScannerError.HAS_CREATED:
                return "it created before";
            case CameraScannerError.OPEN_CAMERA_FAIL:
                return "open camera failed";
            case CameraScannerError.LICENSE_FAIL:
                return "license certified failed";
            case CameraScannerError.NOT_FOUND_DECODRE:
                return "not found decoder";
            default:
                return "unknown error";
        }
    }
}
