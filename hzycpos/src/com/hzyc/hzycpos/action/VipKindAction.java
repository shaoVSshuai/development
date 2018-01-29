package com.hzyc.hzycpos.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.hzyc.hzycpos.domain.VipKind;
import com.hzyc.hzycpos.service.VipKindSer;
import com.opensymphony.xwork2.ActionSupport;

@Controller
public class VipKindAction extends ActionSupport implements ServletRequestAware, ServletResponseAware{

	@Autowired
	private VipKindSer vks;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private VipKind vk;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private List<VipKind> vkList;
	
	public List<VipKind> getVkList() {
		return vkList;
	}
	public void setVkList(List<VipKind> vkList) {
		this.vkList = vkList;
	}
	
	public VipKind getVk() {
		return vk;
	}
	public void setVk(VipKind vk) {
		this.vk = vk;
	}
	/**
	 * 
	 * 会员种类添加
	 * 
	 * @author ZHENGBIN
	 */
	public void addVipKind(){
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			boolean flag =vks.addVipKind(vk);
			
			if (flag) {
				//添加成功返回1
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
	 * 获取所有的会员类型，转换成Json形式
	 * 
	 * @author ZHENGBIN
	 */
	public String selVipKind() {
			//获取的所有会员类型
			this.vkList = vks.selVipKind();
			return SUCCESS;
	}
	
	@Override
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}

}
