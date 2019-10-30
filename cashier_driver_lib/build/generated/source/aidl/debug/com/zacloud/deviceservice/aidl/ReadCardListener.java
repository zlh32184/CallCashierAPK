/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: F:\\Test\\CallCashierAPK\\cashier_driver_lib\\src\\main\\aidl\\com\\zacloud\\deviceservice\\aidl\\ReadCardListener.aidl
 */
package com.zacloud.deviceservice.aidl;
/**
 * 读卡监听器
 */
public interface ReadCardListener extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.zacloud.deviceservice.aidl.ReadCardListener
{
private static final java.lang.String DESCRIPTOR = "com.zacloud.deviceservice.aidl.ReadCardListener";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.zacloud.deviceservice.aidl.ReadCardListener interface,
 * generating a proxy if needed.
 */
public static com.zacloud.deviceservice.aidl.ReadCardListener asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.zacloud.deviceservice.aidl.ReadCardListener))) {
return ((com.zacloud.deviceservice.aidl.ReadCardListener)iin);
}
return new com.zacloud.deviceservice.aidl.ReadCardListener.Stub.Proxy(obj);
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
case TRANSACTION_onSuccess:
{
data.enforceInterface(descriptor);
java.lang.String _arg0;
_arg0 = data.readString();
this.onSuccess(_arg0);
reply.writeNoException();
return true;
}
default:
{
return super.onTransact(code, data, reply, flags);
}
}
}
private static class Proxy implements com.zacloud.deviceservice.aidl.ReadCardListener
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
//读卡失败，通过回调监听返回错误码和错误信息

@Override public void onError(int errorCode, java.lang.String message) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(errorCode);
_data.writeString(message);
mRemote.transact(Stub.TRANSACTION_onError, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
//读卡成功，通过回调监听返回卡号

@Override public void onSuccess(java.lang.String cardNo) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(cardNo);
mRemote.transact(Stub.TRANSACTION_onSuccess, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_onError = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_onSuccess = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
}
//读卡失败，通过回调监听返回错误码和错误信息

public void onError(int errorCode, java.lang.String message) throws android.os.RemoteException;
//读卡成功，通过回调监听返回卡号

public void onSuccess(java.lang.String cardNo) throws android.os.RemoteException;
}
