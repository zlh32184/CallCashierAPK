package com.zacloud.deviceservice.aidl;

import android.os.Parcel;
import android.os.Parcelable;

public class TusnData implements Parcelable {

	private int mTerminalType;			// 终端类型，1-存量，2-新增
	private String mMac;				// 序列号密文
	private String mTusn;				// 终端序列号

	public void setTerminalType(int terminalType) {
		mTerminalType = terminalType;
	}

	public int getTerminalType() {
		return mTerminalType;
	}

	public void setMac(String mac) {
		mMac = mac;
	}

	public String getMac() {
		return mMac;
	}

	public void setTusn(String tusn) {
		mTusn = tusn;
	}

	public String getTusn() {
		return mTusn;
	}

	public TusnData() {}

	protected TusnData(Parcel src) {
		mTerminalType = src.readInt();
		mMac = src.readString();
		mTusn = src.readString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(mTerminalType);
		dest.writeString(mMac);
		dest.writeString(mTusn);
	}

	public static final Parcelable.Creator<TusnData> CREATOR = new Parcelable.Creator<TusnData>() {

		@Override
		public TusnData createFromParcel(Parcel src) {
			return new TusnData(src);
		}

		@Override
		public TusnData[] newArray(int size) {
			return new TusnData[size];
		}

	};

}
