/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: F:\\Test\\CallCashierAPK\\cashier_driver_lib\\src\\main\\aidl\\com\\zacloud\\deviceservice\\aidl\\IBeeper.aidl
 */
package com.zacloud.deviceservice.aidl;
/**
 * 蜂鸣器对象
 */
public interface IBeeper extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.zacloud.deviceservice.aidl.IBeeper
{
private static final java.lang.String DESCRIPTOR = "com.zacloud.deviceservice.aidl.IBeeper";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.zacloud.deviceservice.aidl.IBeeper interface,
 * generating a proxy if needed.
 */
public static com.zacloud.deviceservice.aidl.IBeeper asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.zacloud.deviceservice.aidl.IBeeper))) {
return ((com.zacloud.deviceservice.aidl.IBeeper)iin);
}
return new com.zacloud.deviceservice.aidl.IBeeper.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
java.lang.String descriptor = DESCRIPTOR;
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(descriptor);
return true;
}
case TRANSACTION_startBeep:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
this.startBeep(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_stopBeep:
{
data.enforceInterface(descriptor);
this.stopBeep();
reply.writeNoException();
return true;
}
default:
{
return super.onTransact(code, data, reply, flags);
}
}
}
private static class Proxy implements com.zacloud.deviceservice.aidl.IBeeper
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
/**
	 * 开始蜂鸣
	 * 通过调用该方法可以让POS的蜂鸣器持续鸣叫msec毫秒。如果用户设定msec为0则不进行鸣叫。
	 * 蜂鸣器发声是采用非阻塞方式进行的，调用该函数之后，程序立即退出，不会停留在该函数。
	 * @param msec - 需要鸣叫的时间，单位ms
	 */
@Override public void startBeep(int msec) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(msec);
mRemote.transact(Stub.TRANSACTION_startBeep, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
	 * 停止蜂鸣
	 * 调用该方法后立即停止鸣叫
	 */
@Override public void stopBeep() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_stopBeep, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_startBeep = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_stopBeep = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
}
/**
	 * 开始蜂鸣
	 * 通过调用该方法可以让POS的蜂鸣器持续鸣叫msec毫秒。如果用户设定msec为0则不进行鸣叫。
	 * 蜂鸣器发声是采用非阻塞方式进行的，调用该函数之后，程序立即退出，不会停留在该函数。
	 * @param msec - 需要鸣叫的时间，单位ms
	 */
public void startBeep(int msec) throws android.os.RemoteException;
/**
	 * 停止蜂鸣
	 * 调用该方法后立即停止鸣叫
	 */
public void stopBeep() throws android.os.RemoteException;
}
