/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: F:\\Test\\CallCashierAPK\\cashier_driver_lib\\src\\main\\aidl\\com\\sssoft\\drivers\\pos\\aidl\\ScannerListener.aidl
 */
package com.sssoft.drivers.pos.aidl;
public interface ScannerListener extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.sssoft.drivers.pos.aidl.ScannerListener
{
private static final java.lang.String DESCRIPTOR = "com.sssoft.drivers.pos.aidl.ScannerListener";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.sssoft.drivers.pos.aidl.ScannerListener interface,
 * generating a proxy if needed.
 */
public static com.sssoft.drivers.pos.aidl.ScannerListener asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.sssoft.drivers.pos.aidl.ScannerListener))) {
return ((com.sssoft.drivers.pos.aidl.ScannerListener)iin);
}
return new com.sssoft.drivers.pos.aidl.ScannerListener.Stub.Proxy(obj);
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
case TRANSACTION_onScanResult:
{
data.enforceInterface(descriptor);
java.lang.String _arg0;
_arg0 = data.readString();
this.onScanResult(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_onError:
{
data.enforceInterface(descriptor);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
this.onError(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_onFinish:
{
data.enforceInterface(descriptor);
this.onFinish();
reply.writeNoException();
return true;
}
default:
{
return super.onTransact(code, data, reply, flags);
}
}
}
private static class Proxy implements com.sssoft.drivers.pos.aidl.ScannerListener
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
@Override public void onScanResult(java.lang.String barcode) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(barcode);
mRemote.transact(Stub.TRANSACTION_onScanResult, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void onError(java.lang.String code, java.lang.String detail) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(code);
_data.writeString(detail);
mRemote.transact(Stub.TRANSACTION_onError, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void onFinish() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_onFinish, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_onScanResult = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_onError = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_onFinish = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
}
public void onScanResult(java.lang.String barcode) throws android.os.RemoteException;
public void onError(java.lang.String code, java.lang.String detail) throws android.os.RemoteException;
public void onFinish() throws android.os.RemoteException;
}
