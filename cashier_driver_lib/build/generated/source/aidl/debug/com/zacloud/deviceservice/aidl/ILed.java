/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: F:\\Test\\CallCashierAPK\\cashier_driver_lib\\src\\main\\aidl\\com\\zacloud\\deviceservice\\aidl\\ILed.aidl
 */
package com.zacloud.deviceservice.aidl;
/**
 * LED对象
 */
public interface ILed extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.zacloud.deviceservice.aidl.ILed
{
private static final java.lang.String DESCRIPTOR = "com.zacloud.deviceservice.aidl.ILed";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.zacloud.deviceservice.aidl.ILed interface,
 * generating a proxy if needed.
 */
public static com.zacloud.deviceservice.aidl.ILed asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.zacloud.deviceservice.aidl.ILed))) {
return ((com.zacloud.deviceservice.aidl.ILed)iin);
}
return new com.zacloud.deviceservice.aidl.ILed.Stub.Proxy(obj);
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
case TRANSACTION_turnOn:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
this.turnOn(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_turnOff:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
this.turnOff(_arg0);
reply.writeNoException();
return true;
}
default:
{
return super.onTransact(code, data, reply, flags);
}
}
}
private static class Proxy implements com.zacloud.deviceservice.aidl.ILed
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
	 * 点亮LED
	 * @param led - 灯标识
	 * <ul>
	 * <li>1 - 蓝灯</li>
	 * <li>2 - 橙灯</li>
	 * <li>3 - 黄灯</li>
	 * <li>4 - 红灯</li>
	 * </ul>
	 */
@Override public void turnOn(int led) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(led);
mRemote.transact(Stub.TRANSACTION_turnOn, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
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
@Override public void turnOff(int led) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(led);
mRemote.transact(Stub.TRANSACTION_turnOff, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_turnOn = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_turnOff = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
}
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
public void turnOn(int led) throws android.os.RemoteException;
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
public void turnOff(int led) throws android.os.RemoteException;
}
