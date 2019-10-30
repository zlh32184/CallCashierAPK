package com.example.download;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.util.Log;
import android.util.Xml;


public class UpdataInfoParser {
	public static UpdataInfo getUpdataInfo(File file) throws Exception{
		InputStream input = new FileInputStream(file);
		XmlPullParser  parser = Xml.newPullParser();  
		parser.setInput(input, "GBK");
		int type = parser.getEventType();
		UpdataInfo info = new UpdataInfo();
		List<FileApp> fileapps = new ArrayList<FileApp>();
		while(type != XmlPullParser.END_DOCUMENT ){
			FileApp fileApp = new FileApp();
			switch (type) {
			case XmlPullParser.START_TAG:
				if("app".equals(parser.getName())){
					fileApp.setVersion(parser.getAttributeValue(0));
					fileApp.setAppCode(parser.getAttributeValue(1));
					fileApp.setAppName(parser.getAttributeValue(2));
					fileapps.add(fileApp);
				}
				break;
			}
			type = parser.next();
		}
		info.setFileapps(fileapps);
		return info;
	}
}
