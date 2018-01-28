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
		System.out.println("xml�ļ��ĸ��ڵ�Ϊ��"+root.getNodeName());
		NodeList stuNodeList = root.getElementsByTagName("property");
		for(int i = 0;i < stuNodeList.getLength();i ++){
				
			Node stuInforNode = stuNodeList.item(i);
			if(stuInforNode.getNodeType() == Node.ELEMENT_NODE){
				
				 //��ȡ����µ�Ԫ��
				 Element element = (Element)stuNodeList.item(i);
		    	 String name = element.getAttribute("name");
	             System.out.println("name��" + name);
	             //��ȡ��Ӧ��valueֵ
	             String value = element.getAttribute("value");
	             System.out.println("name��" + value);
	             
	             ht.put(name, value);
			}
				
		}
			
	}
	
//	1���ڸ��ڵ�������һ��<student>�ڵ㣬�������snֵΪ04��
//	<student>�ڵ�����<name>��<age>�ڵ�ڵ����ݷֱ�Ϊname=������age=21���ڵ�����ӣ�
	public void addElement(Document document){
		
		Element root = document.getDocumentElement();
		
		//������ⲽ��1�������ڵ� �������� �������� 2��д������ д������ д��ڵ�
		//1������
		//����students�ڵ��Լ�students�µ�name�ڵ���age�ڵ�
		Element stuEle = document.createElement("student");
		Element nameEle = document.createElement("name");
		Element ageEle = document.createElement("age");
		//����name��age����
		Text nameText = document.createTextNode("����");
		Text ageText = document.createTextNode("21");
		//����student�ڵ�����sn=04
		Attr stuAttr = document.createAttribute("sn");
		stuAttr.setValue("04");
		
		//2��д��
		//д������
		stuEle.setAttributeNode(stuAttr);
		//��name��ageд��student��
		stuEle.appendChild(nameEle);
		stuEle.appendChild(ageEle);
		//д������
		nameEle.appendChild(nameText);
		ageEle.appendChild(ageText);
		//��studentд����ڵ���
		root.appendChild(stuEle);
		
		
	}
/**
 * @author ���ٸ�
 * @param document ���
 * @param paramName Ҫ�޸ĵ�xml��name���Ե���
 * @param status Ҫ�޸ĳɵ�״̬
 */
public void updateElement(Document document,String paramName,String status){
	
	Element root = document.getDocumentElement();
	System.out.println("xml�ļ��ĸ��ڵ�Ϊ��"+root.getNodeName());
	System.out.println();
	NodeList stuNodeList = root.getElementsByTagName("property");
	for(int i = 0;i < stuNodeList.getLength();i ++){
			
		Node stuInforNode = stuNodeList.item(i);
		if(stuInforNode.getNodeType() == Node.ELEMENT_NODE){
			
			 //��ȡ����µ�Ԫ��
			 Element element = (Element)stuNodeList.item(i);
	    	 String name = element.getAttribute("name");
             System.out.println("name��" + name);
             if(name.equals(paramName)){
            	  element.setAttribute("value", status);
             }
		}
			
	}
		
}
	//����xml�ļ�
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
