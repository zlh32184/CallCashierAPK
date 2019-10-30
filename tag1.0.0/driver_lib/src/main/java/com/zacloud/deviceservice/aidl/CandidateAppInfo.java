package com.zacloud.deviceservice.aidl;

import android.os.Parcel;
import android.os.Parcelable;

public class CandidateAppInfo implements Parcelable {

	private byte[] tAID = new byte[16];				// AID
	private byte[] tAppLabel = new byte[16];		// 应用标签
	private byte[] tAPN = new byte[16];				// 应用首选名
	private byte cAPID;								// 应用优先权标识符
	private byte cFlgAPID;							// 应用优先权标识符标志
	private byte[] sLangPref = new byte[8];			// 首选语言
	private byte cIssCTIndex;						// 发卡行代码表索引
	private byte cFlgIssCTIndex;					// 发卡行代码表索引标志

	public void setAID(byte[] aid) {
		tAID = aid;
	}

	public byte[] getAID() {
		return tAID;
	}

	public void setAppLabel(byte[] appLabel) {
		tAppLabel = appLabel;
	}

	public byte[] getAppLabel() {
		return tAppLabel;
	}

	public void setAPN(byte[] apn) {
		tAPN = apn;
	}

	public byte[] getAPN() {
		return tAPN;
	}

	public void setAPID(byte apid) {
		cAPID = apid;
	}

	public byte getAPID() {
		return cAPID;
	}

	public void setAPIDFlag(byte flag) {
		cFlgAPID = flag;
	}

	public byte getAPIDFlag() {
		return cFlgAPID;
	}

	public void setLangPref(byte[] langPref) {
		sLangPref = langPref;
	}

	public byte[] getLangPref() {
		return sLangPref;
	}

	public void setIssCTIndex(byte index) {
		cIssCTIndex = index;
	}

	public byte getIssCTIndex() {
		return cIssCTIndex;
	}

	public void setIssCTIndexFlag(byte flag) {
		cFlgIssCTIndex = flag;
	}

	public byte getIssCTIndexFlag() {
		return cFlgIssCTIndex;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeByteArray(tAID);
		dest.writeByteArray(tAppLabel);
		dest.writeByteArray(tAPN);
		dest.writeByte(cAPID);
		dest.writeByte(cFlgAPID);
		dest.writeByteArray(sLangPref);
		dest.writeByte(cIssCTIndex);
		dest.writeByte(cFlgIssCTIndex);
	}

	public static final Parcelable.Creator<CandidateAppInfo> CREATOR = new Parcelable.Creator<CandidateAppInfo>() {

		@Override
		public CandidateAppInfo createFromParcel(Parcel source) {
			CandidateAppInfo appInfo = new CandidateAppInfo();

			readByteArray(source.createByteArray(), appInfo.tAID);
			readByteArray(source.createByteArray(), appInfo.tAppLabel);
			readByteArray(source.createByteArray(), appInfo.tAPN);

			appInfo.cAPID = source.readByte();
			appInfo.cFlgAPID = source.readByte();

			readByteArray(source.createByteArray(), appInfo.sLangPref);

			appInfo.cIssCTIndex = source.readByte();
			appInfo.cFlgIssCTIndex = source.readByte();
			return appInfo;
		}

		@Override
		public CandidateAppInfo[] newArray(int size) {
			return new CandidateAppInfo[size];
		}

		private int getMinValue(int value1, int value2) {
			return value1 > value2 ? value2 : value1;
		}

		private void readByteArray(byte[] source, byte[] dest) {
			int arrayLen = 0;
			arrayLen = getMinValue(source.length, dest.length);
			System.arraycopy(source, 0, dest, 0, arrayLen);
		}

	};

}
