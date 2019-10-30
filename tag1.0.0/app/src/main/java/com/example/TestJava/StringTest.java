package com.example.TestJava;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringTest {
    public static void main(String[] args) {
        String s1 = new String("Hello");
        String s2 = new String("Hello");
        System.out.print(s1 == s2);
        System.out.print(",");
        System.out.println(s1.equals(s2 ));

        System.out.println("compareDouble(1.00,0.00) = " + compareDouble(1.00, 1.005));

        System.out.println("Integer: " + Integer.SIZE/8);           // 4
        System.out.println("Short: " + Short.SIZE/8);               // 2
        System.out.println("Long: " + Long.SIZE/8);                 // 8
        System.out.println("Byte: " + Byte.SIZE/8);                 // 1
        System.out.println("Character: " + Character.SIZE/8);       // 2
        System.out.println("Float: " + Float.SIZE/8);               // 4
        System.out.println("Double: " + Double.SIZE/8);             // 8
        System.out.println("Boolean: " + Boolean.toString(false));

        System.out.println("================================");
        int first = 100;
        int second = 100;

        first = second++;
        second = ++first;
        System.out.println(str2DoubleAmt("0.00")<=0.005);


        System.out.println(strToStr("150101","HHmmss", "HH:mm:ss"));
    }


    public static Double str2DoubleAmt(String amt){
        if(amt == null || amt.trim().equals(""))
            return new Double(0);
        return new Double(amt.replace(",", ""));
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
            e.printStackTrace();
            return null;
        }
    }

    public static int compareDouble(double v1, double v2) {

        BigDecimal bigDecimal1 = new BigDecimal(v1);
        BigDecimal bigDecimal2 = new BigDecimal(v2);

        return bigDecimal1.compareTo(bigDecimal2);
    }
}
