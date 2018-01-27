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
	//Ȩ���б�
	private List<Privilege> pList;
	//��ʾ��Ϣ
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
		//ҳ���Ϲ��ܵĶ�ѡ��
		String[] funcId = request.getParameterValues("func");
		
		//��Ա�������ɹ���config
		String vipCodeType = request.getParameter("vip_code_type");
		
		//��Ա����config
		String vipType = request.getParameter("vip_type");
		
		//��Ա����privilege 12
		String vipMessage = request.getParameter("vip_message");
		
		//��Ա����privilege 11
		String vipIntegral =  request.getParameter("vip_integral");
		
		cs.updateConfig(funcId,vipCodeType,vipType,vipMessage,vipIntegral);
		return SUCCESS;
	}
	
	//��ѯһ���˵�
	public String selLevel1(){
		List<Privilege> pList = ps.selLevel1();
		this.pList = pList;
		
		return SUCCESS;
	}
	
	//��ѯ2���˵�
	public String selLevel2(){
		String pid = request.getParameter("pid");
		List<Privilege> pList = ps.selLevel2(pid);
		this.pList = pList;
		
		return SUCCESS;
	}

	/**
	 * �˵�����
	 * */
	public String sort(){
		String str = request.getParameter("canshu");
		System.out.println("������"+str);
		String[] arr1 = str.split(",");
		//ֱ���ӵ�treeMap�� ���������˵���
		Map<Integer,String> sortMap = new TreeMap<Integer,String>();
		for(String a1 : arr1){
			String arr2[]  = a1.split("-");
			sortMap.put(Integer.parseInt(arr2[0]) ,arr2[1]);
		}
		System.out.println(sortMap.toString());
		boolean b = ps.sort(sortMap);
		if(b){
			//���سɹ�
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
	//��ȡ��ʾ��Ϣ
	public PromptMessage getPm(){
		return this.pm;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request= request;
	}
	
}
