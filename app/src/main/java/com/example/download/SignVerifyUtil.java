/**
 * Copyright  2008 SilverStone Computer System Co.,Ltd
 *
 * History:
 *   2014-8-18 下午4:44:31 Created by dingwm
 */
package com.example.download;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import org.apaches.commons.codec.binary.Hex;
import org.apache.log4j.Logger;
import org.json.sssoft.JSONArray;
import org.json.sssoft.JSONObject;




/**
 * 签名+验签
 */
public class SignVerifyUtil {
	private static Logger log = Logger.getLogger("SignVerifyUtil");
	
	//签名
	public static String sign(JSONObject jsObj, String key){
		ArrayList<String> list = new ArrayList<String>();
		Iterator iter = jsObj.keys();
        while(iter.hasNext()){
        	String field = (String) iter.next();
        	Object value = jsObj.get(field);
        	if (!Constant.Field.SIGN.equals(field) && !(value instanceof JSONArray)) {
				list.add(field + "=" + value.toString());
			}
        }
        list.add("Key="+key);
        int size = list.size();
        String [] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i ++) {
        	if(i > 0)
        		sb.append("&");
            sb.append(arrayToSort[i]);
        }
        String result = sb.toString();
        try{
        	MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");  
  	    	byte[] bytes = messageDigest.digest(result.getBytes(Constant.DEFAULT_CHARSET));
  	    	result = Hex.encodeHexString(bytes);
        }catch(Exception e){//忽略加密方式和字符集不支持异常
        }
        return result;
	}
	
	//验签
	public static boolean verify(JSONObject jsObj,String key){
		String sign = jsObj.getString(Constant.Field.SIGN);
		if(sign == null || sign.equals("")){
			return false;
		}
		return sign.equals(sign(jsObj, key));
	}
}
