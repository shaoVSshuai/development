package com.hzyc.hzycpos.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.hzyc.hzycpos.domain.storeInfo;
import com.hzyc.hzycpos.service.StoreInfoSer;
import com.opensymphony.xwork2.ActionSupport;

@Controller
public class StoreInfoAction extends ActionSupport implements ServletRequestAware, ServletResponseAware{

	@Autowired
	StoreInfoSer sis;
	
	HttpServletRequest request;
	HttpServletResponse response;
	
	//店铺集合
	private List<storeInfo> list ; 
	
	//查询所有
	public String selAll(){
		try {
			List<storeInfo> list = sis.selAll();
			this.list = list;
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	public List<storeInfo> getList(){
		return this.list;
	}
	
	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		this.response = arg0;
		
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.request = arg0;
	}
	
}
