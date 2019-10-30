/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: F:\\Test\\CallCashierAPK\\cashier_driver_lib\\src\\main\\aidl\\com\\zacloud\\deviceservice\\aidl\\IPrinter.aidl
 */
package com.zacloud.deviceservice.aidl;
/**
 * 打印机对象
 * 实现对文本、条形码、二维码和图片的打印功能。
 */
public interface IPrinter extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.zacloud.deviceservice.aidl.IPrinter
{
private static final java.lang.String DESCRIPTOR = "com.zacloud.deviceservice.aidl.IPrinter";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.zacloud.deviceservice.aidl.IPrinter interface,
 * generating a proxy if needed.
 */
public static com.zacloud.deviceservice.aidl.IPrinter asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.zacloud.deviceservice.aidl.IPrinter))) {
return ((com.zacloud.deviceservice.aidl.IPrinter)iin);
}
return new com.zacloud.deviceservice.aidl.IPrinter.Stub.Proxy(obj);
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
case TRANSACTION_getStatus:
{
data.enforceInterface(descriptor);
int _result = this.getStatus();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_setGray:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
this.setGray(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_addText:
{
data.enforceInterface(descriptor);
android.os.Bundle _arg0;
if ((0!=data.readInt())) {
_arg0 = android.os.Bundle.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
java.lang.String _arg1;
_arg1 = data.readString();
this.addText(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_addBarCode:
{
data.enforceInterface(descriptor);
android.os.Bundle _arg0;
if ((0!=data.readInt())) {
_arg0 = android.os.Bundle.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
java.lang.String _arg1;
_arg1 = data.readString();
this.addBarCode(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_addQrCode:
{
data.enforceInterface(descriptor);
android.os.Bundle _arg0;
if ((0!=data.readInt())) {
_arg0 = android.os.Bundle.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
java.lang.String _arg1;
_arg1 = data.readString();
this.addQrCode(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_addImage:
{
data.enforceInterface(descriptor);
android.os.Bundle _arg0;
if ((0!=data.readInt())) {
_arg0 = android.os.Bundle.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
byte[] _arg1;
_arg1 = data.createByteArray();
this.addImage(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_feedLine:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
this.feedLine(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_startPrint:
{
data.enforceInterface(descriptor);
com.zacloud.deviceservice.aidl.PrinterListener _arg0;
_arg0 = com.zacloud.deviceservice.aidl.PrinterListener.Stub.asInterface(data.readStrongBinder());
this.startPrint(_arg0);
reply.writeNoException();
return true;
}
default:
{
return super.onTransact(code, data, reply, flags);
}
}
}
private static class Proxy implements com.zacloud.deviceservice.aidl.IPrinter
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
@Override public int getStatus() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getStatus, _data, _reply, 0);
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
	 * 设置打印灰度
	 * @param gray - 打印灰度0~10
	 */
@Override public void setGray(int gray) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(gray);
mRemote.transact(Stub.TRANSACTION_setGray, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
	 * 添加一行打印文本
	 * @param format - 打印字体格式
	 * <ul>
	 * <li>font(int) - 0:small, 1:normal, 2:large</li>
	 * <li>align(int) - 0:left, 1:center, 2:right</li>
	 * </ul>
	 * @param text - 打印文本
	 */
@Override public void addText(android.os.Bundle format, java.lang.String text) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((format!=null)) {
_data.writeInt(1);
format.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
_data.writeString(text);
mRemote.transact(Stub.TRANSACTION_addText, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
	 * 添加条码打印
	 * @param format - 打印格式，可设置打印的位置、宽度、高度
	 * <ul>
	 * <li>align(int) - 0:left, 1:center, 2:right</li>
	 * <li>width(int) - 宽度</li>
	 * <li>height(int) - 高度</li>
	 * </ul>
	 * @param barcode - 条码内容
	 */
@Override public void addBarCode(android.os.Bundle format, java.lang.String barcode) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((format!=null)) {
_data.writeInt(1);
format.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
_data.writeString(barcode);
mRemote.transact(Stub.TRANSACTION_addBarCode, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
	 * 添加二维码打印
	 * @param format - 打印格式，可设置打印的位置、期望高度
	 * <ul>
	 * <li>offset(int) - 打印起始位置</li>
	 * <li>expectedHeight(int) - 期望高度</li>
	 * </ul>
	 * @param qrCode - 二维码内容
	 */
@Override public void addQrCode(android.os.Bundle format, java.lang.String qrCode) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((format!=null)) {
_data.writeInt(1);
format.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
_data.writeString(qrCode);
mRemote.transact(Stub.TRANSACTION_addQrCode, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
	 * 添加图片打印
	 * @param format - 打印格式，可设置打印的位置、宽度、高度
	 * <ul>
	 * <li>offset(int) - 打印起始位置</li>
	 * <li>width(int) - 宽度</li>
     * <li>height(int) - 高度</li>
     * </ul>
	 * @param imageData - 图片数据
	 */
@Override public void addImage(android.os.Bundle format, byte[] imageData) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((format!=null)) {
_data.writeInt(1);
format.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
_data.writeByteArray(imageData);
mRemote.transact(Stub.TRANSACTION_addImage, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
	 * 走纸
	 * @param lines - 行数
	 */
@Override public void feedLine(int lines) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(lines);
mRemote.transact(Stub.TRANSACTION_feedLine, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
	 * 启动打印
	 * @param listener - 打印结果监听器
	 */
@Override public void startPrint(com.zacloud.deviceservice.aidl.PrinterListener listener) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((listener!=null))?(listener.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_startPrint, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_getStatus = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_setGray = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_addText = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_addBarCode = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_addQrCode = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_addImage = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
static final int TRANSACTION_feedLine = (android.os.IBinder.FIRST_CALL_TRANSACTION + 6);
static final int TRANSACTION_startPrint = (android.os.IBinder.FIRST_CALL_TRANSACTION + 7);
}
public int getStatus() throws android.os.RemoteException;
/**
	 * 设置打印灰度
	 * @param gray - 打印灰度0~10
	 */
public void setGray(int gray) throws android.os.RemoteException;
/**
	 * 添加一行打印文本
	 * @param format - 打印字体格式
	 * <ul>
	 * <li>font(int) - 0:small, 1:normal, 2:large</li>
	 * <li>align(int) - 0:left, 1:center, 2:right</li>
	 * </ul>
	 * @param text - 打印文本
	 */
public void addText(android.os.Bundle format, java.lang.String text) throws android.os.RemoteException;
/**
	 * 添加条码打印
	 * @param format - 打印格式，可设置打印的位置、宽度、高度
	 * <ul>
	 * <li>align(int) - 0:left, 1:center, 2:right</li>
	 * <li>width(int) - 宽度</li>
	 * <li>height(int) - 高度</li>
	 * </ul>
	 * @param barcode - 条码内容
	 */
public void addBarCode(android.os.Bundle format, java.lang.String barcode) throws android.os.RemoteException;
/**
	 * 添加二维码打印
	 * @param format - 打印格式，可设置打印的位置、期望高度
	 * <ul>
	 * <li>offset(int) - 打印起始位置</li>
	 * <li>expectedHeight(int) - 期望高度</li>
	 * </ul>
	 * @param qrCode - 二维码内容
	 */
public void addQrCode(android.os.Bundle format, java.lang.String qrCode) throws android.os.RemoteException;
/**
	 * 添加图片打印
	 * @param format - 打印格式，可设置打印的位置、宽度、高度
	 * <ul>
	 * <li>offset(int) - 打印起始位置</li>
	 * <li>width(int) - 宽度</li>
     * <li>height(int) - 高度</li>
     * </ul>
	 * @param imageData - 图片数据
	 */
public void addImage(android.os.Bundle format, byte[] imageData) throws android.os.RemoteException;
/**
	 * 走纸
	 * @param lines - 行数
	 */
public void feedLine(int lines) throws android.os.RemoteException;
/**
	 * 启动打印
	 * @param listener - 打印结果监听器
	 */
public void startPrint(com.zacloud.deviceservice.aidl.PrinterListener listener) throws android.os.RemoteException;
}
