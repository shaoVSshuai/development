package com.hzyc.hzycpos.action;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.github.pagehelper.PageInfo;
import com.hzyc.hzycpos.domain.Privilege;
import com.hzyc.hzycpos.domain.PromptMessage;
import com.hzyc.hzycpos.domain.User;
import com.hzyc.hzycpos.service.PrivilegeSer;
import com.hzyc.hzycpos.service.UserSer;
import com.hzyc.hzycpos.system.PromptMan;
import com.hzyc.hzycpos.util.FileConversion;
import com.opensymphony.xwork2.ActionSupport;

/**
 * �û����Ʋ�
 * ͨ��ʵ���Զ�����ӿ�ʵ��IOC��ʽ��requestע��
 * 
 * @author ZHENGBIN
 */
@Controller
public class UserAction extends ActionSupport implements ServletRequestAware, ServletResponseAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//model��
	private User user;
	
	//��װ��ʾ��Ϣ
	private PromptMessage pm;
	
	//�û���ѯ��
	private List<User> userList;
	//��ҳ��Ϣ��
	private PageInfo<?> pageInfo;
	
	public PageInfo<?> getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo<?> pageInfo) {
		this.pageInfo = pageInfo;
	}
	//�û����֤ͼƬ
	private File cardImgFace;
	//����
	private File cardImgReverse; 
	
	public void setCardImgFace(File cardImgFace) {
		this.cardImgFace = cardImgFace;
	}

	public void setCardImgReverse(File cardImgReverse) {
		this.cardImgReverse = cardImgReverse;
	}
	@Autowired
	private UserSer userSer;
	@Autowired
	private PrivilegeSer privilegeSer;

	private HttpServletRequest request;
	private HttpServletResponse response;
	
	//��װ�û�Ȩ����Ϣ
	private List<Privilege> pList;
	
	//��֤�û����Ƿ��ظ�
	public String validUserName(){
		String username = request.getParameter("userName");
		if(username != null){
			boolean b = userSer.validUserName(username);
			PromptMessage pm = null;
			//�ظ���
			if(b){
				pm = PromptMan.convert(107);
			}else{
				pm = PromptMan.convert(106);
			}
			this.pm = pm;
			return SUCCESS;
		}else{
			return ERROR;
		}
	}
	
	//��ѯ�û� ��������ѯ
	public String selective(){
		List<User> list = userSer.selectice(user,pageInfo);
		String a = request.getParameter("user.trueName");
		System.out.println(a);
		System.out.println(user.getTrueName()+"====");
		
		this.userList  = list;
		//������ʾ��ҳ��(����ҳ)
		pageInfo = new PageInfo<>(list , 5); 
		System.out.println(pageInfo.getNavigatepageNums().length);
		return SUCCESS;
	}
	
	
	
	//¼���û�
	public String insert(){
		try {
			//���֤����
			userSer.insertUser(user , cardImgFace , cardImgReverse);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		} 
	}
	
	//ɾ���û�
	public String delete(){
		try {
			int id = Integer.valueOf(request.getParameter("id"));
			userSer.deleteUser(id);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		} 
	}
	
	
	/**
	 * �û���¼
	 * 
	 * @author ZHENGBIN
	 */
	public String loginVerification()throws Exception{
		long a=System.currentTimeMillis();
		this.user = new User();
		//��ȡ�û���������
		String username = request.getParameter("userName");
		String password = request.getParameter("password");
		//��ӵ�user��
		user.setUname(username);
		user.setPwd(password);
		
		user = userSer.loginVerification(user);
		response.setHeader("Access-Control-Allow-Origin", "*");
		//�ж��û��������Ƿ���ȷ
		if (user != null) {
			//�����˺��ظ���¼�ж��Լ���Ϣ����
			userSer.userSession(request,response,user);
			//��ȷ������û�-��ɫ-Ȩ�޲�ѯ
			//ͨ���˺��˺Ż�ȡȨ����Ϣ
			this.pList = privilegeSer.sel(user,request);
			
			System.out.println("\r<br>ִ�к�ʱ : "+(System.currentTimeMillis()-a)/1000f+" �� ");
			//��ת��ҳ��
			System.out.println("��¼�ɹ�������������������");
			PromptMessage pm = PromptMan.convert(102);
			this.pm = pm;
			return SUCCESS;
		} else {
			//ʧ����ʧ����Ϣ����
			System.out.println("��¼ʧ�ܣ�����������������");
			PromptMessage pm = PromptMan.convert(104);
			this.pm= pm;
			//���ص�¼ҳ��
			return ERROR;
		}
		
	}
	

	//���û�Ȩ����Ϣ
	public List<Privilege> getpList() {
		return pList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	//get��message��Ϣ
	public PromptMessage getPm(){
		return this.pm;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}	
	public List<User> getUserList() {
		return userList;
	}


}
