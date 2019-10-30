package com.zacloud.deviceservice.aidl;

public abstract interface KeyAlgorithm
{
	public static final char KA_AES = 'A';		// AES算法
	public static final char KA_DEA = 'D';		// DEA算法
	public static final char KA_TDEA = 'T';		// TDEA算法
	public static final char KA_SM4 = 'M';		// SM4算法
}
