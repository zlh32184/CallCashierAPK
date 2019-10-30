/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: F:\\Test\\CallCashierAPK\\cashier_driver_lib\\src\\main\\aidl\\com\\sssoft\\drivers\\pos\\aidl\\Printer.aidl
 */
package com.sssoft.drivers.pos.aidl;
public interface Printer extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.sssoft.drivers.pos.aidl.Printer
{
private static final java.lang.String DESCRIPTOR = "com.sssoft.drivers.pos.aidl.Printer";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.sssoft.drivers.pos.aidl.Printer interface,
 * generating a proxy if needed.
 */
public static com.sssoft.drivers.pos.aidl.Printer asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.sssoft.drivers.pos.aidl.Printer))) {
return ((com.sssoft.drivers.pos.aidl.Printer)iin);
}
return new com.sssoft.drivers.pos.aidl.Printer.Stub.Proxy(obj);
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
case TRANSACTION_addPicture:
{
data.enforceInterface(descriptor);
android.os.Bundle _arg0;
if ((0!=data.readInt())) {
_arg0 = android.os.Bundle.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
android.graphics.Bitmap _arg1;
if ((0!=data.readInt())) {
_arg1 = android.graphics.Bitmap.CREATOR.createFromParcel(data);
}
else {
_arg1 = null;
}
this.addPicture(_arg0, _arg1);
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
case TRANSACTION_paperSkip:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
this.paperSkip(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_startPrinter:
{
data.enforceInterface(descriptor);
com.sssoft.drivers.pos.aidl.PrinterListener _arg0;
_arg0 = com.sssoft.drivers.pos.aidl.PrinterListener.Stub.asInterface(data.readStrongBinder());
this.startPrinter(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_getStatus:
{
data.enforceInterface(descriptor);
java.lang.String _result = this.getStatus();
reply.writeNoException();
reply.writeString(_result);
return true;
}
default:
{
return super.onTransact(code, data, reply, flags);
}
}
}
private static class Proxy implements com.sssoft.drivers.pos.aidl.Printer
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
@Override public void addPicture(android.os.Bundle format, android.graphics.Bitmap bitmap) throws android.os.RemoteException
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
if ((bitmap!=null)) {
_data.writeInt(1);
bitmap.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_addPicture, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void addBarCode(android.os.Bundle format, java.lang.String barCode) throws android.os.RemoteException
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
_data.writeString(barCode);
mRemote.transact(Stub.TRANSACTION_addBarCode, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
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
@Override public void paperSkip(int line) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(line);
mRemote.transact(Stub.TRANSACTION_paperSkip, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void startPrinter(com.sssoft.drivers.pos.aidl.PrinterListener printerListener) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((printerListener!=null))?(printerListener.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_startPrinter, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public java.lang.String getStatus() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getStatus, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_addText = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_addPicture = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_addBarCode = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_addQrCode = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_paperSkip = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_startPrinter = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
static final int TRANSACTION_getStatus = (android.os.IBinder.FIRST_CALL_TRANSACTION + 6);
}
public void addText(android.os.Bundle format, java.lang.String text) throws android.os.RemoteException;
public void addPicture(android.os.Bundle format, android.graphics.Bitmap bitmap) throws android.os.RemoteException;
public void addBarCode(android.os.Bundle format, java.lang.String barCode) throws android.os.RemoteException;
public void addQrCode(android.os.Bundle format, java.lang.String qrCode) throws android.os.RemoteException;
public void paperSkip(int line) throws android.os.RemoteException;
public void startPrinter(com.sssoft.drivers.pos.aidl.PrinterListener printerListener) throws android.os.RemoteException;
public java.lang.String getStatus() throws android.os.RemoteException;
}
