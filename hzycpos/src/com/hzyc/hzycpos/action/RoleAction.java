package com.hzyc.hzycpos.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.hzyc.hzycpos.domain.Role;
import com.hzyc.hzycpos.service.RoleSer;
import com.opensymphony.xwork2.ActionSupport;

@Controller
public class RoleAction extends ActionSupport implements ServletRequestAware, ServletResponseAware{

	@Autowired
	RoleSer rs;
	
	private List<Role> list;
	
	public List<Role> getList(){
		return this.list;
	}
	
	public String  selAll(){
		try {
			this.list = rs.selAll();
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	
	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		
	}

}
