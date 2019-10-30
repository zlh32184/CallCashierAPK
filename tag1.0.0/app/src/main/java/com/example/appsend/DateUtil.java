/**
 * Copyright  2005 SilverStone System, Ltd.
 *
 * History:
 *   2007-5-9 下午04:03:18 Created by dingwm
 */
package com.example.appsend;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

/**
 * 日期时间辅助类
 * 
 * @author <a href="mailto:dingwm@ss-soft.com">dingwm</a>
 * @version 1.0 2007-5-9 下午04:03:18
 */
public class DateUtil{
	//private static Logger log = Logger.getLogger(DateUtil.class);
	/**
	 * 取得当前时间的timestamp实例
	 * @return       当前时间的timestamp实例
	 */
	public static Timestamp nowTimestamp(){
		return new Timestamp((new java.util.Date()).getTime());
	}
	
	/**
	 * 取得当前时间的毫秒数
	 * @return           当前时间的毫秒数
	 */
	public static String nowToTime(){
		return String.valueOf(new Date().getTime());
	}
	
	/**
	 * 字符串格式时间，转化为timestamp格式
	 * @param dateString        字符串格式时间
	 * @return                  timestamp格式
	 */
	public static Timestamp dateStringToTimestamp(String dateString){
		java.sql.Date date = null;
		date = java.sql.Date.valueOf(dateString);
		return new Timestamp(date.getTime());
	}
	
	/**
	 * 字符串格式转化为java.util.Date格式
	 * @param str             yyyy-MM-dd格式的字符串
	 * @return                java.util.Date对象
	 */
	public static Date strToDate(String str,String format){
		if(str == null || str.trim().equals(""))
			return null;
		DateFormat time = new SimpleDateFormat(format);
		try{
			return time.parse(str);
		}catch(ParseException e){
			//log.debug("Parse str to date has a exception :" + e);
			return null;
		}
	}
	
	/**
	 * 时间字符串格式转化为另外一种时间字符串格式
	 * @param str               要转化的字符串
	 * @param fromFormat        要转化的字符串格式
	 * @param toFormat          目标字符串的格式
	 * @return                  目标字符串
	 */
	public static String strToStr(String str,String fromFormat,String toFormat){
		if(str == null || str.trim().equals(""))
			return str;
		DateFormat fromTime = new SimpleDateFormat(fromFormat);
		DateFormat toTime = new SimpleDateFormat(toFormat);
		try{
			Date date = fromTime.parse(str);
			return toTime.format(date);
		}catch(ParseException e){
			//log.debug(""+e);
			return null;
		}
	}
	
	/**
	 * java.util.Date转化成字符串
	 * @param date            java.util.Date日期
	 * @param format          格式化参数
	 * @return                日期字符串
	 */
	public static String dateToStr(Date date,String format){
		if(date==null)
			return "";
		DateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}
	
	/**
	 * 日期往后移动i天
	 * @param date	        当前日期
	 * @param i			    移动天数
	 * @param format		日期输出格式
	 * @return String       移动后结果 
	 */
	public static String dayAddToStr(Date date,int i,String format){
		if(date==null)
				return "";
		DateFormat dateFormat = new SimpleDateFormat(format);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, i);
		return dateFormat.format(calendar.getTime());
	}
	
	//日期往后移动1天（yyyy-MM-dd）
	public static String dayAdd(String strDate){
		if(strDate == null || strDate.trim().equals(""))
			return strDate;
		return dayAddToStr(DateUtil.strToDate(strDate,"yyyy-MM-dd"),1,"yyyy-MM-dd");
	}
	/**
	 * 日期往后移动i月
	 * @param date	        当前日期
	 * @param i			    移动月份数
	 * @param format		日期输出格式
	 * @return String       移动后结果 
	 */
	public static String monthAddToStr(Date date,int i,String format){
		if(date==null)
				return "";
		DateFormat dateFormat = new SimpleDateFormat(format);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, i);
		return dateFormat.format(calendar.getTime());
	}
	public static boolean isDateTime(String time){
		boolean convertSuccess=true;
	    SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
          format.setLenient(false);
          format.parse(time);
        } catch (ParseException e) {
           convertSuccess=false;
        } 
        return convertSuccess;
	}
	
}