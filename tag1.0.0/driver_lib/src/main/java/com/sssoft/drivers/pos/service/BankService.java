package com.sssoft.drivers.pos.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

import com.ccb.deviceservice.aidl.IDeviceService;

public class BankService {
	private static Context mContext;
    private static IDeviceService deviceService;
    private static final String ACTION_DEVICE_SERVICE = "com.ccb.device_service";
	private static final String PACKAGE_DEVICE_SERVICE = "com.ccb.deviceservice";
	public static Context getmContext() {
		return mContext;
	}
	
	public static IDeviceService getDeviceService() {
		return deviceService;
	}

	public static void setDeviceService(IDeviceService deviceService) {
		BankService.deviceService = deviceService;
	}
	public static void bankInit(Context context,String bankType){
		mContext = context;
		switch (bankType) {
		case "CCB":
			Intent service = new Intent(ACTION_DEVICE_SERVICE);
			service.setPackage(PACKAGE_DEVICE_SERVICE);
			mContext.bindService(service, ccbConnection, Context.BIND_AUTO_CREATE);
			break;

		default:
			break;
		}
	}
	public static ServiceConnection ccbConnection = new ServiceConnection(){
        
        @Override
        public void onServiceDisconnected(ComponentName name){
        	deviceService = null;
        }
        
        @Override
        public void onServiceConnected(ComponentName name, IBinder service)
        {
        	deviceService = IDeviceService.Stub.asInterface(service);
        	login();
        }
    };
    public static void login() {
		try {
			boolean ret = deviceService.lock(null, new Binder());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
    public static void logout() {
		try {
			deviceService.unlock();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
    public static void unBindDeviceService() {
    	switch (AIDLService.isBnak) {
		case "CCB":
			if(deviceService!=null){
				logout();
				mContext.unbindService(ccbConnection);
			}
		default:
			
		}
    }
}
