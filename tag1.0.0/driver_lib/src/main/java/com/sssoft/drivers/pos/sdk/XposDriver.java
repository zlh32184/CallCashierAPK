/**
 * Copyright  2008 SilverStone Computer System Co.,Ltd
 *
 * History:
 *   2017-3-29 上午9:55:13 Created by dingwm
 */
package com.sssoft.drivers.pos.sdk;

import android.util.Log;

import com.sssoft.drivers.pos.aidl.AllCard;
import com.sssoft.drivers.pos.aidl.Card;
import com.sssoft.drivers.pos.aidl.InsertCard;
import com.sssoft.drivers.pos.aidl.Printer;
import com.sssoft.drivers.pos.aidl.RfM1Card;
import com.sssoft.drivers.pos.aidl.Scanner;
import com.sssoft.drivers.pos.bank.CcbMagCard;
import com.sssoft.drivers.pos.bank.CcbPrinter;
import com.sssoft.drivers.pos.bank.CcbScanner;
import com.sssoft.drivers.pos.common.scanner.CommomScanner;
import com.sssoft.drivers.pos.impl.aisino.A90.AisinoA90Printer;
import com.sssoft.drivers.pos.impl.aisino.A90.AisinoA90Scanner;
import com.sssoft.drivers.pos.impl.aisino.A90.AisinoMagCard;
import com.sssoft.drivers.pos.impl.hisense.hi98.Hi98AllCard;
import com.sssoft.drivers.pos.impl.hisense.hi98.Hi98Card;
import com.sssoft.drivers.pos.impl.hisense.hi98.Hi98Printer;
import com.sssoft.drivers.pos.impl.jolly.S500.JollyS500AllCard;
import com.sssoft.drivers.pos.impl.jolly.S500.JollyS500Printer;
import com.sssoft.drivers.pos.impl.landi.a8.Landia8Card;
import com.sssoft.drivers.pos.impl.landi.a8.Landia8Printer;
import com.sssoft.drivers.pos.impl.landi.a8.Landia8Scanner;
import com.sssoft.drivers.pos.impl.newland.n9.NewlandCard;
import com.sssoft.drivers.pos.impl.newland.n9.Newlandn9Printer;
import com.sssoft.drivers.pos.impl.newland.n9.Newlandn9Scanner;
import com.sssoft.drivers.pos.impl.newland.n9.RfM1CardInf;
import com.sssoft.drivers.pos.impl.verifone.X990.VerifoneCard;
import com.sssoft.drivers.pos.impl.verifone.X990.VerifoneMagCard;
import com.sssoft.drivers.pos.impl.verifone.X990.VerifoneScanner;
import com.sssoft.drivers.pos.impl.verifone.X990.VerifoneX990Printer;
import com.sssoft.drivers.pos.service.AIDLService;

/**
 * 类说明
 * @author <a href="mailto:dingwm@ss-soft.com">dingwm</a>
 * @version 1.0  2017-3-29 上午9:55:13
 */
public class XposDriver {
	private static String model = android.os.Build.MODEL;
	public static Printer getPrinter(){
		//打印	
		if(AIDLService.isBnak.equals("")){
			if(model.startsWith("APOS")){
				return new Landia8Printer();
			}else if(model.startsWith("N")){
				return new Newlandn9Printer();
			}else if(model.startsWith("HI")){
				return new Hi98Printer();
			}else if(model.startsWith("X")){
				return new VerifoneX990Printer();
			}else if(model.startsWith("A90")){
				return new AisinoA90Printer();
			}else if(model.startsWith("S500")){
				return new JollyS500Printer();
			}
		}else{
			switch (AIDLService.isBnak) {
			case "CCB":
				return new CcbPrinter();
			default:
				return null;
			}
		}
		return null;
	}
		
	public static Card getCard(){
		//刷卡
		if(AIDLService.isBnak.equals("")){
			if(model.startsWith("APOS")){
				return new Landia8Card();
			}else if(model.startsWith("HI")){
				return new Hi98Card();
			}else if(model.startsWith("N")){
				return new NewlandCard();
			}else if(model.startsWith("X")){
				return new VerifoneMagCard();
			}else if(model.startsWith("A90")){
				return new AisinoMagCard();
			}
		}else{
			switch (AIDLService.isBnak) {
			case "CCB":
				return new CcbMagCard();
			default:
				return null;
			}
		}
		return null;
	}

	public static InsertCard getInsertCard(){
		//接触卡
		if(AIDLService.isBnak.equals("")){
			if(model.startsWith("APOS")){
				//return new Landia8InsertCard();
			}
		}else{
			switch (AIDLService.isBnak) {
			case "CCB":
				return null;
			default:
				return null;
			}
		}
		return null;
	}
	public static RfM1Card getRfM1Card(){
		//非接
		if(AIDLService.isBnak.equals("")){
			if(model.startsWith("N")){
				return new RfM1CardInf();
			}
		}else{
			switch (AIDLService.isBnak) {
			case "CCB":
				return null;
			default:
				return null;
			}
		}
		return null;
	}
	public static AllCard getAllCard(){
		//统一控制所有卡
		if(AIDLService.isBnak.equals("")){
			if(model.startsWith("APOS")){
				return null;
			}else if(model.startsWith("HI")){
				return new Hi98AllCard();
			}else if(model.startsWith("X")){
				return new VerifoneCard();
			}else if(model.startsWith("S500")){
				return new JollyS500AllCard();
			}
		}else{
			switch (AIDLService.isBnak) {
			case "CCB":
				return null;
			default:
				return null;
			}
		}
		return null;
	}
	
	public static Scanner getScanner(){
		Log.i("cheny", "AIDLService.isBnak:"+AIDLService.isBnak);
		//扫码
		if(AIDLService.isBnak.equals("")){
			if(model.startsWith("N")){
				return new Newlandn9Scanner();
			}else if(model.startsWith("A90")){
				return new AisinoA90Scanner();
			}else if(model.startsWith("X")){
				return new VerifoneScanner();
			}else if(model.startsWith("APOS")){
				return new Landia8Scanner();
			}else{
				return new CommomScanner();
			}
		}else{
			switch (AIDLService.isBnak) {
			case "CCB":
				return new CcbScanner();
			default:
				return null;
			}
		}
	}
}
