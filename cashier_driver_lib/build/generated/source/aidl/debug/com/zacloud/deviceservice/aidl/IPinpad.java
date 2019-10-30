/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: F:\\Test\\CallCashierAPK\\cashier_driver_lib\\src\\main\\aidl\\com\\zacloud\\deviceservice\\aidl\\IPinpad.aidl
 */
package com.zacloud.deviceservice.aidl;
/**
 * 密码键盘对象
 * 实现金融交易过程中密钥管理、数据加密和PIN输入处理过程。
 */
public interface IPinpad extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.zacloud.deviceservice.aidl.IPinpad
{
private static final java.lang.String DESCRIPTOR = "com.zacloud.deviceservice.aidl.IPinpad";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.zacloud.deviceservice.aidl.IPinpad interface,
 * generating a proxy if needed.
 */
public static com.zacloud.deviceservice.aidl.IPinpad asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.zacloud.deviceservice.aidl.IPinpad))) {
return ((com.zacloud.deviceservice.aidl.IPinpad)iin);
}
return new com.zacloud.deviceservice.aidl.IPinpad.Stub.Proxy(obj);
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
case TRANSACTION_isKeyExist:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
int _arg1;
_arg1 = data.readInt();
boolean _result = this.isKeyExist(_arg0, _arg1);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_loadTEK:
{
data.enforceInterface(descriptor);
byte[] _arg0;
_arg0 = data.createByteArray();
byte[] _arg1;
_arg1 = data.createByteArray();
boolean _result = this.loadTEK(_arg0, _arg1);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_loadEncryptMainKey:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
byte[] _arg1;
_arg1 = data.createByteArray();
byte[] _arg2;
_arg2 = data.createByteArray();
boolean _result = this.loadEncryptMainKey(_arg0, _arg1, _arg2);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_loadMainKey:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
byte[] _arg1;
_arg1 = data.createByteArray();
byte[] _arg2;
_arg2 = data.createByteArray();
boolean _result = this.loadMainKey(_arg0, _arg1, _arg2);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_loadWorkKey:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
int _arg1;
_arg1 = data.readInt();
int _arg2;
_arg2 = data.readInt();
byte[] _arg3;
_arg3 = data.createByteArray();
byte[] _arg4;
_arg4 = data.createByteArray();
boolean _result = this.loadWorkKey(_arg0, _arg1, _arg2, _arg3, _arg4);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_calcMAC:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
byte[] _arg1;
_arg1 = data.createByteArray();
byte[] _result = this.calcMAC(_arg0, _arg1);
reply.writeNoException();
reply.writeByteArray(_result);
return true;
}
case TRANSACTION_encryptTrackData:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
int _arg1;
_arg1 = data.readInt();
byte[] _arg2;
_arg2 = data.createByteArray();
byte[] _result = this.encryptTrackData(_arg0, _arg1, _arg2);
reply.writeNoException();
reply.writeByteArray(_result);
return true;
}
case TRANSACTION_getTUSN:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
byte[] _arg1;
_arg1 = data.createByteArray();
com.zacloud.deviceservice.aidl.TusnData _result = this.getTUSN(_arg0, _arg1);
reply.writeNoException();
if ((_result!=null)) {
reply.writeInt(1);
_result.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
}
else {
reply.writeInt(0);
}
return true;
}
case TRANSACTION_startPinInput:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
android.os.Bundle _arg1;
if ((0!=data.readInt())) {
_arg1 = android.os.Bundle.CREATOR.createFromParcel(data);
}
else {
_arg1 = null;
}
com.zacloud.deviceservice.aidl.PinInputListener _arg2;
_arg2 = com.zacloud.deviceservice.aidl.PinInputListener.Stub.asInterface(data.readStrongBinder());
this.startPinInput(_arg0, _arg1, _arg2);
reply.writeNoException();
return true;
}
case TRANSACTION_stopPinInput:
{
data.enforceInterface(descriptor);
this.stopPinInput();
reply.writeNoException();
return true;
}
case TRANSACTION_getLastError:
{
data.enforceInterface(descriptor);
java.lang.String _result = this.getLastError();
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_setKeyAlgorithm:
{
data.enforceInterface(descriptor);
char _arg0;
_arg0 = (char)data.readInt();
this.setKeyAlgorithm(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_isSM4Enabled:
{
data.enforceInterface(descriptor);
boolean _result = this.isSM4Enabled();
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
private static class Proxy implements com.zacloud.deviceservice.aidl.IPinpad
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
	 * 判断密钥是否存在(密钥已下载)
	 * @param keyType - 密钥类型
	 * <ul>
	 * <li>0-主密钥</li>
	 * <li>1-MAC密钥</li>
	 * <li>2-PIN密钥</li>
	 * <li>3-TD密钥</li>
	 * </ul>
	 * @param keyId - 密钥ID
	 * @return 存在返回true，不存在返回false
	 */
@Override public boolean isKeyExist(int keyType, int keyId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(keyType);
_data.writeInt(keyId);
mRemote.transact(Stub.TRANSACTION_isKeyExist, _data, _reply, 0);
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
	 * 下装TEK密钥
	 * TEK是加密主密钥的密钥
	 * @param key - 密钥
	 * @param checkValue - 校验值
	 * @return 成功true，失败false
	 */
@Override public boolean loadTEK(byte[] key, byte[] checkValue) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeByteArray(key);
_data.writeByteArray(checkValue);
mRemote.transact(Stub.TRANSACTION_loadTEK, _data, _reply, 0);
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
	 * 下装密文主密钥
	 * @param keyId - 下装密钥存储ID
	 * @param key - 密钥
	 * @param checkValue - 校验值
	 * @return 成功true，失败false
	 */
@Override public boolean loadEncryptMainKey(int keyId, byte[] key, byte[] checkValue) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(keyId);
_data.writeByteArray(key);
_data.writeByteArray(checkValue);
mRemote.transact(Stub.TRANSACTION_loadEncryptMainKey, _data, _reply, 0);
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
	 * 下装明文主密钥
	 * @param keyId - 下装密钥存储ID
	 * @param key - 密钥
	 * @param checkValue - 校验值
	 * @return 成功true，失败false
	 */
@Override public boolean loadMainKey(int keyId, byte[] key, byte[] checkValue) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(keyId);
_data.writeByteArray(key);
_data.writeByteArray(checkValue);
mRemote.transact(Stub.TRANSACTION_loadMainKey, _data, _reply, 0);
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
	 * 下装工作密钥
	 * @param keyType - 下装工作密钥类型，1-MAC密钥，2-PIN密钥，3-TD密钥
	 * @param mkId - 解密工作密钥的主密钥ID
	 * @param wkId - 下装密钥存储ID
	 * @param key - 密钥
	 * @param checkValue - 校验值
	 * @return 成功true，失败false
	 */
@Override public boolean loadWorkKey(int keyType, int mkId, int wkId, byte[] key, byte[] checkValue) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(keyType);
_data.writeInt(mkId);
_data.writeInt(wkId);
_data.writeByteArray(key);
_data.writeByteArray(checkValue);
mRemote.transact(Stub.TRANSACTION_loadWorkKey, _data, _reply, 0);
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
	 * 计算MAC值
	 * @param keyId - MAC密钥索引
	 * @param data - 计算MAC的数据
	 * @return 成功返回mac值，失败返回null
	 */
@Override public byte[] calcMAC(int keyId, byte[] data) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
byte[] _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(keyId);
_data.writeByteArray(data);
mRemote.transact(Stub.TRANSACTION_calcMAC, _data, _reply, 0);
_reply.readException();
_result = _reply.createByteArray();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
	 * 加密磁道数据
	 * @param mode - 加密模式，0-ECB，1-CBC
	 * @param keyId - TDK密钥索引
	 * @param trkData - 待加密磁道数据
	 * @return 成功返回加密后的磁道数据，失败返回null
	 */
@Override public byte[] encryptTrackData(int mode, int keyId, byte[] trkData) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
byte[] _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(mode);
_data.writeInt(keyId);
_data.writeByteArray(trkData);
mRemote.transact(Stub.TRANSACTION_encryptTrackData, _data, _reply, 0);
_reply.readException();
_result = _reply.createByteArray();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
     * 获取终端唯一序列号及其密文
     * @param mode - 模式，预留参数，固定填0
     * @param input - 加密随机因子，允许范围4-10字节
     * @return 成功返回TUSN数据，失败返回null
     */
@Override public com.zacloud.deviceservice.aidl.TusnData getTUSN(int mode, byte[] input) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
com.zacloud.deviceservice.aidl.TusnData _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(mode);
_data.writeByteArray(input);
mRemote.transact(Stub.TRANSACTION_getTUSN, _data, _reply, 0);
_reply.readException();
if ((0!=_reply.readInt())) {
_result = com.zacloud.deviceservice.aidl.TusnData.CREATOR.createFromParcel(_reply);
}
else {
_result = null;
}
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
     * 启动PIN输入
     * @param keyId - PIN密钥索引
     * @param param - PIN输入配置参数
     * <ul>
     * <li>pinLimit(byte[]) - 允许输入密码的长度</li>
     * <li>timeout(int) - 输入超时时间，单位秒</li>
     * <li>isOnline(boolean) - 是否联机PIN</li>
     * <li>panBlock(byte[]) - 用于加密联机PIN的PAN BLOCK</li>
     * </ul>
     * @param listener - PIN输入过程监听器
     */
@Override public void startPinInput(int keyId, android.os.Bundle param, com.zacloud.deviceservice.aidl.PinInputListener listener) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(keyId);
if ((param!=null)) {
_data.writeInt(1);
param.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
_data.writeStrongBinder((((listener!=null))?(listener.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_startPinInput, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
	 * 取消PIN输入过程
	 */
@Override public void stopPinInput() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_stopPinInput, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public java.lang.String getLastError() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getLastError, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
     * 设置密钥的算法类型
     * @param keyAlgorithm - 算法类型，见KeyAlgorithm常量定义
     * @see KeyAlgorithm定义
     */
@Override public void setKeyAlgorithm(char keyAlgorithm) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(((int)keyAlgorithm));
mRemote.transact(Stub.TRANSACTION_setKeyAlgorithm, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
     * 是否使用国密算法
     * @return 是返回true，否返回false
     */
@Override public boolean isSM4Enabled() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_isSM4Enabled, _data, _reply, 0);
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
static final int TRANSACTION_isKeyExist = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_loadTEK = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_loadEncryptMainKey = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_loadMainKey = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_loadWorkKey = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_calcMAC = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
static final int TRANSACTION_encryptTrackData = (android.os.IBinder.FIRST_CALL_TRANSACTION + 6);
static final int TRANSACTION_getTUSN = (android.os.IBinder.FIRST_CALL_TRANSACTION + 7);
static final int TRANSACTION_startPinInput = (android.os.IBinder.FIRST_CALL_TRANSACTION + 8);
static final int TRANSACTION_stopPinInput = (android.os.IBinder.FIRST_CALL_TRANSACTION + 9);
static final int TRANSACTION_getLastError = (android.os.IBinder.FIRST_CALL_TRANSACTION + 10);
static final int TRANSACTION_setKeyAlgorithm = (android.os.IBinder.FIRST_CALL_TRANSACTION + 11);
static final int TRANSACTION_isSM4Enabled = (android.os.IBinder.FIRST_CALL_TRANSACTION + 12);
}
/**
	 * 判断密钥是否存在(密钥已下载)
	 * @param keyType - 密钥类型
	 * <ul>
	 * <li>0-主密钥</li>
	 * <li>1-MAC密钥</li>
	 * <li>2-PIN密钥</li>
	 * <li>3-TD密钥</li>
	 * </ul>
	 * @param keyId - 密钥ID
	 * @return 存在返回true，不存在返回false
	 */
public boolean isKeyExist(int keyType, int keyId) throws android.os.RemoteException;
/**
	 * 下装TEK密钥
	 * TEK是加密主密钥的密钥
	 * @param key - 密钥
	 * @param checkValue - 校验值
	 * @return 成功true，失败false
	 */
public boolean loadTEK(byte[] key, byte[] checkValue) throws android.os.RemoteException;
/**
	 * 下装密文主密钥
	 * @param keyId - 下装密钥存储ID
	 * @param key - 密钥
	 * @param checkValue - 校验值
	 * @return 成功true，失败false
	 */
public boolean loadEncryptMainKey(int keyId, byte[] key, byte[] checkValue) throws android.os.RemoteException;
/**
	 * 下装明文主密钥
	 * @param keyId - 下装密钥存储ID
	 * @param key - 密钥
	 * @param checkValue - 校验值
	 * @return 成功true，失败false
	 */
public boolean loadMainKey(int keyId, byte[] key, byte[] checkValue) throws android.os.RemoteException;
/**
	 * 下装工作密钥
	 * @param keyType - 下装工作密钥类型，1-MAC密钥，2-PIN密钥，3-TD密钥
	 * @param mkId - 解密工作密钥的主密钥ID
	 * @param wkId - 下装密钥存储ID
	 * @param key - 密钥
	 * @param checkValue - 校验值
	 * @return 成功true，失败false
	 */
public boolean loadWorkKey(int keyType, int mkId, int wkId, byte[] key, byte[] checkValue) throws android.os.RemoteException;
/**
	 * 计算MAC值
	 * @param keyId - MAC密钥索引
	 * @param data - 计算MAC的数据
	 * @return 成功返回mac值，失败返回null
	 */
public byte[] calcMAC(int keyId, byte[] data) throws android.os.RemoteException;
/**
	 * 加密磁道数据
	 * @param mode - 加密模式，0-ECB，1-CBC
	 * @param keyId - TDK密钥索引
	 * @param trkData - 待加密磁道数据
	 * @return 成功返回加密后的磁道数据，失败返回null
	 */
public byte[] encryptTrackData(int mode, int keyId, byte[] trkData) throws android.os.RemoteException;
/**
     * 获取终端唯一序列号及其密文
     * @param mode - 模式，预留参数，固定填0
     * @param input - 加密随机因子，允许范围4-10字节
     * @return 成功返回TUSN数据，失败返回null
     */
public com.zacloud.deviceservice.aidl.TusnData getTUSN(int mode, byte[] input) throws android.os.RemoteException;
/**
     * 启动PIN输入
     * @param keyId - PIN密钥索引
     * @param param - PIN输入配置参数
     * <ul>
     * <li>pinLimit(byte[]) - 允许输入密码的长度</li>
     * <li>timeout(int) - 输入超时时间，单位秒</li>
     * <li>isOnline(boolean) - 是否联机PIN</li>
     * <li>panBlock(byte[]) - 用于加密联机PIN的PAN BLOCK</li>
     * </ul>
     * @param listener - PIN输入过程监听器
     */
public void startPinInput(int keyId, android.os.Bundle param, com.zacloud.deviceservice.aidl.PinInputListener listener) throws android.os.RemoteException;
/**
	 * 取消PIN输入过程
	 */
public void stopPinInput() throws android.os.RemoteException;
public java.lang.String getLastError() throws android.os.RemoteException;
/**
     * 设置密钥的算法类型
     * @param keyAlgorithm - 算法类型，见KeyAlgorithm常量定义
     * @see KeyAlgorithm定义
     */
public void setKeyAlgorithm(char keyAlgorithm) throws android.os.RemoteException;
/**
     * 是否使用国密算法
     * @return 是返回true，否返回false
     */
public boolean isSM4Enabled() throws android.os.RemoteException;
}
