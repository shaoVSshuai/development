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
 * 用户管理控制层
 * 通过实现自动引入接口实现IOC方式的request注入
 * 
 * @author SHAOSHUAI
 * 使用ModelDriven<User>驱动方式，对于传递多个对象不方便
 * 改使用set对象方法传递
 */

@Controller
public class SellerAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

	@Autowired
	private SellerSer ss;
	
	public SellerAction(){
		System.out.println("实例化SellerAction...");
	}
	
	//model绑定
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
			//实例化
			this.seller = new Seller();
			
			writer = response.getWriter();	
			
			MultiPartRequestWrapper mpRequest = (MultiPartRequestWrapper)request; 
			File[] files = mpRequest.getFiles("file");    //文件现在还在临时目录中
			//商店logo
			byte [] picture = null;
			//商店名称
			String name = request.getParameter("name");
			String date = request.getParameter("date");
			//如果不为空则进行操作
			if (files.length > 0 && files != null && !name.equals("")) {
				picture = FileConversion.PictureConversion(files[0]);
				seller.setName(name);
				seller.setPicture(picture);
				//如果有节制日期则添加
				if (!date.equals("")) {
					seller.setTrialPeriod(date);
				}
				boolean flag = ss.addSeller(seller);
				if (flag) {
					//如果成功返回为1
					writer.print("1");
				} else {
					//如果失败返回为0
					writer.print("0");
				}
			} else {
				//如果为空返回为0
				writer.print("0");
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			//强制刷新
			writer.flush();
			//关闭资源
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
