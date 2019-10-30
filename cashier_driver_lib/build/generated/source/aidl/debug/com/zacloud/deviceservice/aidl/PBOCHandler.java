/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: F:\\Test\\CallCashierAPK\\cashier_driver_lib\\src\\main\\aidl\\com\\zacloud\\deviceservice\\aidl\\PBOCHandler.aidl
 */
package com.zacloud.deviceservice.aidl;
/**
 * PBOC交易流程回调接口
 */
public interface PBOCHandler extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.zacloud.deviceservice.aidl.PBOCHandler
{
private static final java.lang.String DESCRIPTOR = "com.zacloud.deviceservice.aidl.PBOCHandler";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.zacloud.deviceservice.aidl.PBOCHandler interface,
 * generating a proxy if needed.
 */
public static com.zacloud.deviceservice.aidl.PBOCHandler asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.zacloud.deviceservice.aidl.PBOCHandler))) {
return ((com.zacloud.deviceservice.aidl.PBOCHandler)iin);
}
return new com.zacloud.deviceservice.aidl.PBOCHandler.Stub.Proxy(obj);
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
case TRANSACTION_onRequestAmount:
{
data.enforceInterface(descriptor);
this.onRequestAmount();
reply.writeNoException();
return true;
}
case TRANSACTION_onSelectApplication:
{
data.enforceInterface(descriptor);
java.util.List<java.lang.String> _arg0;
_arg0 = data.createStringArrayList();
this.onSelectApplication(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_onConfirmCardInfo:
{
data.enforceInterface(descriptor);
android.os.Bundle _arg0;
if ((0!=data.readInt())) {
_arg0 = android.os.Bundle.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.onConfirmCardInfo(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_onRequestInputPIN:
{
data.enforceInterface(descriptor);
boolean _arg0;
_arg0 = (0!=data.readInt());
int _arg1;
_arg1 = data.readInt();
this.onRequestInputPIN(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_onConfirmCertInfo:
{
data.enforceInterface(descriptor);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
this.onConfirmCertInfo(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_onRequestOnlineProcess:
{
data.enforceInterface(descriptor);
android.os.Bundle _arg0;
if ((0!=data.readInt())) {
_arg0 = android.os.Bundle.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.onRequestOnlineProcess(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_onTransactionResult:
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
this.onTransactionResult(_arg0, _arg1);
reply.writeNoException();
return true;
}
default:
{
return super.onTransact(code, data, reply, flags);
}
}
}
private static class Proxy implements com.zacloud.deviceservice.aidl.PBOCHandler
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
	 * 请求输入金额
	 */
@Override public void onRequestAmount() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_onRequestAmount, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
	 * 请求应用选择
	 * @param appList - 应用列表
	 */
@Override public void onSelectApplication(java.util.List<java.lang.String> appList) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStringList(appList);
mRemote.transact(Stub.TRANSACTION_onSelectApplication, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
     * 要求确认卡信息
     * @param info - 卡片信息
     * <ul>
     * <li>PAN(String) - 主账号(卡号)</li>
     * <li>TRACK2(String) - 磁道2数据</li>
     * <li>CARD_SN(String) - 卡片序列号</li>
     * <li>SERVICE_CODE(String) - 服务码</li>
     * <li>EXPIRED_DATE(String) - 卡片有效期</li>
     * </ul>
     */
@Override public void onConfirmCardInfo(android.os.Bundle info) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((info!=null)) {
_data.writeInt(1);
info.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_onConfirmCardInfo, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
     * 要求输入PIN
     * @param isOnlinePin - 是否是联机pin
     * @param retryTimes - 脱机pin的输入次数
     */
@Override public void onRequestInputPIN(boolean isOnlinePin, int retryTimes) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(((isOnlinePin)?(1):(0)));
_data.writeInt(retryTimes);
mRemote.transact(Stub.TRANSACTION_onRequestInputPIN, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
     * 要求确认持卡人证件
     * @param certType - 认证类型
     * @param certInfo - 认证信息
     */
@Override public void onConfirmCertInfo(java.lang.String certType, java.lang.String certInfo) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(certType);
_data.writeString(certInfo);
mRemote.transact(Stub.TRANSACTION_onConfirmCertInfo, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
     * 联机处理请求
     * @param aaResult - 卡片分析结果，联机数据
     * <ul>
     * <li>RESULT(int) - 结果类型，QPBOC_ARQC(201)-qPBOC联机请求，AARESULT_ARQC(2)-行为分析结果ARQC</li>
     * <li>ARQC_TLV(String) - 联机请求卡片55域数据</li>
     * <li>REVERSAL_TLV(String) - IC卡冲正TLV数据</li>
     * </ul>
     */
@Override public void onRequestOnlineProcess(android.os.Bundle aaResult) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((aaResult!=null)) {
_data.writeInt(1);
aaResult.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_onRequestOnlineProcess, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
     * PBOC交易结果，见于简易流程，qPBOC，以及交易过程失败等
     * @param result - 交易结果
     * <ul>
     * <li>EMV_COMPLETE(9) - EMV简易流程结束</li>
     * <li>EMV_ERROR(11) - EMV内核错误</li>
     * <li>EMV_FALLBACK(12) - FALLBACK</li>
     * <li>EMV_DATA_AUTH_FAIL(13) - 脱机数据认证失败</li>
     * <li>EMV_APP_BLOCKED(14) - 应用被锁定</li>
     * <li>EMV_NOT_ECCARD(15) - 非电子现金卡</li>
     * <li>EMV_UNSUPPORT_ECCARD(16) - 该交易不支持电子现金卡</li>
     * <li>EMV_AMOUNT_EXCEED_ON_PURELYEC(17) - 纯电子现金卡消费金额超限</li>
     * <li>EMV_SET_PARAM_ERROR(18) - 参数设置错误(9F7A)</li>
     * <li>EMV_PAN_NOT_MATCH_TRACK2(19) - 主账号与二磁道不符</li>
     * <li>EMV_CARD_HOLDER_VALIDATE_ERROR(20) - 持卡人认证失败</li>
     * <li>EMV_PURELYEC_REJECT(21) - 纯电子现金卡被拒绝交易</li>
     * <li>EMV_BALANCE_INSUFFICIENT(22) - 余额不足</li>
     * <li>EMV_AMOUNT_EXCEED_ON_RFLIMIT_CHECK(23) - 交易金额超过非接限额检查</li>
     * <li>EMV_CARD_BIN_CHECK_FAIL(24) - 卡BIN检查失败</li>
     * <li>EMV_CARD_BLOCKED(25) - 卡被锁</li>
     * <li>EMV_MULTI_CARD_ERROR(26) - 多卡冲突</li>
     * <li>EMV_BALANCE_EXCEED(27) - 余额超出</li>
     * <li>EMV_RFCARD_PASS_FAIL(60) - 挥卡失败</li>
     * <li>EMV_IN_QPBOC_PROCESS(99) - QPBOC流程处理中</li>
     * <li>AARESULT_TC(0) - 行为分析结果，交易批准(脱机)</li>
     * <li>AARESULT_AAC(1) - 行为分析结果，交易拒绝</li>
     * <li>QPBOC_AAC(202) - qPBOC交易结果，交易拒绝</li>
     * <li>QPBOC_ERROR(203) - qPBOC交易结果，交易失败</li>
     * <li>QPBOC_TC(204) - qPBOC交易结果，交易批准</li>
     * <li>QPBOC_CONT(205) - qPBOC结果，转接触式卡</li>
     * <li>QPBOC_NO_APP(206) - qPBOC交易结果，无应用(可转UPCard)</li>
     * <li>QPBOC_NOT_CPU_CARD(207) - qPBOC交易结果，该卡非TYPE B/PRO卡</li>
     * </ul>
     * @param data - 交易结果数据
     * <ul>
     * <li>RESULT_TLV(String) - IC卡交易批准卡片返回数据</li>
     * <li>REVERSAL_TLV(String) - IC卡冲正数据</li>
     * <li>ERROR(String) - 错误描述(PBOC流程错误返回)</li>
     * </ul>
     */
@Override public void onTransactionResult(int result, android.os.Bundle data) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(result);
if ((data!=null)) {
_data.writeInt(1);
data.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_onTransactionResult, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_onRequestAmount = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_onSelectApplication = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_onConfirmCardInfo = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_onRequestInputPIN = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_onConfirmCertInfo = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_onRequestOnlineProcess = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
static final int TRANSACTION_onTransactionResult = (android.os.IBinder.FIRST_CALL_TRANSACTION + 6);
}
/**
	 * 请求输入金额
	 */
public void onRequestAmount() throws android.os.RemoteException;
/**
	 * 请求应用选择
	 * @param appList - 应用列表
	 */
public void onSelectApplication(java.util.List<java.lang.String> appList) throws android.os.RemoteException;
/**
     * 要求确认卡信息
     * @param info - 卡片信息
     * <ul>
     * <li>PAN(String) - 主账号(卡号)</li>
     * <li>TRACK2(String) - 磁道2数据</li>
     * <li>CARD_SN(String) - 卡片序列号</li>
     * <li>SERVICE_CODE(String) - 服务码</li>
     * <li>EXPIRED_DATE(String) - 卡片有效期</li>
     * </ul>
     */
public void onConfirmCardInfo(android.os.Bundle info) throws android.os.RemoteException;
/**
     * 要求输入PIN
     * @param isOnlinePin - 是否是联机pin
     * @param retryTimes - 脱机pin的输入次数
     */
public void onRequestInputPIN(boolean isOnlinePin, int retryTimes) throws android.os.RemoteException;
/**
     * 要求确认持卡人证件
     * @param certType - 认证类型
     * @param certInfo - 认证信息
     */
public void onConfirmCertInfo(java.lang.String certType, java.lang.String certInfo) throws android.os.RemoteException;
/**
     * 联机处理请求
     * @param aaResult - 卡片分析结果，联机数据
     * <ul>
     * <li>RESULT(int) - 结果类型，QPBOC_ARQC(201)-qPBOC联机请求，AARESULT_ARQC(2)-行为分析结果ARQC</li>
     * <li>ARQC_TLV(String) - 联机请求卡片55域数据</li>
     * <li>REVERSAL_TLV(String) - IC卡冲正TLV数据</li>
     * </ul>
     */
public void onRequestOnlineProcess(android.os.Bundle aaResult) throws android.os.RemoteException;
/**
     * PBOC交易结果，见于简易流程，qPBOC，以及交易过程失败等
     * @param result - 交易结果
     * <ul>
     * <li>EMV_COMPLETE(9) - EMV简易流程结束</li>
     * <li>EMV_ERROR(11) - EMV内核错误</li>
     * <li>EMV_FALLBACK(12) - FALLBACK</li>
     * <li>EMV_DATA_AUTH_FAIL(13) - 脱机数据认证失败</li>
     * <li>EMV_APP_BLOCKED(14) - 应用被锁定</li>
     * <li>EMV_NOT_ECCARD(15) - 非电子现金卡</li>
     * <li>EMV_UNSUPPORT_ECCARD(16) - 该交易不支持电子现金卡</li>
     * <li>EMV_AMOUNT_EXCEED_ON_PURELYEC(17) - 纯电子现金卡消费金额超限</li>
     * <li>EMV_SET_PARAM_ERROR(18) - 参数设置错误(9F7A)</li>
     * <li>EMV_PAN_NOT_MATCH_TRACK2(19) - 主账号与二磁道不符</li>
     * <li>EMV_CARD_HOLDER_VALIDATE_ERROR(20) - 持卡人认证失败</li>
     * <li>EMV_PURELYEC_REJECT(21) - 纯电子现金卡被拒绝交易</li>
     * <li>EMV_BALANCE_INSUFFICIENT(22) - 余额不足</li>
     * <li>EMV_AMOUNT_EXCEED_ON_RFLIMIT_CHECK(23) - 交易金额超过非接限额检查</li>
     * <li>EMV_CARD_BIN_CHECK_FAIL(24) - 卡BIN检查失败</li>
     * <li>EMV_CARD_BLOCKED(25) - 卡被锁</li>
     * <li>EMV_MULTI_CARD_ERROR(26) - 多卡冲突</li>
     * <li>EMV_BALANCE_EXCEED(27) - 余额超出</li>
     * <li>EMV_RFCARD_PASS_FAIL(60) - 挥卡失败</li>
     * <li>EMV_IN_QPBOC_PROCESS(99) - QPBOC流程处理中</li>
     * <li>AARESULT_TC(0) - 行为分析结果，交易批准(脱机)</li>
     * <li>AARESULT_AAC(1) - 行为分析结果，交易拒绝</li>
     * <li>QPBOC_AAC(202) - qPBOC交易结果，交易拒绝</li>
     * <li>QPBOC_ERROR(203) - qPBOC交易结果，交易失败</li>
     * <li>QPBOC_TC(204) - qPBOC交易结果，交易批准</li>
     * <li>QPBOC_CONT(205) - qPBOC结果，转接触式卡</li>
     * <li>QPBOC_NO_APP(206) - qPBOC交易结果，无应用(可转UPCard)</li>
     * <li>QPBOC_NOT_CPU_CARD(207) - qPBOC交易结果，该卡非TYPE B/PRO卡</li>
     * </ul>
     * @param data - 交易结果数据
     * <ul>
     * <li>RESULT_TLV(String) - IC卡交易批准卡片返回数据</li>
     * <li>REVERSAL_TLV(String) - IC卡冲正数据</li>
     * <li>ERROR(String) - 错误描述(PBOC流程错误返回)</li>
     * </ul>
     */
public void onTransactionResult(int result, android.os.Bundle data) throws android.os.RemoteException;
}
