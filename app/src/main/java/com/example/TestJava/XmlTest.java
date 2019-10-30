package com.example.TestJava;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class XmlTest {
    public static void main(String[] args) {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("Request");
        root.addElement("PayMode").setText("1");
        root.addElement("TxnAmt").setText("12.34");

        //document.setXMLEncoding("GBK");

        System.out.println("document.asXML() = " + document.asXML());

        //Element node = document.getRootElement();
        //listNodes(node);

/*        String strBuf = "101010101|102102102102102|";
        String[] xmls = strBuf.split("\\|");
        System.out.println(xmls.length+" xmls[0]"+xmls[0]+" xmls[1]"+xmls[1]);*/

        System.out.println("=============================");
        //elementMethod(node);
        // 解析节点
        String xmlBuf = document.asXML();
        try {
            Document document1 = DocumentHelper.parseText(xmlBuf);
            Element node = document1.getRootElement();
            HashMap<String, String> hashMap1 = new HashMap<String, String>();
            hashMap1 =  listNodes(node, hashMap1);

            Iterator iterator = hashMap1.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                System.out.println(entry.getKey() + ":" + entry.getValue());
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        }


    }
    /**
     * 遍历当前节点元素下面的所有(元素的)子节点
     *
     * @param node
     */
    public static  HashMap<String, String> listNodes(Element node, HashMap<String, String> hashMap) {
        String nodeName = "";

        System.out.println("当前节点的名称：：" + node.getName());
        // 获取当前节点的所有属性节点
        List<Attribute> list = node.attributes();
        // 遍历属性节点
        for (Attribute attr : list) {
            System.out.println(attr.getText() + "-----" + attr.getName()
                    + "---" + attr.getValue());
        }
        nodeName =node.getName();
        if (nodeName == null) {
            nodeName = "";
        }
        if (!(node.getTextTrim().equals("")) && !"".equals(nodeName)) {
            hashMap.put(nodeName, node.getTextTrim());
            System.out.println("文本内容：：：：" + node.getText());
        }

        // 当前节点下面子节点迭代器
        Iterator<Element> it = node.elementIterator();
        // 遍历
        while (it.hasNext()) {
            // 获取某个子节点对象
            Element e = it.next();
            // 对子节点进行遍历
            hashMap = listNodes(e,hashMap);
        }
        return hashMap;
    }

    /**
     * 介绍Element中的element方法和elements方法的使用
     *
     * @param node
     */
    public static void elementMethod(Element node) {
        // 获取node节点中，子节点的元素名称为supercars的元素节点。
        Element e = node.element("Request");
        // 获取supercars元素节点中，子节点为carname的元素节点(可以看到只能获取第一个carname元素节点)
        Element carname = e.element("carname");

        System.out.println(e.getName() + "----" + carname.getText());

        // 获取supercars这个元素节点 中，所有子节点名称为carname元素的节点 。

        List<Element> carnames = e.elements("carname");
        for (Element cname : carnames) {
            System.out.println(cname.getText());
        }

        // 获取supercars这个元素节点 所有元素的子节点。
        List<Element> elements = e.elements();

        for (Element el : elements) {
            System.out.println(el.getText());
        }

    }
}
