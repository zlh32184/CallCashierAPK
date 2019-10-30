package com.sssoft.drivers.pos.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.SurfaceView;

import com.jl.Ddi;
import com.landicorp.android.eptapi.DeviceService;
import com.landicorp.android.eptapi.exception.ReloginException;
import com.landicorp.android.eptapi.exception.RequestException;
import com.landicorp.android.eptapi.exception.ServiceOccupiedException;
import com.landicorp.android.eptapi.exception.UnsupportMultiProcess;
import com.newland.me.ConnUtils;
import com.newland.me.DeviceManager;
import com.newland.mtype.ConnectionCloseEvent;
import com.newland.mtype.Device;
import com.newland.mtype.event.DeviceEventListener;
import com.newland.mtypex.nseries.NSConnV100ConnParams;
import com.sssoft.drivers.pos.impl.hisense.hi98.Hi98AllCardSample;
import com.sssoft.drivers.pos.impl.hisense.hi98.Hi98CardSample;
import com.sssoft.drivers.pos.impl.hisense.hi98.Hi98PrinterSample;
import com.zacloud.deviceservice.aidl.IDeviceService;

public class AIDLService
{	
	//标识
	public static String isBnak = "";
	//hisense
	private static Hi98PrinterSample hiPrinterSample;
	private static Hi98AllCardSample hiAllCardSample;
	private static Hi98CardSample hiCardSample;
	//新大陆
	private static final String K21_DRIVER_NAME = "com.newland.me.K21Driver";
	private static DeviceManager deviceManager ;
	private static Device device;
	private static SurfaceView surfaceView;
    //联迪
    //惠尔丰
    private static IDeviceService deviceService;
    private static MyConnection myConnection = new MyConnection();
    //艾体威尔
    private static com.icbc.smartpos.deviceservice.aidl.IDeviceService deviceServiceA90;
    //九磊S500
    public static Ddi ddi;
    
    private static String model = android.os.Build.MODEL;
    private static Context mContext;
    
	public static Context getmContext() {
		return mContext;
	}
	public static void init(Context context){
		mContext = context;
		isBnak = "";
		if(model.startsWith("APOS")){
    		lanInitDevice();
    	}else if(model.startsWith("N")){
    		newInitDevice();
    	}else if(model.startsWith("HI")){
    		hiInitDevice();
    	}else if(model.startsWith("X")){
    		bindDeviceService();
    	}else if(model.startsWith("A90")){
    		bindDeviceService();
    	}else if(model.startsWith("S500")){
    		jollyInitDevice();
    	}
	}
	public static void init(Context context,String bankType){
		isBnak = bankType;
		BankService.bankInit(context, bankType);
	}
    private static void jollyInitDevice(){
    	ddi = new Ddi();
    	ddi.ddi_ddi_sys_init();
    }
    
    private static void hiInitDevice(){
    	hiPrinterSample = new Hi98PrinterSample();
    	hiAllCardSample = new Hi98AllCardSample();
    	hiCardSample = new Hi98CardSample();
    }
    //联迪初始化设备 
    private static void lanInitDevice() {
    	bindDeviceService();		
	}

    // 新大陆初始化设备
  	public static void newInitDevice(){ 			
  		bindDeviceService();
  	}


  	//绑定服务
  	public static void bindDeviceService() {
  		if(model.startsWith("APOS")){
    			try {
    				DeviceService.login(mContext);
    			} catch (RequestException e) {
    				e.printStackTrace();
    			} catch (ServiceOccupiedException e) {
    				e.printStackTrace();
    			} catch (ReloginException e) {
    				e.printStackTrace();
    			} catch (UnsupportMultiProcess e) {
    				e.printStackTrace();
    			}
    	}else if(model.startsWith("N")){
    		deviceManager = ConnUtils.getDeviceManager();
    		deviceManager.init(mContext, K21_DRIVER_NAME, new NSConnV100ConnParams(),  new DeviceEventListener<ConnectionCloseEvent>() {
				@Override
				public void onEvent(ConnectionCloseEvent event, Handler handler) {
					if (event.isSuccess()) {
					}
					if (event.isFailed()) {
					}
				}
				@Override
				public Handler getUIHandler() {
					return null;
				}
			});
			try {
				deviceManager.connect();
				device = deviceManager.getDevice();
				device.setBundle(new NSConnV100ConnParams());
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}else if(model.startsWith("X")){
    		myConnection = new MyConnection();
    		Intent intent = new Intent();  
    	    intent.setAction("com.zacloud.device_service");
    	    intent.setPackage("com.zacloud.deviceservice");
    	    mContext.bindService(intent, myConnection, Context.BIND_AUTO_CREATE);
    	}else if(model.startsWith("A90")){
    		Intent intent = new Intent();  
    	    intent.setAction("com.icbc.smartpos.device_service");
    	    intent.setPackage("com.icbc.smartpos.deviceservice");
    	    mContext.bindService(intent, A90Connection, Context.BIND_AUTO_CREATE);
    	}
  	}
  	public static ServiceConnection A90Connection = new ServiceConnection(){
        
        @Override
        public void onServiceDisconnected(ComponentName name){
        	deviceServiceA90 = null;
        }
        
        @Override
        public void onServiceConnected(ComponentName name, IBinder service)
        {
        	deviceServiceA90 = com.icbc.smartpos.deviceservice.aidl.IDeviceService.Stub.asInterface(service);
        }
    };
  	//解绑服务
  	public static void unBindDeviceService() {
  		if(AIDLService.isBnak.equals("")){
	  		if(model.startsWith("N")){
	  			try {
					if (deviceManager != null) {
						deviceManager.disconnect();				
						deviceManager = null;
					}
				} catch (Exception e) {
					
				}
	  		}
	  		if(model.startsWith("APOS")){
	  			DeviceService.logout();
	  		}
	  		if(model.startsWith("X")){
	  			if(deviceService!=null)
	  				mContext.unbindService(myConnection);
	  		}
	  		if(model.startsWith("A90")){
	  			if(deviceServiceA90!=null)
	  				mContext.unbindService(A90Connection);
	  		}
  		}else{
  			BankService.unBindDeviceService();
  		}
  	}
  	public static class MyConnection implements ServiceConnection
    {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder)
        {
            deviceService = IDeviceService.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName)
        {
            deviceService = null;
        }
    }
	public static Device getDevice() {
		return device;
	}
	public static void setDevice(Device device) {
		AIDLService.device = device;
	}
	public static Hi98PrinterSample getHiPrinterSample() {
		return hiPrinterSample;
	}
	public static void setHiPrinterSample(Hi98PrinterSample hiPrinterSample) {
		AIDLService.hiPrinterSample = hiPrinterSample;
	}
	public static Hi98AllCardSample getHiAllCardSample() {
		return hiAllCardSample;
	}
	public static void setHiAllCardSample(Hi98AllCardSample hiAllCardSample) {
		AIDLService.hiAllCardSample = hiAllCardSample;
	}
	public static Hi98CardSample getHiCardSample() {
		Log.i("dj","getHiCardSample");
		return hiCardSample;
	}
	public static void setHiCardSample(Hi98CardSample hiCardSample) {
		AIDLService.hiCardSample = hiCardSample;
	}

	public static IDeviceService getDeviceService() {
		return deviceService;
	}
	public static void setDeviceService(IDeviceService deviceService) {
		AIDLService.deviceService = deviceService;
	}

	public static com.icbc.smartpos.deviceservice.aidl.IDeviceService getDeviceServiceA90() {
		return deviceServiceA90;
	}

	public static void setDeviceServiceA90(
			com.icbc.smartpos.deviceservice.aidl.IDeviceService deviceServiceA90) {
		AIDLService.deviceServiceA90 = deviceServiceA90;
	}
	
}
