package com.example.TestJava;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.ByteArrayInputStream;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class XmlReader {
    public static HashMap< String, String> readXml(String nodeName, String xmlData) throws Exception {
        String encoding = "GBK";
        String[] xmls = xmlData.split("\n|\r");
        if(xmls[0].contains("encoding")){
            if(!xmls[0].contains("GBK"))
                encoding = "UTF-8";
        }
        HashMap< String, String> hashMap = new HashMap<String, String>();
        DocumentBuilder db = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder();
        Document document = db.parse(new ByteArrayInputStream(xmlData.getBytes(encoding)));// 把XML文件解析成DOCUMENT类型
        Element root = document.getDocumentElement();

        String NodeName=nodeName;                  	//自选XML中的节点名

        NodeList list = root.getElementsByTagName(NodeName);// 获得page元素
        System.out.println("list.getLength() = " + list.getLength());

        hashMap = showElem(list);
        return hashMap;
    }


    public static HashMap< String, String> showElem(NodeList nl) {
        HashMap< String, String> hashMap = new HashMap<String, String>();
        //List<String> list = new ArrayList<String>();
        for (int i = 0; i < nl.getLength(); i++) {
            Node n = nl.item(i);// 得到父节点
            // 子节点
            NodeList childList = n.getChildNodes();
            System.out.println("childList.getLength() = " + childList.getLength());
            System.out.println("childList.toString() = " + childList.toString());
            for (int x = 0; x < childList.getLength(); x++) {
                Node childNode = childList.item(x);
                // 判断取出的值是否属于Element元素,目的是过滤掉
                if (childNode instanceof Element) {
                    // 得到子节点的名字
                    String childNodeName = childNode.getNodeName();
                    // 得到子节点的值
                    String childNodeValue = childNode.getTextContent();
                    hashMap.put(childNodeName, childNodeValue);
                }
            }
        }
        return hashMap;
    }
}
