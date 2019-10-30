package com.example.appsend;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import android.view.Window;

import com.sssoft.drivers.pos.aidl.XposDriverInc;


public class ServiceUtil {
	private static XposDriverInc xposDriverService;
	private static final String BIND_ACTION = "com.sssoft.drivers.pos.service";
	synchronized public static void startServiceConnect(Context mContext){
 		Intent it = new Intent();
        it.setAction(BIND_ACTION);
        it.setPackage("com.sssoft.drivers.pos.service");
        mContext.bindService(it, mserviceConnection, mContext.BIND_AUTO_CREATE);
	}
	public static ServiceConnection mserviceConnection = new ServiceConnection(){
        
        @Override
        public void onServiceDisconnected(ComponentName name){

        }
        
        @Override
        public void onServiceConnected(ComponentName name, IBinder service)
        {
            xposDriverService = XposDriverInc.Stub.asInterface(service);

        }
    };
	public static XposDriverInc getXposDriverService() {
		return xposDriverService;
	}
}
