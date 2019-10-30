/**
 * Copyright  2008 SilverStone Computer System Co.,Ltd
 *
 * History:
 *   2015-1-23 上午9:49:50 Created by dingwm
 */
package com.example.appsend;

import java.math.BigDecimal;


public class FormatUtil {
	
	public static String fmtWrap(String cont){
		if(cont == null || cont.trim().equals(""))
			return cont;
		return cont.replace("\r\n", "<br/>").replace("\n", "<br/>").replace("\r", "<br/>");
	}
	
	public static Double int2M(Integer size){
		return int2K(size)/1024;
	}


	public static Double int2K(Integer size){
		return size.doubleValue()/1000;
	}
	

	public static String double2StrAmt(Double amt){
		BigDecimal formatAmt = new BigDecimal(amt);
		formatAmt = formatAmt.setScale(2,BigDecimal.ROUND_HALF_UP);
		return formatAmt.toString();
	}
	

	public static String double2Str(Double amt, int scale){
		BigDecimal formatAmt = new BigDecimal(amt);
		formatAmt = formatAmt.setScale(scale,BigDecimal.ROUND_HALF_UP);
		return formatAmt.toString();
	}

	public static String lpad(int length, int number) {
        String f = "%0" + length + "d";
        return String.format(f, number);
    }
	
	public static String formatAmtFloat(String amt){
		if(amt == null || amt.trim().equals(""))
			return amt;
		return amt.replace( "," , "" );
	}
	
	public static String formatAmt2Fen(String amt){
		if(amt == null || amt.trim().equals(""))
			return amt;
		return amt.replace( "," , "" ).replace(".", "");
	}
	
	public static Double str2DoubleAmt(String amt){
		if(amt == null || amt.trim().equals(""))
			return new Double(0);
		return new Double(amt.replace(",", ""));
	}
	
	
	public static Double double2Double(Double amt,int newScale){
		BigDecimal formatAmt = new BigDecimal(amt);
		formatAmt = formatAmt.setScale(newScale,BigDecimal.ROUND_HALF_UP);
		return formatAmt.doubleValue();
	}
	

	public static String fixedLength2StrAmt(String amt){
		if(amt==null || amt.trim().equals("")){
			return amt;
		}
		BigDecimal formatAmt = new BigDecimal(new Double(amt)/100);
		formatAmt = formatAmt.setScale(2,BigDecimal.ROUND_HALF_UP);
		return formatAmt.toString();
	}
	

	public static String strDateFormat(String strDate){
		if(strDate==null) return null;
		else if(strDate.trim().equals("")) return "";
		else return strDate.replace("-", "");
	}
	

	
	public static String strToStr(String str){
		if(str==null)
			return null;
		else if(str.trim().equals(""))
			return "";
		else
			return str.replace("-", "");
	}
	

	public static String getPanLrc( String card ){
		int i,sum,len;
		int ch;	   
		String WEIGHT="121212121212121212";
		if( card.length() > 18 ) return "";
		len = 18 - card.length(); 
		for( sum=0,i=0; i<card.length(); i++ ){
			int tmp = ( card.charAt(i)-'0' )* (WEIGHT.charAt(i+len)-'0');
			sum += ( tmp % 10 ) +( tmp / 10 );
		}
		ch=(10-sum%10)%10; // (int)'0';
		return ""+ch;
	} 
	
	

	
	

	
}
