/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: F:\\Test\\CallCashierAPK\\cashier_driver_lib\\src\\main\\aidl\\com\\zacloud\\deviceservice\\aidl\\IInsertCardReader.aidl
 */
package com.zacloud.deviceservice.aidl;
/**
 * 接触式IC读卡器对象
 */
public interface IInsertCardReader extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.zacloud.deviceservice.aidl.IInsertCardReader
{
private static final java.lang.String DESCRIPTOR = "com.zacloud.deviceservice.aidl.IInsertCardReader";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.zacloud.deviceservice.aidl.IInsertCardReader interface,
 * generating a proxy if needed.
 */
public static com.zacloud.deviceservice.aidl.IInsertCardReader asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.zacloud.deviceservice.aidl.IInsertCardReader))) {
return ((com.zacloud.deviceservice.aidl.IInsertCardReader)iin);
}
return new com.zacloud.deviceservice.aidl.IInsertCardReader.Stub.Proxy(obj);
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
case TRANSACTION_powerUp:
{
data.enforceInterface(descriptor);
boolean _result = this.powerUp();
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_powerDown:
{
data.enforceInterface(descriptor);
boolean _result = this.powerDown();
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_isCardIn:
{
data.enforceInterface(descriptor);
boolean _result = this.isCardIn();
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_exchangeApdu:
{
data.enforceInterface(descriptor);
byte[] _arg0;
_arg0 = data.createByteArray();
byte[] _result = this.exchangeApdu(_arg0);
reply.writeNoException();
reply.writeByteArray(_result);
return true;
}
default:
{
return super.onTransact(code, data, reply, flags);
}
}
}
private static class Proxy implements com.zacloud.deviceservice.aidl.IInsertCardReader
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
	 * 卡上电
	 * @return 上电成功true，失败false。
	 */
@Override public boolean powerUp() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_powerUp, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
	 * 卡下电
	 * @return 关闭成功true，失败false。
	 */
@Override public boolean powerDown() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_powerDown, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
	 * 卡片是否在位
	 * @return 卡在位true，卡不在位false。
	 */
@Override public boolean isCardIn() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_isCardIn, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
	 * APDU数据通讯
	 * @param apdu - apdu数据
	 * @return 成功返回卡片应答数据，失败返回null。
	 */
@Override public byte[] exchangeApdu(byte[] apdu) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
byte[] _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeByteArray(apdu);
mRemote.transact(Stub.TRANSACTION_exchangeApdu, _data, _reply, 0);
_reply.readException();
_result = _reply.createByteArray();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_powerUp = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_powerDown = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_isCardIn = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_exchangeApdu = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
}
/**
	 * 卡上电
	 * @return 上电成功true，失败false。
	 */
public boolean powerUp() throws android.os.RemoteException;
/**
	 * 卡下电
	 * @return 关闭成功true，失败false。
	 */
public boolean powerDown() throws android.os.RemoteException;
/**
	 * 卡片是否在位
	 * @return 卡在位true，卡不在位false。
	 */
public boolean isCardIn() throws android.os.RemoteException;
/**
	 * APDU数据通讯
	 * @param apdu - apdu数据
	 * @return 成功返回卡片应答数据，失败返回null。
	 */
public byte[] exchangeApdu(byte[] apdu) throws android.os.RemoteException;
}
