/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: F:\\Test\\CallCashierAPK\\cashier_driver_lib\\src\\main\\aidl\\com\\zacloud\\deviceservice\\aidl\\PinInputListener.aidl
 */
package com.zacloud.deviceservice.aidl;
/**
 * PIN输入过程监听器
 */
public interface PinInputListener extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.zacloud.deviceservice.aidl.PinInputListener
{
private static final java.lang.String DESCRIPTOR = "com.zacloud.deviceservice.aidl.PinInputListener";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.zacloud.deviceservice.aidl.PinInputListener interface,
 * generating a proxy if needed.
 */
public static com.zacloud.deviceservice.aidl.PinInputListener asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.zacloud.deviceservice.aidl.PinInputListener))) {
return ((com.zacloud.deviceservice.aidl.PinInputListener)iin);
}
return new com.zacloud.deviceservice.aidl.PinInputListener.Stub.Proxy(obj);
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
case TRANSACTION_onInput:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
int _arg1;
_arg1 = data.readInt();
this.onInput(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_onConfirm:
{
data.enforceInterface(descriptor);
byte[] _arg0;
_arg0 = data.createByteArray();
boolean _arg1;
_arg1 = (0!=data.readInt());
this.onConfirm(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_onCancel:
{
data.enforceInterface(descriptor);
this.onCancel();
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
default:
{
return super.onTransact(code, data, reply, flags);
}
}
}
private static class Proxy implements com.zacloud.deviceservice.aidl.PinInputListener
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
     * 按键按压事件
     * @param len - 已输入密码长度
     * @param key - 当前的Key值(预留给物理键盘使用)
     */
@Override public void onInput(int len, int key) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(len);
_data.writeInt(key);
mRemote.transact(Stub.TRANSACTION_onInput, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
     * 确认PIN输入时调用
     * @param data - PIN密文，输入为空时为null
     * @param isNonePin - 输入为空的时候为true
     */
@Override public void onConfirm(byte[] data, boolean isNonePin) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeByteArray(data);
_data.writeInt(((isNonePin)?(1):(0)));
mRemote.transact(Stub.TRANSACTION_onConfirm, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
     * 取消PIN输入时调用
     */
@Override public void onCancel() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_onCancel, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
     * 错误时回调
     * @param errorCode - 错误码
     */
@Override public void onError(int errorCode) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(errorCode);
mRemote.transact(Stub.TRANSACTION_onError, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_onInput = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_onConfirm = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_onCancel = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_onError = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
}
/**
     * 按键按压事件
     * @param len - 已输入密码长度
     * @param key - 当前的Key值(预留给物理键盘使用)
     */
public void onInput(int len, int key) throws android.os.RemoteException;
/**
     * 确认PIN输入时调用
     * @param data - PIN密文，输入为空时为null
     * @param isNonePin - 输入为空的时候为true
     */
public void onConfirm(byte[] data, boolean isNonePin) throws android.os.RemoteException;
/**
     * 取消PIN输入时调用
     */
public void onCancel() throws android.os.RemoteException;
/**
     * 错误时回调
     * @param errorCode - 错误码
     */
public void onError(int errorCode) throws android.os.RemoteException;
}
