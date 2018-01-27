package com.hzyc.hzycpos.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.w3c.dom.Document;
import com.hzyc.hzycpos.util.Dom;
/**
 * @author 马荣福
 */
@Service
public class PropertySer {
	
	/**
	 * @param path 要修改的xml的路径
	 * @param name 要修改的property上的name属性
	 * @param status 要修改成的状态
	 */
	public static void updateProperty(String path,String name ,String status){
		Dom d = new Dom();
		Document document = d.createDocument(path);
		d.domXML(document);
		d.updateElement(document, name, status);
		d.saveXml(document, path);
	}
	
	public void update(String[] vipset){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String path=request.getSession().getServletContext().getRealPath("WEB-INF/appConfig/config_vipSet.xml");
        System.out.println(path);
		for(int i = 0;i<vipset.length;i++){
			System.out.println(vipset[i]+"=====");
			PropertySer.updateProperty(path,vipset[i],"true");
		}
	}
	public void cache(){
		Dom d = new Dom();
		String path1 = "WebContent/WEB-INF/appConfig/config_discount.xml";
		String path2 = "WebContent/WEB-INF/appConfig/config_hotKey.xml";
		String path3 = "WebContent/WEB-INF/appConfig/config_printReceipt.xml";
		String path4 = "WebContent/WEB-INF/appConfig/config_vipIntegral.xml";
		String path5 = "WebContent/WEB-INF/appConfig/config_vipSet.xml";
		String path6 = "WebContent/WEB-INF/appConfig/config_vipSms.xml";
		
		Document document1 = d.createDocument(path1);
		d.domXML(document1);
		Document document2 = d.createDocument(path2);
		d.domXML(document2);
		Document document3 = d.createDocument(path3);
		d.domXML(document3);
		Document document4 = d.createDocument(path4);
		d.domXML(document4);
		Document document5 = d.createDocument(path5);
		d.domXML(document5);
		Document document6 = d.createDocument(path6);
		d.domXML(document6);
	}
	public static void main(String[] args) {
		String path = "WebContent/WEB-INF/appConfig/config_vipSet.xml";
		PropertySer ps = new PropertySer();
		ps.cache();
		System.out.println(Dom.ht.get("vip_discount"));
	}
}
