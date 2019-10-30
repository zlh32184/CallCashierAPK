/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: F:\\Test\\CallCashierAPK\\cashier_driver_lib\\src\\main\\aidl\\com\\zacloud\\deviceservice\\aidl\\MagCardListener.aidl
 */
package com.zacloud.deviceservice.aidl;
/**
 * 磁条卡刷卡结果回调
 */
public interface MagCardListener extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.zacloud.deviceservice.aidl.MagCardListener
{
private static final java.lang.String DESCRIPTOR = "com.zacloud.deviceservice.aidl.MagCardListener";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.zacloud.deviceservice.aidl.MagCardListener interface,
 * generating a proxy if needed.
 */
public static com.zacloud.deviceservice.aidl.MagCardListener asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.zacloud.deviceservice.aidl.MagCardListener))) {
return ((com.zacloud.deviceservice.aidl.MagCardListener)iin);
}
return new com.zacloud.deviceservice.aidl.MagCardListener.Stub.Proxy(obj);
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
case TRANSACTION_onSuccess:
{
data.enforceInterface(descriptor);
android.os.Bundle _arg0;
if ((0!=data.readInt())) {
_arg0 = android.os.Bundle.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.onSuccess(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_onError:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
this.onError(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_onTimeout:
{
data.enforceInterface(descriptor);
this.onTimeout();
reply.writeNoException();
return true;
}
default:
{
return super.onTransact(code, data, reply, flags);
}
}
}
private static class Proxy implements com.zacloud.deviceservice.aidl.MagCardListener
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
	 * 刷卡成功回调
	 * @param track - 磁卡数据对象
     * <ul>
     * <li>PAN(String) - 主账号(卡号)</li>
     * <li>TRACK1(String) - 磁道1数据</li>
     * <li>TRACK2(String) - 磁道2数据 </li>
     * <li>TRACK3(String) - 磁道3数据</li>
     * <li>SERVICE_CODE(String) - 服务码 </li>
     * <li>EXPIRED_DATE(String) - 卡片有效期 </li>
     * </ul>
	 */
@Override public void onSuccess(android.os.Bundle track) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((track!=null)) {
_data.writeInt(1);
track.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_onSuccess, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
	 * 刷卡失败回调
	 * @param error - 错误码
	 * <ul>
     * <li>MAG_SWIPE_ERROR(1) - 刷卡失败</li>
     * <li>SERVICE_CRASH(99) - 设备服务异常</li>
     * <li>REQUEST_EXCEPTION(100) - 请求异常</li>
     * </ul>
	 */
@Override public void onError(int error) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(error);
mRemote.transact(Stub.TRANSACTION_onError, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
	 * 刷卡超时回调
	 */
@Override public void onTimeout() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_onTimeout, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_onSuccess = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_onError = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_onTimeout = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
}
/**
	 * 刷卡成功回调
	 * @param track - 磁卡数据对象
     * <ul>
     * <li>PAN(String) - 主账号(卡号)</li>
     * <li>TRACK1(String) - 磁道1数据</li>
     * <li>TRACK2(String) - 磁道2数据 </li>
     * <li>TRACK3(String) - 磁道3数据</li>
     * <li>SERVICE_CODE(String) - 服务码 </li>
     * <li>EXPIRED_DATE(String) - 卡片有效期 </li>
     * </ul>
	 */
public void onSuccess(android.os.Bundle track) throws android.os.RemoteException;
/**
	 * 刷卡失败回调
	 * @param error - 错误码
	 * <ul>
     * <li>MAG_SWIPE_ERROR(1) - 刷卡失败</li>
     * <li>SERVICE_CRASH(99) - 设备服务异常</li>
     * <li>REQUEST_EXCEPTION(100) - 请求异常</li>
     * </ul>
	 */
public void onError(int error) throws android.os.RemoteException;
/**
	 * 刷卡超时回调
	 */
public void onTimeout() throws android.os.RemoteException;
}
