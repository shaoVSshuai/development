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
 * 会员业务处理
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
	
	//参数绑定
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
	 * 查询是打折会员还是储值&打折
	 * 
	 * @author ZHENGBIN
	 *
	 */
	public void selType(){
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			
			String result = vs.selType();
			//打折是0
			if (result.equals("0")) {
				writer.print("0");
			} else if (result.equals("1")) {
				writer.print("1");
			} else{
				//没查到是2
				writer.print("2");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			//强制刷新
			writer.flush();
			//关闭资源
			writer.close();
		}
	}

	/**
	 * 会员录入
	 * 
	 * @author ZHENGBIN
	 *
	 */
	public void addVip() throws Exception{
		PrintWriter writer = null;
		try {
			//处理图片
			MultiPartRequestWrapper mpRequest = (MultiPartRequestWrapper)request; 
			File[] files = mpRequest.getFiles("file");    //文件现在还在临时目录中
			writer = response.getWriter();
			if (files.length > 0 && files != null && vip != null) {
				byte [] picture = FileConversion.PictureConversion(files[0]);
				//将图片存入model
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
			//强制刷新
			writer.flush();
			//关闭资源
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
