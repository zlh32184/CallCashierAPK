/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: F:\\Test\\CallCashierAPK\\cashier_driver_lib\\src\\main\\aidl\\com\\zacloud\\deviceservice\\aidl\\IDeviceService.aidl
 */
package com.zacloud.deviceservice.aidl;
/**
 * 设备服务对象，提供终端各外设对象的服务接口
 */
public interface IDeviceService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.zacloud.deviceservice.aidl.IDeviceService
{
private static final java.lang.String DESCRIPTOR = "com.zacloud.deviceservice.aidl.IDeviceService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.zacloud.deviceservice.aidl.IDeviceService interface,
 * generating a proxy if needed.
 */
public static com.zacloud.deviceservice.aidl.IDeviceService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.zacloud.deviceservice.aidl.IDeviceService))) {
return ((com.zacloud.deviceservice.aidl.IDeviceService)iin);
}
return new com.zacloud.deviceservice.aidl.IDeviceService.Stub.Proxy(obj);
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
case TRANSACTION_getBeeper:
{
data.enforceInterface(descriptor);
android.os.IBinder _result = this.getBeeper();
reply.writeNoException();
reply.writeStrongBinder(_result);
return true;
}
case TRANSACTION_getLed:
{
data.enforceInterface(descriptor);
android.os.IBinder _result = this.getLed();
reply.writeNoException();
reply.writeStrongBinder(_result);
return true;
}
case TRANSACTION_getSerialPort:
{
data.enforceInterface(descriptor);
android.os.IBinder _result = this.getSerialPort();
reply.writeNoException();
reply.writeStrongBinder(_result);
return true;
}
case TRANSACTION_getScanner:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
android.os.IBinder _result = this.getScanner(_arg0);
reply.writeNoException();
reply.writeStrongBinder(_result);
return true;
}
case TRANSACTION_getMagCardReader:
{
data.enforceInterface(descriptor);
android.os.IBinder _result = this.getMagCardReader();
reply.writeNoException();
reply.writeStrongBinder(_result);
return true;
}
case TRANSACTION_getInsertCardReader:
{
data.enforceInterface(descriptor);
android.os.IBinder _result = this.getInsertCardReader();
reply.writeNoException();
reply.writeStrongBinder(_result);
return true;
}
case TRANSACTION_getRFCardReader:
{
data.enforceInterface(descriptor);
android.os.IBinder _result = this.getRFCardReader();
reply.writeNoException();
reply.writeStrongBinder(_result);
return true;
}
case TRANSACTION_getPinpad:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
android.os.IBinder _result = this.getPinpad(_arg0);
reply.writeNoException();
reply.writeStrongBinder(_result);
return true;
}
case TRANSACTION_getPrinter:
{
data.enforceInterface(descriptor);
android.os.IBinder _result = this.getPrinter();
reply.writeNoException();
reply.writeStrongBinder(_result);
return true;
}
case TRANSACTION_getPBOC:
{
data.enforceInterface(descriptor);
android.os.IBinder _result = this.getPBOC();
reply.writeNoException();
reply.writeStrongBinder(_result);
return true;
}
case TRANSACTION_getDeviceInfo:
{
data.enforceInterface(descriptor);
android.os.IBinder _result = this.getDeviceInfo();
reply.writeNoException();
reply.writeStrongBinder(_result);
return true;
}
default:
{
return super.onTransact(code, data, reply, flags);
}
}
}
private static class Proxy implements com.zacloud.deviceservice.aidl.IDeviceService
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
     * 获取蜂鸣器操作对象
     * @return IBeeper对象，参见IBeeper.aidl类
     */
@Override public android.os.IBinder getBeeper() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
android.os.IBinder _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getBeeper, _data, _reply, 0);
_reply.readException();
_result = _reply.readStrongBinder();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
     * 获取LED操作对象
     * @return ILed对象，参见ILed.aidl类
     */
@Override public android.os.IBinder getLed() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
android.os.IBinder _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getLed, _data, _reply, 0);
_reply.readException();
_result = _reply.readStrongBinder();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
     * 获取串口操作对象
     * @return ISerialPort对象，参见ISerialPort.aidl类
     */
@Override public android.os.IBinder getSerialPort() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
android.os.IBinder _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getSerialPort, _data, _reply, 0);
_reply.readException();
_result = _reply.readStrongBinder();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
     * 获取扫码器操作对象
     * @param cameraId - 0 后置扫码器，1 前置扫码器
     * @return IScanner对象，参见IScanner.aidl类
     */
@Override public android.os.IBinder getScanner(int cameraId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
android.os.IBinder _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(cameraId);
mRemote.transact(Stub.TRANSACTION_getScanner, _data, _reply, 0);
_reply.readException();
_result = _reply.readStrongBinder();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
     * 获取磁条卡操作对象
     * @return IMagCardReader对象，参见IMagCardReader.aidl类
     */
@Override public android.os.IBinder getMagCardReader() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
android.os.IBinder _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getMagCardReader, _data, _reply, 0);
_reply.readException();
_result = _reply.readStrongBinder();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
     * 获取IC卡操作对象
     * @return IInsertCardReader对象，参见IInsertCardReader.aidl类
     */
@Override public android.os.IBinder getInsertCardReader() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
android.os.IBinder _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getInsertCardReader, _data, _reply, 0);
_reply.readException();
_result = _reply.readStrongBinder();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
     * 获取RF卡操作对象
     * @return IRFCardReader对象，参见IRFCardReader.aidl类
     */
@Override public android.os.IBinder getRFCardReader() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
android.os.IBinder _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getRFCardReader, _data, _reply, 0);
_reply.readException();
_result = _reply.readStrongBinder();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
     * 获取密码键盘操作对象
     * @param kapId - 密码键盘索引，每个kapId对应一个逻辑密码键盘
     * @return IPinpad对象，参见IPinpad.aidl类
     */
@Override public android.os.IBinder getPinpad(int kapId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
android.os.IBinder _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(kapId);
mRemote.transact(Stub.TRANSACTION_getPinpad, _data, _reply, 0);
_reply.readException();
_result = _reply.readStrongBinder();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
     * 获取打印机操作对象
     * @return IPrinter对象，参见IPrinter.aidl类
     */
@Override public android.os.IBinder getPrinter() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
android.os.IBinder _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getPrinter, _data, _reply, 0);
_reply.readException();
_result = _reply.readStrongBinder();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
     * 获取PBOC(EMV)流程操作对象
     * @return IPBOC对象，参见IPBOC.aidl类
     */
@Override public android.os.IBinder getPBOC() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
android.os.IBinder _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getPBOC, _data, _reply, 0);
_reply.readException();
_result = _reply.readStrongBinder();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
     * 获取设备信息操作对象
     * @return IDeviceInfo对象，参见IDeviceInfo.aidl类
     */
@Override public android.os.IBinder getDeviceInfo() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
android.os.IBinder _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getDeviceInfo, _data, _reply, 0);
_reply.readException();
_result = _reply.readStrongBinder();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_getBeeper = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_getLed = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_getSerialPort = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_getScanner = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_getMagCardReader = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_getInsertCardReader = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
static final int TRANSACTION_getRFCardReader = (android.os.IBinder.FIRST_CALL_TRANSACTION + 6);
static final int TRANSACTION_getPinpad = (android.os.IBinder.FIRST_CALL_TRANSACTION + 7);
static final int TRANSACTION_getPrinter = (android.os.IBinder.FIRST_CALL_TRANSACTION + 8);
static final int TRANSACTION_getPBOC = (android.os.IBinder.FIRST_CALL_TRANSACTION + 9);
static final int TRANSACTION_getDeviceInfo = (android.os.IBinder.FIRST_CALL_TRANSACTION + 10);
}
/**
     * 获取蜂鸣器操作对象
     * @return IBeeper对象，参见IBeeper.aidl类
     */
public android.os.IBinder getBeeper() throws android.os.RemoteException;
/**
     * 获取LED操作对象
     * @return ILed对象，参见ILed.aidl类
     */
public android.os.IBinder getLed() throws android.os.RemoteException;
/**
     * 获取串口操作对象
     * @return ISerialPort对象，参见ISerialPort.aidl类
     */
public android.os.IBinder getSerialPort() throws android.os.RemoteException;
/**
     * 获取扫码器操作对象
     * @param cameraId - 0 后置扫码器，1 前置扫码器
     * @return IScanner对象，参见IScanner.aidl类
     */
public android.os.IBinder getScanner(int cameraId) throws android.os.RemoteException;
/**
     * 获取磁条卡操作对象
     * @return IMagCardReader对象，参见IMagCardReader.aidl类
     */
public android.os.IBinder getMagCardReader() throws android.os.RemoteException;
/**
     * 获取IC卡操作对象
     * @return IInsertCardReader对象，参见IInsertCardReader.aidl类
     */
public android.os.IBinder getInsertCardReader() throws android.os.RemoteException;
/**
     * 获取RF卡操作对象
     * @return IRFCardReader对象，参见IRFCardReader.aidl类
     */
public android.os.IBinder getRFCardReader() throws android.os.RemoteException;
/**
     * 获取密码键盘操作对象
     * @param kapId - 密码键盘索引，每个kapId对应一个逻辑密码键盘
     * @return IPinpad对象，参见IPinpad.aidl类
     */
public android.os.IBinder getPinpad(int kapId) throws android.os.RemoteException;
/**
     * 获取打印机操作对象
     * @return IPrinter对象，参见IPrinter.aidl类
     */
public android.os.IBinder getPrinter() throws android.os.RemoteException;
/**
     * 获取PBOC(EMV)流程操作对象
     * @return IPBOC对象，参见IPBOC.aidl类
     */
public android.os.IBinder getPBOC() throws android.os.RemoteException;
/**
     * 获取设备信息操作对象
     * @return IDeviceInfo对象，参见IDeviceInfo.aidl类
     */
public android.os.IBinder getDeviceInfo() throws android.os.RemoteException;
}
