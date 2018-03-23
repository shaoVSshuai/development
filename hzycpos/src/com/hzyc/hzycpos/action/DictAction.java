package com.hzyc.hzycpos.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.stereotype.Controller;

import com.hzyc.hzycpos.domain.Dict;
import com.hzyc.hzycpos.system.DataDict;
import com.opensymphony.xwork2.ActionSupport;

@Controller
public class DictAction extends ActionSupport implements ServletRequestAware, ServletResponseAware{

	//封装字典
	private List<Dict> dictList ; 
	//封装根据类别查询的字典数组
	private Dict[] dictArray;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private Dict[] payWayArray;
	
	//根据类别查询过滤字典
	public String selDictByType(){
		//type是字典类型  例如sex;
		String type = request.getParameter("dictType");
		Dict[] dictList = DataDict.getDictByType(type);
		dictArray = dictList;
		return SUCCESS;
	}
	
	public String selDictByPayWay() {
		//DataDict.
		this.payWayArray = DataDict.getDictByType("pay_way");
		return SUCCESS;
	}
	
	
	
	
	
	
	
	
	
	
	public Dict[] getDictArray(){
		return dictArray;
	}
	
	public List<Dict> getDictList(){
		return dictList;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		this.response = arg0;
		
	}

	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.request = arg0;
		
	}

	public Dict[] getPayWayArray() {
		return payWayArray;
	}

	public void setPayWayArray(Dict[] payWayArray) {
		this.payWayArray = payWayArray;
	}

}
