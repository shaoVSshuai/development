package com.hzyc.hzycpos.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Hashtable;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;


public class Dom {
	public static Hashtable ht = new Hashtable();
	public Document createDocument(String path){
		
		Document document = null;
			
		try {
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			FileInputStream  xmlInputStream = new FileInputStream(path);
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
				
			document = builder.parse(xmlInputStream);
				
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return document;
			
	}
	public void domXML(Document document){
		
		Element root = document.getDocumentElement();
		System.out.println("xml文件的根节点为："+root.getNodeName());
		NodeList stuNodeList = root.getElementsByTagName("property");
		for(int i = 0;i < stuNodeList.getLength();i ++){
				
			Node stuInforNode = stuNodeList.item(i);
			if(stuInforNode.getNodeType() == Node.ELEMENT_NODE){
				
				 //获取结点下的元素
				 Element element = (Element)stuNodeList.item(i);
		    	 String name = element.getAttribute("name");
	             System.out.println("name：" + name);
	             //获取对应的value值
	             String value = element.getAttribute("value");
	             System.out.println("name：" + value);
	             
	             ht.put(name, value);
			}
				
		}
			
	}
	
//	1、在根节点下增加一个<student>节点，添加属性sn值为04，
//	<student>节点下有<name>、<age>节点节点内容分别为name=赵六，age=21（节点的增加）
	public void addElement(Document document){
		
		Element root = document.getDocumentElement();
		
		//解决问题步骤1、创建节点 创建属性 创建内容 2、写入内容 写入属性 写入节点
		//1、创建
		//创建students节点以及students下的name节点与age节点
		Element stuEle = document.createElement("student");
		Element nameEle = document.createElement("name");
		Element ageEle = document.createElement("age");
		//创建name与age内容
		Text nameText = document.createTextNode("赵六");
		Text ageText = document.createTextNode("21");
		//创建student节点属性sn=04
		Attr stuAttr = document.createAttribute("sn");
		stuAttr.setValue("04");
		
		//2、写入
		//写入属性
		stuEle.setAttributeNode(stuAttr);
		//把name、age写入student下
		stuEle.appendChild(nameEle);
		stuEle.appendChild(ageEle);
		//写入内容
		nameEle.appendChild(nameText);
		ageEle.appendChild(ageText);
		//把student写入根节点下
		root.appendChild(stuEle);
		
		
	}
/**
 * @author 马荣福
 * @param document 结点
 * @param paramName 要修改的xml的name属性的名
 * @param status 要修改成的状态
 */
public void updateElement(Document document,String paramName,String status){
	
	Element root = document.getDocumentElement();
	System.out.println("xml文件的根节点为："+root.getNodeName());
	System.out.println();
	NodeList stuNodeList = root.getElementsByTagName("property");
	for(int i = 0;i < stuNodeList.getLength();i ++){
			
		Node stuInforNode = stuNodeList.item(i);
		if(stuInforNode.getNodeType() == Node.ELEMENT_NODE){
			
			 //获取结点下的元素
			 Element element = (Element)stuNodeList.item(i);
	    	 String name = element.getAttribute("name");
             System.out.println("name：" + name);
             if(name.equals(paramName)){
            	  element.setAttribute("value", status);
             }
		}
			
	}
		
}
	//保存xml文件
	public void saveXml(Document document,String path){
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		try {
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(new FileOutputStream(path));
		    
			transformer.transform(domSource, streamResult);
			
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		/*Dom d = new Dom();
		Document document = d.createDocument();
		d.domXML(document);
		d.addElement(document);
		d.saveXml(document);*/
	}
}
