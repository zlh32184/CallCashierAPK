package com.sssoft.drivers.pos.aidl;
import android.os.Bundle;
import android.graphics.Bitmap;
interface PrinterListener
{

	void onError(String code,String detail);

	void onFinish();

}