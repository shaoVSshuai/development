package com.hzyc.hzycpos.action;


import java.io.File;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.hzyc.hzycpos.domain.Vip;
import com.hzyc.hzycpos.service.VipSer;
import com.hzyc.hzycpos.util.FileConversion;
import com.opensymphony.xwork2.ActionSupport;

/**
 * ��Աҵ����
 * 
 * @author ZHENGBIN
 *
 */
@Controller
public class VipAction extends ActionSupport implements ServletRequestAware, ServletResponseAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//������
	private Vip vip;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	@Autowired
	private VipSer vs;
	
	public Vip getVip() {
		return vip;
	}

	public void setVip(Vip vip) {
		this.vip = vip;
	}
	
	/**
	 * ��ѯ�Ǵ��ۻ�Ա���Ǵ�ֵ&����
	 * 
	 * @author ZHENGBIN
	 *
	 */
	public void selType(){
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			
			String result = vs.selType();
			//������0
			if (result.equals("0")) {
				writer.print("0");
			} else if (result.equals("1")) {
				writer.print("1");
			} else{
				//û�鵽��2
				writer.print("2");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			//ǿ��ˢ��
			writer.flush();
			//�ر���Դ
			writer.close();
		}
	}
	
	/**
	 * ��vip�ĵ绰�Ž����ж�
	 * 
	 * @author ZHENGBIN
	 *
	 */
	public void selTel(){
		PrintWriter writer = null;
		try {
			String tel = request.getParameter("tel");
			writer = response.getWriter();
			boolean flag = vs.selTel(tel);
			if (flag) {
				//�鵽�˷���1
				writer.print("1");
			} else {
				writer.print("0");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			writer.flush();
			writer.close();
		}
	}

	/**
	 * ��Ա¼��
	 * 
	 * @author ZHENGBIN
	 *
	 */
	public void addVip() throws Exception{
		PrintWriter writer = null;
		try {
			//����ͼƬ
			MultiPartRequestWrapper mpRequest = (MultiPartRequestWrapper)request; 
			File[] files = mpRequest.getFiles("file");    //�ļ����ڻ�����ʱĿ¼��
			writer = response.getWriter();
			if (vip != null) {
				byte [] picture;
				if (files != null && files.length > 0) {
					picture = FileConversion.PictureConversion(files[0]);
				} else {
					//��ȡĬ��ͼƬ·��
					String path = request.getRealPath("/");
					String allPath = path + "images" +"/logo.png";
					File file = new File(allPath); 
					picture = FileConversion.PictureConversion(file);
				}
				//��ͼƬ����model
				vip.setHyPhoto(picture);
				
				boolean falg = vs.addVip(vip);
				if (falg) {
					writer.print("1");
				} else {
					writer.print("0");
				}
			} else {
				writer.print("0");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//ǿ��ˢ��
			writer.flush();
			//�ر���Դ
			writer.close();
		}
		
		
	}
	

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
		
	}

	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}

}
