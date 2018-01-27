package com.hzyc.hzycpos.test;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


import org.w3c.dom.Document;
import org.w3c.dom.NodeList;



public class Bin {

	public static void main(String[] args) {
		
		   
		   try {   
		     File f = new File("WebContent/WEB-INF/appConfig/config.xml");   
		     DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();   
		     DocumentBuilder builder = factory.newDocumentBuilder();   
		     Document doc = builder.parse(f);   
		     NodeList nl = doc.getElementsByTagName("property");  
		     NodeList n1 = doc.getElementsByTagName("param");  
		     NodeList n2 = doc.getElementsByTagName("param-value");  
		     for (int j = 0; j < nl.getLength(); j++){
		    	 for (int i = 0; i < n1.getLength() || i < n2.getLength(); i++) {   
		    		 System.out.print("功能名:"+ n1.item(i).getFirstChild().getNodeValue() + "  ");   
		    		 System.out.println("是否开启:"+ n2.item(i).getFirstChild().getNodeValue()); 
			     }   
		     }
		    } catch (Exception e) {   
		     e.printStackTrace();   
		    }  
		
	}
}
