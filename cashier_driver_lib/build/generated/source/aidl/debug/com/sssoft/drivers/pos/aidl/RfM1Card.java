/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: F:\\Test\\CallCashierAPK\\cashier_driver_lib\\src\\main\\aidl\\com\\sssoft\\drivers\\pos\\aidl\\RfM1Card.aidl
 */
package com.sssoft.drivers.pos.aidl;
public interface RfM1Card extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.sssoft.drivers.pos.aidl.RfM1Card
{
private static final java.lang.String DESCRIPTOR = "com.sssoft.drivers.pos.aidl.RfM1Card";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.sssoft.drivers.pos.aidl.RfM1Card interface,
 * generating a proxy if needed.
 */
public static com.sssoft.drivers.pos.aidl.RfM1Card asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.sssoft.drivers.pos.aidl.RfM1Card))) {
return ((com.sssoft.drivers.pos.aidl.RfM1Card)iin);
}
return new com.sssoft.drivers.pos.aidl.RfM1Card.Stub.Proxy(obj);
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
java.lang.String _arg1;
_arg1 = data.readString();
java.lang.String _arg2;
_arg2 = data.readString();
com.sssoft.drivers.pos.aidl.RfM1CardListener _arg3;
_arg3 = com.sssoft.drivers.pos.aidl.RfM1CardListener.Stub.asInterface(data.readStrongBinder());
this.read(_arg0, _arg1, _arg2, _arg3);
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
private static class Proxy implements com.sssoft.drivers.pos.aidl.RfM1Card
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
@Override public void read(int timeout, java.lang.String b0auk, java.lang.String b4auk, com.sssoft.drivers.pos.aidl.RfM1CardListener listener) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(timeout);
_data.writeString(b0auk);
_data.writeString(b4auk);
_data.writeStrongBinder((((listener!=null))?(listener.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_read, _data, _reply, 0);
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
static final int TRANSACTION_cancel = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
}
public void read(int timeout, java.lang.String b0auk, java.lang.String b4auk, com.sssoft.drivers.pos.aidl.RfM1CardListener listener) throws android.os.RemoteException;
public void cancel() throws android.os.RemoteException;
}
