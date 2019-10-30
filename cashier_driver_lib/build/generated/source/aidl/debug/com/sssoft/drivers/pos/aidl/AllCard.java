/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: F:\\Test\\CallCashierAPK\\cashier_driver_lib\\src\\main\\aidl\\com\\sssoft\\drivers\\pos\\aidl\\AllCard.aidl
 */
package com.sssoft.drivers.pos.aidl;
public interface AllCard extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.sssoft.drivers.pos.aidl.AllCard
{
private static final java.lang.String DESCRIPTOR = "com.sssoft.drivers.pos.aidl.AllCard";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.sssoft.drivers.pos.aidl.AllCard interface,
 * generating a proxy if needed.
 */
public static com.sssoft.drivers.pos.aidl.AllCard asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.sssoft.drivers.pos.aidl.AllCard))) {
return ((com.sssoft.drivers.pos.aidl.AllCard)iin);
}
return new com.sssoft.drivers.pos.aidl.AllCard.Stub.Proxy(obj);
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
case TRANSACTION_read:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
com.sssoft.drivers.pos.aidl.AllCardListener _arg1;
_arg1 = com.sssoft.drivers.pos.aidl.AllCardListener.Stub.asInterface(data.readStrongBinder());
this.read(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_setM1Key:
{
data.enforceInterface(descriptor);
java.lang.String _arg0;
_arg0 = data.readString();
this.setM1Key(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_cancel:
{
data.enforceInterface(descriptor);
this.cancel();
reply.writeNoException();
return true;
}
default:
{
return super.onTransact(code, data, reply, flags);
}
}
}
private static class Proxy implements com.sssoft.drivers.pos.aidl.AllCard
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
@Override public void read(int timeout, com.sssoft.drivers.pos.aidl.AllCardListener listener) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(timeout);
_data.writeStrongBinder((((listener!=null))?(listener.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_read, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void setM1Key(java.lang.String Key) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(Key);
mRemote.transact(Stub.TRANSACTION_setM1Key, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void cancel() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_cancel, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_read = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_setM1Key = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_cancel = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
}
public void read(int timeout, com.sssoft.drivers.pos.aidl.AllCardListener listener) throws android.os.RemoteException;
public void setM1Key(java.lang.String Key) throws android.os.RemoteException;
public void cancel() throws android.os.RemoteException;
}
