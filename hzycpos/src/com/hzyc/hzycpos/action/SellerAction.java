package com.hzyc.hzycpos.action;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import com.hzyc.hzycpos.domain.Seller;
import com.hzyc.hzycpos.service.SellerSer;
import com.hzyc.hzycpos.util.FileConversion;
import com.opensymphony.xwork2.ActionSupport;

/**
 * �û�������Ʋ�
 * ͨ��ʵ���Զ�����ӿ�ʵ��IOC��ʽ��requestע��
 * 
 * @author SHAOSHUAI
 * ʹ��ModelDriven<User>������ʽ�����ڴ��ݶ�����󲻷���
 * ��ʹ��set���󷽷�����
 */

@Controller
public class SellerAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

	@Autowired
	private SellerSer ss;
	
	public SellerAction(){
		System.out.println("ʵ����SellerAction...");
	}
	
	//model��
	private Seller seller;

	private HttpServletRequest request;
	private HttpServletResponse response;
	
	
	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void addSeller() throws Exception{
		PrintWriter writer = null;
		
		try {
			//ʵ����
			this.seller = new Seller();
			
			writer = response.getWriter();	
			
			MultiPartRequestWrapper mpRequest = (MultiPartRequestWrapper)request; 
			File[] files = mpRequest.getFiles("file");    //�ļ����ڻ�����ʱĿ¼��
			//�̵�logo
			byte [] picture = null;
			//�̵�����
			String name = request.getParameter("name");
			String date = request.getParameter("date");
			//�����Ϊ������в���
			if (files.length > 0 && files != null && !name.equals("")) {
				picture = FileConversion.PictureConversion(files[0]);
				seller.setName(name);
				seller.setPicture(picture);
				//����н������������
				if (!date.equals("")) {
					seller.setTrialPeriod(date);
				}
				boolean flag = ss.addSeller(seller);
				if (flag) {
					//����ɹ�����Ϊ1
					writer.print("1");
				} else {
					//���ʧ�ܷ���Ϊ0
					writer.print("0");
				}
			} else {
				//���Ϊ�շ���Ϊ0
				writer.print("0");
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			//ǿ��ˢ��
			writer.flush();
			//�ر���Դ
			writer.close();
		}
		
		
		
		
	}
	
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}

}
