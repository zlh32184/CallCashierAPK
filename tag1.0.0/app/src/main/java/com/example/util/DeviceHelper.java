package com.example.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import com.ccb.deviceservice.aidl.IDeviceService;
import com.ccb.deviceservice.aidl.beeper.IBeeper;
import com.ccb.deviceservice.aidl.cashbox.ICashBox;
import com.ccb.deviceservice.aidl.deviceinfo.IDeviceInfo;
import com.ccb.deviceservice.aidl.idcardreader.IIDCardReader;
import com.ccb.deviceservice.aidl.insertreader.IInsertCardReader;
import com.ccb.deviceservice.aidl.led.ILed;
import com.ccb.deviceservice.aidl.magreader.IMagCardReader;
import com.ccb.deviceservice.aidl.paramfile.IParamFile;
import com.ccb.deviceservice.aidl.pboc.IPBOC;
import com.ccb.deviceservice.aidl.pedestal.IPedestal;
import com.ccb.deviceservice.aidl.pinpad.IPinpad;
import com.ccb.deviceservice.aidl.printer.IPrinter;
import com.ccb.deviceservice.aidl.psamcardreader.IPsamCardReader;
import com.ccb.deviceservice.aidl.rfreader.IRFCardReader;
import com.ccb.deviceservice.aidl.scanner.IScanner;
import com.ccb.deviceservice.aidl.serialport.ISerialPort;
import com.ccb.deviceservice.aidl.signpanel.ISignPanel;

/**
 * �豸�������࣬ʵ�����豸��������ӣ����ṩ���ʸ��豸�Ľӿڡ�
 * @author baoxl
 *
 */
public final class DeviceHelper implements IBinder.DeathRecipient {
	private static final String TAG = DeviceHelper.class.getSimpleName();
	
	private static final String ACTION_DEVICE_SERVICE = "com.ccb.device_service";
	private static final String PACKAGE_DEVICE_SERVICE = "com.ccb.deviceservice";

	private static DeviceHelper sInstance = new DeviceHelper();
	
	private Context mContext;
	private boolean isBindService;
	private IDeviceService mDeviceService;
	
	private ServiceConnection mConnection = new ServiceConnection() {
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			isBindService = true;
			mDeviceService = IDeviceService.Stub.asInterface(service);
			Log.d(TAG, "-------------onServiceConnected----------");
			login();
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			isBindService = false;
			Log.d(TAG, "-------------onServiceDisconnected----------");
		}

	};
	
	public static DeviceHelper getInstance() {
		return sInstance;
	}
	
	public void init(Context context) {
		mContext = context;
	}
	
	public void bindService() {
		if (!isBindService) {
			Intent service = new Intent(ACTION_DEVICE_SERVICE);
			service.setPackage(PACKAGE_DEVICE_SERVICE);
			boolean bindSucc = mContext.bindService(service, mConnection, Context.BIND_AUTO_CREATE);
			if (bindSucc) {
				Log.d(TAG, "-------------bind service success----------");
			} else {
				Log.e(TAG, "-------------bind service failed----------");
			}
		}
	}
	
	public void unbindService() {
		if (isBindService) {
			Log.d(TAG, "-------------unbind service success----------");
			isBindService = false;
//			mDeviceService.asBinder().unlinkToDeath(this, 0);
			mContext.unbindService(mConnection);
			logout();
		}
	}
	
	public void login() {
		try {
//			boolean ret = mDeviceService.lock(null, new Binder());
			boolean ret = mDeviceService.lock(null, new Binder());
			Log.d(TAG, "/// lock " + (ret ? "success" : "fail"));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void logout() {
		try {
			mDeviceService.unlock();
//			try {
//				Thread.sleep(2000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
		} catch (RemoteException e) {
			Log.e(TAG, "unBindDeviceService | RemoteException occured, message = " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public IDeviceService getDeviceService() {
		return mDeviceService;
	}
	
	public IBeeper getBeeper() {
		try {
			IBinder binder = mDeviceService.getBeeper();
			return IBeeper.Stub.asInterface(binder);
		} catch (RemoteException e) {
			e.printStackTrace();
			Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_LONG).show();
			return null;
		} catch (SecurityException e) {
			e.printStackTrace();
			Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_LONG).show();
			Log.d(TAG, "-------------------getBeeper SecurityException---------------");
			return null;
		}
	}
	
	public IDeviceInfo getDeviceInfo() {
		try {
			IBinder binder = mDeviceService.getDeviceInfo();
			return IDeviceInfo.Stub.asInterface(binder);
		} catch (RemoteException e) {
			e.printStackTrace();
			Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_LONG).show();
			return null;
		} catch (SecurityException e) {
			e.printStackTrace();
			Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_LONG).show();
			Log.d(TAG, "-------------------getDeviceInfo SecurityException---------------");
			return null;
		}
	}
	
	public IMagCardReader getMagCardReader() {
		try {
			IBinder binder = mDeviceService.getMagCardReader();
			return IMagCardReader.Stub.asInterface(binder);
		} catch (RemoteException e) {
			e.printStackTrace();
			Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_LONG).show();
			return null;
		} catch (SecurityException e) {
			e.printStackTrace();
			Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_LONG).show();
			Log.d(TAG, "-------------------getMagCardReader SecurityException---------------");
			return null;
		}
	}
	
	public IInsertCardReader getInsertCardReader() {
		try {
			IBinder binder = mDeviceService.getInsertCardReader();
			return IInsertCardReader.Stub.asInterface(binder);
		} catch (RemoteException e) {
			e.printStackTrace();
			Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_LONG).show();
			return null;
		} catch (SecurityException e) {
			e.printStackTrace();
			Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_LONG).show();
			Log.d(TAG, "-------------------getInsertCardReader SecurityException---------------");
			return null;
		}
	}
	
	public IRFCardReader getRFCardReader() {
		try {
			IBinder binder = mDeviceService.getRFCardReader();
			return IRFCardReader.Stub.asInterface(binder);
		} catch (RemoteException e) {
			e.printStackTrace();
			Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_LONG).show();
			return null;
		} catch (SecurityException e) {
			e.printStackTrace();
			Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_LONG).show();
			Log.d(TAG, "-------------------getRFCardReader SecurityException---------------");
			return null;
		}
	}
	
	public IRFCardReader getRFCardReaderEx(String deviceName) {
		try {
			Bundle bundle = new Bundle();
			bundle.putString("deviceName", deviceName);
			IBinder binder = mDeviceService.getRFCardReaderEx(bundle);
			return IRFCardReader.Stub.asInterface(binder);
		} catch (RemoteException e) {
			e.printStackTrace();
			Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_LONG).show();
			return null;
		} catch (SecurityException e) {
			e.printStackTrace();
			Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_LONG).show();
			Log.d(TAG, "-------------------getRFCardReaderEx SecurityException---------------");
			return null;
		}
	}
	
	public ILed getLed() {
		try {
			IBinder binder = mDeviceService.getLed();
			return ILed.Stub.asInterface(binder);
		} catch (RemoteException e) {
			e.printStackTrace();
			Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_LONG).show();
			return null;
		} catch (SecurityException e) {
			e.printStackTrace();
			Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_LONG).show();
			Log.d(TAG, "-------------------getLed SecurityException---------------");
			return null;
		}
	}
	
	public ILed getLedEx(String deviceName) {
		try {
			Bundle bundle = new Bundle();
			bundle.putString("deviceName", deviceName);
			IBinder binder = mDeviceService.getLedEx(bundle);
			return ILed.Stub.asInterface(binder);
		} catch (RemoteException e) {
			e.printStackTrace();
			Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_LONG).show();
			return null;
		} catch (SecurityException e) {
			e.printStackTrace();
			Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_LONG).show();
			Log.d(TAG, "-------------------getLedEx SecurityException---------------");
			return null;
		}
	}
	
	public IPrinter getPrinter() {
		try {
			IBinder binder = mDeviceService.getPrinter();
			return IPrinter.Stub.asInterface(binder);
		} catch (RemoteException e) {
			e.printStackTrace();
			Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_LONG).show();
			return null;
		} catch (SecurityException e) {
			e.printStackTrace();
			Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_LONG).show();
			Log.d(TAG, "-------------------getPrinter SecurityException---------------");
			return null;
		}
	}
	
	public IScanner getScanner(int type) {
		try {
			IBinder binder = mDeviceService.getScanner(type);
			return IScanner.Stub.asInterface(binder);
		} catch (RemoteException e) {
			e.printStackTrace();
			Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_LONG).show();
			return null;
		} catch (SecurityException e) {
			e.printStackTrace();
			Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_LONG).show();
			Log.d(TAG, "-------------------getScanner SecurityException---------------");
			return null;
		}
	}
	
	public IPinpad getPinpad(int kapId) {
		try {
			IBinder binder = mDeviceService.getPinpad(kapId);
			return IPinpad.Stub.asInterface(binder);
		} catch (RemoteException e) {
			e.printStackTrace();
			Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_LONG).show();
			return null;
		} catch (SecurityException e) {
			e.printStackTrace();
			Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_LONG).show();
			Log.d(TAG, "-------------------getPinpad SecurityException---------------");
			return null;
		}
	}
	
	public IPinpad getPinpadEx(int kapId, String deviceName) {
		try {
			Bundle bundle = new Bundle();
			bundle.putInt("regionId", 0);
			bundle.putInt("kapId", kapId);
			bundle.putString("deviceName", deviceName);
			IBinder binder = mDeviceService.getPinpadEx(bundle);
			return IPinpad.Stub.asInterface(binder);
		} catch (RemoteException e) {
			e.printStackTrace();
			Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_LONG).show();
			return null;
		} catch (SecurityException e) {
			e.printStackTrace();
			Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_LONG).show();
			Log.d(TAG, "-------------------getPinpadEx SecurityException---------------");
			return null;
		}
	}
	
	public IPBOC getPBOC() {
		try {
			IBinder binder = mDeviceService.getPBOC();
			return IPBOC.Stub.asInterface(binder);
		} catch (RemoteException e) {
			e.printStackTrace();
			Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_LONG).show();
			return null;
		} catch (SecurityException e) {
			e.printStackTrace();
			Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_LONG).show();
			Log.d(TAG, "-------------------getPBOC SecurityException---------------");
			return null;
		}
	}
	
	public ISerialPort getSerialPort(String deviceName) {
		try {
			Bundle bundle = new Bundle();
			bundle.putString("deviceName", deviceName);
			IBinder binder = mDeviceService.getSerialPortEx(bundle);
			return ISerialPort.Stub.asInterface(binder);
		} catch (RemoteException e) {
			e.printStackTrace();
			Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_LONG).show();
			return null;
		} catch (SecurityException e) {
			e.printStackTrace();
			Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_LONG).show();
			Log.d(TAG, "-------------------getSerialPort SecurityException---------------");
			return null;
		}
	}
	
	public ICashBox getCashBox() {
		try {
			IBinder binder = mDeviceService.getCashBox(new Bundle());
			return ICashBox.Stub.asInterface(binder);
		} catch (RemoteException e) {
			e.printStackTrace();
			Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_LONG).show();
			return null;
		} catch (SecurityException e) {
			e.printStackTrace();
			Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_LONG).show();
			Log.d(TAG, "-------------------getCashBox SecurityException---------------");
			return null;
		}
	}
	
	public ISignPanel getSignPanel() {
		try {
			Bundle bundle = new Bundle();
			bundle.putString("deviceName", "USBH");
			IBinder binder = mDeviceService.getSignPanel(bundle);
			return ISignPanel.Stub.asInterface(binder);
		} catch (RemoteException e) {
			e.printStackTrace();
			Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_LONG).show();
			return null;
		} catch (SecurityException e) {
			e.printStackTrace();
			Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_LONG).show();
			Log.d(TAG, "-------------------getSignPanel SecurityException---------------");
			return null;
		}
	}
	
	public IPsamCardReader getPsamCardReader(int slot) {
		try {
			Bundle bundle = new Bundle();
			bundle.putInt("slot", slot);
			IBinder binder = mDeviceService.getPsamCardReader(bundle);
			return IPsamCardReader.Stub.asInterface(binder);
		} catch (RemoteException e) {
			e.printStackTrace();
			Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_LONG).show();
			return null;
		} catch (SecurityException e) {
			e.printStackTrace();
			Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_LONG).show();
			Log.d(TAG, "-------------------getPsamCardReader SecurityException---------------");
			return null;
		}
	}
	
	public IIDCardReader getIDCardReader() {
		try {
			Bundle bundle = new Bundle();
			IBinder binder = mDeviceService.getIDCardReader(bundle);
			return IIDCardReader.Stub.asInterface(binder);
		} catch (RemoteException e) {
			e.printStackTrace();
			Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_LONG).show();
			return null;
		} catch (SecurityException e) {
			e.printStackTrace();
			Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_LONG).show();
			Log.d(TAG, "-------------------getIDCardReader SecurityException---------------");
			return null;
		}
	}
	
	public IPedestal getPedstal() {
		try {
			Bundle bundle = new Bundle();
			IBinder binder = mDeviceService.getPedestal(bundle);
			return IPedestal.Stub.asInterface(binder);
		} catch (RemoteException e) {
			e.printStackTrace();
			Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_LONG).show();
			return null;
		} catch (SecurityException e) {
			e.printStackTrace();
			Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_LONG).show();
			Log.d(TAG, "-------------------getPedstal SecurityException---------------");
			return null;
		}
	}
	
	public IParamFile getParamFile(String moduleName, String fileName) {
		try {
			IBinder binder = mDeviceService.getParamFile(moduleName, fileName);
			return IParamFile.Stub.asInterface(binder);
		} catch (RemoteException e) {
			e.printStackTrace();
			Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_LONG).show();
			return null;
		} catch (SecurityException e) {
			e.printStackTrace();
			Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_LONG).show();
			Log.d(TAG, "-------------------getParamFile SecurityException---------------");
			return null;
		}
	}
	
	@Override
	public void binderDied() {
		Log.d(TAG, "-------------binder service is dead!!!----------");
		isBindService = false;
		mDeviceService = null;
		bindService();
	}
}
