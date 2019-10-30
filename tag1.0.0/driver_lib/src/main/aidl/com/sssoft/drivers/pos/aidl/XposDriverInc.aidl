package com.sssoft.drivers.pos.aidl;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.IBinder;
import com.sssoft.drivers.pos.aidl.Printer;
import com.sssoft.drivers.pos.aidl.Card;
import com.sssoft.drivers.pos.aidl.RfM1Card;
import com.sssoft.drivers.pos.aidl.InsertCard;
import com.sssoft.drivers.pos.aidl.AllCard;
import com.sssoft.drivers.pos.aidl.Scanner;

interface XposDriverInc
{
	Printer getPrinter();
	//IBinder getScanner();
	Card getCard();
	InsertCard getInsertCard();
	AllCard getAllCard();
	Scanner getScanner();
	RfM1Card getRfM1Card();
}