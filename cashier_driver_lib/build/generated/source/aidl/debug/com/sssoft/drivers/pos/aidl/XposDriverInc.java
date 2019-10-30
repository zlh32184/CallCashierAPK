/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: F:\\Test\\CallCashierAPK\\cashier_driver_lib\\src\\main\\aidl\\com\\sssoft\\drivers\\pos\\aidl\\XposDriverInc.aidl
 */
package com.sssoft.drivers.pos.aidl;
public interface XposDriverInc extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.sssoft.drivers.pos.aidl.XposDriverInc
{
private static final java.lang.String DESCRIPTOR = "com.sssoft.drivers.pos.aidl.XposDriverInc";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.sssoft.drivers.pos.aidl.XposDriverInc interface,
 * generating a proxy if needed.
 */
public static com.sssoft.drivers.pos.aidl.XposDriverInc asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.sssoft.drivers.pos.aidl.XposDriverInc))) {
return ((com.sssoft.drivers.pos.aidl.XposDriverInc)iin);
}
return new com.sssoft.drivers.pos.aidl.XposDriverInc.Stub.Proxy(obj);
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
case TRANSACTION_getPrinter:
{
data.enforceInterface(descriptor);
com.sssoft.drivers.pos.aidl.Printer _result = this.getPrinter();
reply.writeNoException();
reply.writeStrongBinder((((_result!=null))?(_result.asBinder()):(null)));
return true;
}
case TRANSACTION_getCard:
{
data.enforceInterface(descriptor);
com.sssoft.drivers.pos.aidl.Card _result = this.getCard();
reply.writeNoException();
reply.writeStrongBinder((((_result!=null))?(_result.asBinder()):(null)));
return true;
}
case TRANSACTION_getInsertCard:
{
data.enforceInterface(descriptor);
com.sssoft.drivers.pos.aidl.InsertCard _result = this.getInsertCard();
reply.writeNoException();
reply.writeStrongBinder((((_result!=null))?(_result.asBinder()):(null)));
return true;
}
case TRANSACTION_getAllCard:
{
data.enforceInterface(descriptor);
com.sssoft.drivers.pos.aidl.AllCard _result = this.getAllCard();
reply.writeNoException();
reply.writeStrongBinder((((_result!=null))?(_result.asBinder()):(null)));
return true;
}
case TRANSACTION_getScanner:
{
data.enforceInterface(descriptor);
com.sssoft.drivers.pos.aidl.Scanner _result = this.getScanner();
reply.writeNoException();
reply.writeStrongBinder((((_result!=null))?(_result.asBinder()):(null)));
return true;
}
case TRANSACTION_getRfM1Card:
{
data.enforceInterface(descriptor);
com.sssoft.drivers.pos.aidl.RfM1Card _result = this.getRfM1Card();
reply.writeNoException();
reply.writeStrongBinder((((_result!=null))?(_result.asBinder()):(null)));
return true;
}
default:
{
return super.onTransact(code, data, reply, flags);
}
}
}
private static class Proxy implements com.sssoft.drivers.pos.aidl.XposDriverInc
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
@Override public com.sssoft.drivers.pos.aidl.Printer getPrinter() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
com.sssoft.drivers.pos.aidl.Printer _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getPrinter, _data, _reply, 0);
_reply.readException();
_result = com.sssoft.drivers.pos.aidl.Printer.Stub.asInterface(_reply.readStrongBinder());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
//IBinder getScanner();

@Override public com.sssoft.drivers.pos.aidl.Card getCard() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
com.sssoft.drivers.pos.aidl.Card _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getCard, _data, _reply, 0);
_reply.readException();
_result = com.sssoft.drivers.pos.aidl.Card.Stub.asInterface(_reply.readStrongBinder());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public com.sssoft.drivers.pos.aidl.InsertCard getInsertCard() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
com.sssoft.drivers.pos.aidl.InsertCard _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getInsertCard, _data, _reply, 0);
_reply.readException();
_result = com.sssoft.drivers.pos.aidl.InsertCard.Stub.asInterface(_reply.readStrongBinder());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public com.sssoft.drivers.pos.aidl.AllCard getAllCard() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
com.sssoft.drivers.pos.aidl.AllCard _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getAllCard, _data, _reply, 0);
_reply.readException();
_result = com.sssoft.drivers.pos.aidl.AllCard.Stub.asInterface(_reply.readStrongBinder());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public com.sssoft.drivers.pos.aidl.Scanner getScanner() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
com.sssoft.drivers.pos.aidl.Scanner _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getScanner, _data, _reply, 0);
_reply.readException();
_result = com.sssoft.drivers.pos.aidl.Scanner.Stub.asInterface(_reply.readStrongBinder());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public com.sssoft.drivers.pos.aidl.RfM1Card getRfM1Card() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
com.sssoft.drivers.pos.aidl.RfM1Card _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getRfM1Card, _data, _reply, 0);
_reply.readException();
_result = com.sssoft.drivers.pos.aidl.RfM1Card.Stub.asInterface(_reply.readStrongBinder());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_getPrinter = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_getCard = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_getInsertCard = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_getAllCard = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_getScanner = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_getRfM1Card = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
}
public com.sssoft.drivers.pos.aidl.Printer getPrinter() throws android.os.RemoteException;
//IBinder getScanner();

public com.sssoft.drivers.pos.aidl.Card getCard() throws android.os.RemoteException;
public com.sssoft.drivers.pos.aidl.InsertCard getInsertCard() throws android.os.RemoteException;
public com.sssoft.drivers.pos.aidl.AllCard getAllCard() throws android.os.RemoteException;
public com.sssoft.drivers.pos.aidl.Scanner getScanner() throws android.os.RemoteException;
public com.sssoft.drivers.pos.aidl.RfM1Card getRfM1Card() throws android.os.RemoteException;
}
