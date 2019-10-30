/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: F:\\Test\\CallCashierAPK\\cashier_driver_lib\\src\\main\\aidl\\com\\zacloud\\deviceservice\\aidl\\UPCardListener.aidl
 */
package com.zacloud.deviceservice.aidl;
/**
 * 读手机芯片卡结果监听器
 */
public interface UPCardListener extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.zacloud.deviceservice.aidl.UPCardListener
{
private static final java.lang.String DESCRIPTOR = "com.zacloud.deviceservice.aidl.UPCardListener";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.zacloud.deviceservice.aidl.UPCardListener interface,
 * generating a proxy if needed.
 */
public static com.zacloud.deviceservice.aidl.UPCardListener asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.zacloud.deviceservice.aidl.UPCardListener))) {
return ((com.zacloud.deviceservice.aidl.UPCardListener)iin);
}
return new com.zacloud.deviceservice.aidl.UPCardListener.Stub.Proxy(obj);
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
case TRANSACTION_onRead:
{
data.enforceInterface(descriptor);
android.os.Bundle _arg0;
_arg0 = new android.os.Bundle();
this.onRead(_arg0);
reply.writeNoException();
if ((_arg0!=null)) {
reply.writeInt(1);
_arg0.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
}
else {
reply.writeInt(0);
}
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
default:
{
return super.onTransact(code, data, reply, flags);
}
}
}
private static class Proxy implements com.zacloud.deviceservice.aidl.UPCardListener
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
	 * 读卡成功回调
	 * @param data - 芯片卡卡数据
	 * <ul>
     * <li>PAN(String) - 主账号(卡号)</li>
     * <li>TRACK2(String) - 磁道2数据</li>
     * <li>TRACK3(String) - 磁道3数据</li>
     * <li>CARD_SN(String) - 卡片序列号</li>
     * <li>EXPIRED_DATE(String) - 卡片有效期</li>
     * <li>TLV_DATA(String) - 带标签(DF32、DF33、DF34)TLV数据</li>
	 * </ul>
	 */
@Override public void onRead(android.os.Bundle data) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_onRead, _data, _reply, 0);
_reply.readException();
if ((0!=_reply.readInt())) {
data.readFromParcel(_reply);
}
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
	 * 读卡错误回调
	 * @param error - 错误码
	 * <ul>
	 * <li>ERROR_DETECT_CARD(1) - 识别卡失败</li>
	 * <li>ERROR_READ_SN(2) - 读卡序列号失败</li>
	 * <li>ERROR_READ_TRACK(3) - 读卡信息失败</li>
	 * <li>ERROR_SERVICE_CRASH(4) - 设备服务异常</li>
	 * <li>ERROR_NULL_DRIVER(5) - 非接驱动为null</li>
	 * </ul>
	 * @param message - 错误描述
	 */
@Override public void onError(int error, java.lang.String message) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(error);
_data.writeString(message);
mRemote.transact(Stub.TRANSACTION_onError, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_onRead = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_onError = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
}
/**
	 * 读卡成功回调
	 * @param data - 芯片卡卡数据
	 * <ul>
     * <li>PAN(String) - 主账号(卡号)</li>
     * <li>TRACK2(String) - 磁道2数据</li>
     * <li>TRACK3(String) - 磁道3数据</li>
     * <li>CARD_SN(String) - 卡片序列号</li>
     * <li>EXPIRED_DATE(String) - 卡片有效期</li>
     * <li>TLV_DATA(String) - 带标签(DF32、DF33、DF34)TLV数据</li>
	 * </ul>
	 */
public void onRead(android.os.Bundle data) throws android.os.RemoteException;
/**
	 * 读卡错误回调
	 * @param error - 错误码
	 * <ul>
	 * <li>ERROR_DETECT_CARD(1) - 识别卡失败</li>
	 * <li>ERROR_READ_SN(2) - 读卡序列号失败</li>
	 * <li>ERROR_READ_TRACK(3) - 读卡信息失败</li>
	 * <li>ERROR_SERVICE_CRASH(4) - 设备服务异常</li>
	 * <li>ERROR_NULL_DRIVER(5) - 非接驱动为null</li>
	 * </ul>
	 * @param message - 错误描述
	 */
public void onError(int error, java.lang.String message) throws android.os.RemoteException;
}
