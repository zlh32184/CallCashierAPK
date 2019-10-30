/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: F:\\Test\\CallCashierAPK\\cashier_driver_lib\\src\\main\\aidl\\com\\zacloud\\deviceservice\\aidl\\ISerialPort.aidl
 */
package com.zacloud.deviceservice.aidl;
/**
 * 串口设备对象
 * 实现串口数据收发功能。
 */
public interface ISerialPort extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.zacloud.deviceservice.aidl.ISerialPort
{
private static final java.lang.String DESCRIPTOR = "com.zacloud.deviceservice.aidl.ISerialPort";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.zacloud.deviceservice.aidl.ISerialPort interface,
 * generating a proxy if needed.
 */
public static com.zacloud.deviceservice.aidl.ISerialPort asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.zacloud.deviceservice.aidl.ISerialPort))) {
return ((com.zacloud.deviceservice.aidl.ISerialPort)iin);
}
return new com.zacloud.deviceservice.aidl.ISerialPort.Stub.Proxy(obj);
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
case TRANSACTION_open:
{
data.enforceInterface(descriptor);
boolean _result = this.open();
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_close:
{
data.enforceInterface(descriptor);
boolean _result = this.close();
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_init:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
int _arg1;
_arg1 = data.readInt();
int _arg2;
_arg2 = data.readInt();
boolean _result = this.init(_arg0, _arg1, _arg2);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_read:
{
data.enforceInterface(descriptor);
byte[] _arg0;
_arg0 = data.createByteArray();
int _arg1;
_arg1 = data.readInt();
int _result = this.read(_arg0, _arg1);
reply.writeNoException();
reply.writeInt(_result);
reply.writeByteArray(_arg0);
return true;
}
case TRANSACTION_write:
{
data.enforceInterface(descriptor);
byte[] _arg0;
_arg0 = data.createByteArray();
int _arg1;
_arg1 = data.readInt();
int _result = this.write(_arg0, _arg1);
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_clearInputBuffer:
{
data.enforceInterface(descriptor);
boolean _result = this.clearInputBuffer();
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_isBufferEmpty:
{
data.enforceInterface(descriptor);
boolean _arg0;
_arg0 = (0!=data.readInt());
boolean _result = this.isBufferEmpty(_arg0);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
default:
{
return super.onTransact(code, data, reply, flags);
}
}
}
private static class Proxy implements com.zacloud.deviceservice.aidl.ISerialPort
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
	 * 打开串口
	 * @return 成功true，失败false
	 */
@Override public boolean open() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_open, _data, _reply, 0);
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
	 * 关闭串口
	 * @return 成功true，失败false
	 */
@Override public boolean close() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_close, _data, _reply, 0);
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
	 * 初始化串口
	 * @param bps	- 波特率
	 * <ul>
     * <li>BPS_1200(0x01) - 1200波特率</li>
     * <li>BPS_2400(0x02) - 2400波特率</li>
     * <li>BPS_4800(0x03) - 4800波特率</li>
     * <li>BPS_9600(0x04) - 9600波特率</li>
     * <li>BPS_14400(0x05) - 14400波特率</li>
     * <li>BPS_28800(0x06) - 28800波特率</li>
     * <li>BPS_19200(0x07) - 19200波特率/li>
     * <li>BPS_57600(0x08) - 57600波特率</li>
     * <li>BPS_115200(0x09) - 115200波特率(缺省)</li>
     * <li>BPS_38400(0x0A) - 38400波特率</li>
     * </ul>
	 * @param par - 校验
	 * <ul>
     * <li>PAR_NOPAR('N') - 无校验(缺省)</li>
     * <li>PAR_EVEN('E') - 偶校验</li>
     * <li>PAR_ODD('O') - 奇校验</li>
     * </ul>
	 * @param dbs - 数据位
	 * <ul>
     * <li>DBS_7(0x07) - 7位数据位</li>
     * <li>DBS_8(0x08) - 8位数据位(缺省)</li>
     * </ul>
	 * @return 成功true，失败false
	 */
@Override public boolean init(int bps, int par, int dbs) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(bps);
_data.writeInt(par);
_data.writeInt(dbs);
mRemote.transact(Stub.TRANSACTION_init, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public int read(byte[] buffer, int timeout) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeByteArray(buffer);
_data.writeInt(timeout);
mRemote.transact(Stub.TRANSACTION_read, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
_reply.readByteArray(buffer);
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public int write(byte[] data, int timeout) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeByteArray(data);
_data.writeInt(timeout);
mRemote.transact(Stub.TRANSACTION_write, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
	 * 清除接收缓冲区
	 * @return 成功true，失败false
	 */
@Override public boolean clearInputBuffer() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_clearInputBuffer, _data, _reply, 0);
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
	 * 查看缓冲区是否为空
	 * @param input - true为输入缓冲区，false为输出缓冲区
	 * @return 成功true，失败false
	 */
@Override public boolean isBufferEmpty(boolean input) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(((input)?(1):(0)));
mRemote.transact(Stub.TRANSACTION_isBufferEmpty, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_open = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_close = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_init = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_read = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_write = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_clearInputBuffer = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
static final int TRANSACTION_isBufferEmpty = (android.os.IBinder.FIRST_CALL_TRANSACTION + 6);
}
/**
	 * 打开串口
	 * @return 成功true，失败false
	 */
public boolean open() throws android.os.RemoteException;
/**
	 * 关闭串口
	 * @return 成功true，失败false
	 */
public boolean close() throws android.os.RemoteException;
/**
	 * 初始化串口
	 * @param bps	- 波特率
	 * <ul>
     * <li>BPS_1200(0x01) - 1200波特率</li>
     * <li>BPS_2400(0x02) - 2400波特率</li>
     * <li>BPS_4800(0x03) - 4800波特率</li>
     * <li>BPS_9600(0x04) - 9600波特率</li>
     * <li>BPS_14400(0x05) - 14400波特率</li>
     * <li>BPS_28800(0x06) - 28800波特率</li>
     * <li>BPS_19200(0x07) - 19200波特率/li>
     * <li>BPS_57600(0x08) - 57600波特率</li>
     * <li>BPS_115200(0x09) - 115200波特率(缺省)</li>
     * <li>BPS_38400(0x0A) - 38400波特率</li>
     * </ul>
	 * @param par - 校验
	 * <ul>
     * <li>PAR_NOPAR('N') - 无校验(缺省)</li>
     * <li>PAR_EVEN('E') - 偶校验</li>
     * <li>PAR_ODD('O') - 奇校验</li>
     * </ul>
	 * @param dbs - 数据位
	 * <ul>
     * <li>DBS_7(0x07) - 7位数据位</li>
     * <li>DBS_8(0x08) - 8位数据位(缺省)</li>
     * </ul>
	 * @return 成功true，失败false
	 */
public boolean init(int bps, int par, int dbs) throws android.os.RemoteException;
public int read(byte[] buffer, int timeout) throws android.os.RemoteException;
public int write(byte[] data, int timeout) throws android.os.RemoteException;
/**
	 * 清除接收缓冲区
	 * @return 成功true，失败false
	 */
public boolean clearInputBuffer() throws android.os.RemoteException;
/**
	 * 查看缓冲区是否为空
	 * @param input - true为输入缓冲区，false为输出缓冲区
	 * @return 成功true，失败false
	 */
public boolean isBufferEmpty(boolean input) throws android.os.RemoteException;
}
