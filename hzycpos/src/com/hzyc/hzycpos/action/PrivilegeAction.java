package com.hzyc.hzycpos.action;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.hzyc.hzycpos.domain.Privilege;
import com.hzyc.hzycpos.domain.PromptMessage;
import com.hzyc.hzycpos.service.ConfigSer;
import com.hzyc.hzycpos.service.FuncSer;
import com.hzyc.hzycpos.service.PrivilegeSer;
import com.hzyc.hzycpos.system.PromptMan;
import com.opensymphony.xwork2.ActionSupport;


@Controller
public class PrivilegeAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {
	public PrivilegeAction(){
		
	}
	
	private Privilege privilege;
	private HttpServletRequest request;
	private HttpServletResponse response;
	//权限列表
	private List<Privilege> pList;
	//提示信息
	private PromptMessage pm;
	@Autowired
	PrivilegeSer ps;
	@Autowired
	ConfigSer cs;
	
	public Privilege getPrivilege() {
		return privilege;
	}

	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}
	private static final long serialVersionUID = 1L;
	
	public String setPrivilege() throws Exception{
		//页面上功能的多选框
		String[] funcId = request.getParameterValues("func");
		
		//会员卡号生成规则config
		String vipCodeType = request.getParameter("vip_code_type");
		
		//会员类型config
		String vipType = request.getParameter("vip_type");
		
		//会员短信privilege 12
		String vipMessage = request.getParameter("vip_message");
		
		//会员积分privilege 11
		String vipIntegral =  request.getParameter("vip_integral");
		
		cs.updateConfig(funcId,vipCodeType,vipType,vipMessage,vipIntegral);
		return SUCCESS;
	}
	
	//查询一级菜单
	public String selLevel1(){
		List<Privilege> pList = ps.selLevel1();
		this.pList = pList;
		
		return SUCCESS;
	}
	
	//查询2级菜单
	public String selLevel2(){
		String pid = request.getParameter("pid");
		List<Privilege> pList = ps.selLevel2(pid);
		this.pList = pList;
		
		return SUCCESS;
	}

	/**
	 * 菜单排序
	 * */
	public String sort(){
		String str = request.getParameter("canshu");
		System.out.println("参数是"+str);
		String[] arr1 = str.split(",");
		//直接扔到treeMap中 给我排序了得了
		Map<Integer,String> sortMap = new TreeMap<Integer,String>();
		for(String a1 : arr1){
			String arr2[]  = a1.split("-");
			sortMap.put(Integer.parseInt(arr2[0]) ,arr2[1]);
		}
		System.out.println(sortMap.toString());
		boolean b = ps.sort(sortMap);
		if(b){
			//加载成功
			PromptMessage pm  = PromptMan.convert(102);
			this.pm = pm;
			return SUCCESS;
		}else{
			PromptMessage pm = PromptMan.convert(103);
			this.pm = pm;
			return ERROR;
		}
	}
	
	public List<Privilege> getPList(){
		return pList;
	}
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
		this.response.setHeader("Access-Control-Allow-Origin", "*");
		
	}
	//获取提示信息
	public PromptMessage getPm(){
		return this.pm;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request= request;
	}
	
}
