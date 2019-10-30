/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: F:\\Test\\CallCashierAPK\\cashier_driver_lib\\src\\main\\aidl\\com\\zacloud\\deviceservice\\aidl\\CheckCardListener.aidl
 */
package com.zacloud.deviceservice.aidl;
/**
 * 检卡过程监听器
 */
public interface CheckCardListener extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.zacloud.deviceservice.aidl.CheckCardListener
{
private static final java.lang.String DESCRIPTOR = "com.zacloud.deviceservice.aidl.CheckCardListener";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.zacloud.deviceservice.aidl.CheckCardListener interface,
 * generating a proxy if needed.
 */
public static com.zacloud.deviceservice.aidl.CheckCardListener asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.zacloud.deviceservice.aidl.CheckCardListener))) {
return ((com.zacloud.deviceservice.aidl.CheckCardListener)iin);
}
return new com.zacloud.deviceservice.aidl.CheckCardListener.Stub.Proxy(obj);
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
case TRANSACTION_onCardSwiped:
{
data.enforceInterface(descriptor);
android.os.Bundle _arg0;
if ((0!=data.readInt())) {
_arg0 = android.os.Bundle.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.onCardSwiped(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_onCardPowerUp:
{
data.enforceInterface(descriptor);
this.onCardPowerUp();
reply.writeNoException();
return true;
}
case TRANSACTION_onCardActivate:
{
data.enforceInterface(descriptor);
this.onCardActivate();
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
case TRANSACTION_onError:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
java.lang.String _arg1;
_arg1 = data.readString();
this.onError(_arg0, _arg1);
reply.writeNoException();
return true;
}
default:
{
return super.onTransact(code, data, reply, flags);
}
}
}
private static class Proxy implements com.zacloud.deviceservice.aidl.CheckCardListener
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
	 * 检测到磁条卡
	 * @param track - 磁卡数据
	 * <ul>
     * <li>PAN(String) - 主账号(卡号)</li>
     * <li>TRACK1(String) - 磁道1数据</li>
     * <li>TRACK2(String) - 磁道2数据</li>
     * <li>TRACK3(String) - 磁道3数据</li>
     * <li>SERVICE_CODE(String) - 服务码</li>
     * <li>EXPIRED_DATE(String) - 卡片有效期</li>
	 * </ul>
	 */
@Override public void onCardSwiped(android.os.Bundle track) throws android.os.RemoteException
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
mRemote.transact(Stub.TRANSACTION_onCardSwiped, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
	 * IC卡上电成功
	 */
@Override public void onCardPowerUp() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_onCardPowerUp, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
	 * 非接卡激活成功
	 */
@Override public void onCardActivate() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_onCardActivate, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
	 * 检卡超时
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
/**
	 * 检卡出错
	 * @param error - 错误码
	 * <ul>
	 * <li>SERVICE_CRASH(99) - 服务崩溃</li>
	 * <li>REQUEST_EXCEPTION(100) - 请求异常</li>
	 * <li>MAG_SWIPE_ERROR(1) - 刷卡失败</li>
	 * <li>IC_INSERT_ERROR(2) - 插卡失败</li>
	 * <li>IC_POWERUP_ERROR(3) - 卡上电失败</li>
	 * <li>RF_PASS_ERROR(4) - 非接挥卡失败</li>
	 * <li>RF_ACTIVATE_ERROR(5) - 非接卡激活失败</li>
	 * </ul>
	 * @param message - 错误描述
	 */
@Override public void onError(int error, java.lang.String message) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(error);
_data.writeString(message);
mRemote.transact(Stub.TRANSACTION_onError, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_onCardSwiped = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_onCardPowerUp = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_onCardActivate = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_onTimeout = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_onError = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
}
/**
	 * 检测到磁条卡
	 * @param track - 磁卡数据
	 * <ul>
     * <li>PAN(String) - 主账号(卡号)</li>
     * <li>TRACK1(String) - 磁道1数据</li>
     * <li>TRACK2(String) - 磁道2数据</li>
     * <li>TRACK3(String) - 磁道3数据</li>
     * <li>SERVICE_CODE(String) - 服务码</li>
     * <li>EXPIRED_DATE(String) - 卡片有效期</li>
	 * </ul>
	 */
public void onCardSwiped(android.os.Bundle track) throws android.os.RemoteException;
/**
	 * IC卡上电成功
	 */
public void onCardPowerUp() throws android.os.RemoteException;
/**
	 * 非接卡激活成功
	 */
public void onCardActivate() throws android.os.RemoteException;
/**
	 * 检卡超时
	 */
public void onTimeout() throws android.os.RemoteException;
/**
	 * 检卡出错
	 * @param error - 错误码
	 * <ul>
	 * <li>SERVICE_CRASH(99) - 服务崩溃</li>
	 * <li>REQUEST_EXCEPTION(100) - 请求异常</li>
	 * <li>MAG_SWIPE_ERROR(1) - 刷卡失败</li>
	 * <li>IC_INSERT_ERROR(2) - 插卡失败</li>
	 * <li>IC_POWERUP_ERROR(3) - 卡上电失败</li>
	 * <li>RF_PASS_ERROR(4) - 非接挥卡失败</li>
	 * <li>RF_ACTIVATE_ERROR(5) - 非接卡激活失败</li>
	 * </ul>
	 * @param message - 错误描述
	 */
public void onError(int error, java.lang.String message) throws android.os.RemoteException;
}
