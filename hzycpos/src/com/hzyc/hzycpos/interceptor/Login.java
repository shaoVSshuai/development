package com.hzyc.hzycpos.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.StrutsStatics;

import com.hzyc.hzycpos.domain.User;
import com.hzyc.hzycpos.service.PrivilegeSer;
import com.hzyc.hzycpos.util.BeanUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class Login extends MethodFilterInterceptor{

	static PrivilegeSer ps = null;
	
	static{
		ps = BeanUtil.getBean("privilegeSer", PrivilegeSer.class);
		
	}
	
	public Login(){
		System.out.println("login������ʵ����.......");
	}
	/**
	 * ����������
	 * */
	private static final String[] NOFILTURL = {
			"user/user_loginVerification",
			"login.html",
			"login.jsp",
			"no_access.html"
	};
	/***
	 * ��Ŀ��
	 * */
	private static final String PROJECTNAME = "hzycpos";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		// ȷ��Session���Ƿ����LOGIN
		 ActionContext actionContext = invocation.getInvocationContext();   
	     HttpServletRequest request= (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST); 
	     HttpSession session = request.getSession(true);
			User user = (User)session.getAttribute("user");
			
			//��ȡ��ǰ����·��
			String uri = request.getRequestURI();
			System.out.println("===============����������================"+uri);
			/**
			 * Ҫ��ȡ����ʼλ��     /hzycpos/index.jsp
			 * ��ȡ��  index.jsp 
			 * ��indexΪ1��ʼ������Ϊ��Խ����һ�� '/'
			 * ����ټ�1��Ϊ��Խ���ڶ��� '/'
			 * */
			int subIndex = uri.indexOf("/", 1) + 1;
			//������Դ
			String lastUrl = uri.substring(subIndex);
			System.out.println(user);
			//��¼�ɹ�
			if(user != null ){
				//����Ȩ�޵��ж�
				/*|| ��˵�û��  ����  ��˵�����  && �Լ��Ĳ˵�Ҳ��*/ 
				if( ! ps.selMenuByUrl(lastUrl) || (ps.selMenuByUrl(lastUrl) && ps.selMineUrl(lastUrl, request)) ){
					/*
					 * �Ź�
					 * */
					System.out.println("LOG: �û�ӵ�и�Ȩ��...�������");
					//�Ź�
					return invocation.invoke();
				}else{
					//����˵����˵��� ����  �Լ��Ĳ˵�û�� [���ʱ��ܾ�]
					return "no_access";
				}
			}else {
				//û�е�¼�ɹ� ���ǵ�¼��Ҫ����Ź�
				for(String i : NOFILTURL){
					if(i.equals(lastUrl)){
						//ƥ�䵽�˲�����url
						invocation.invoke();
					}
				}
				//�ǹ���������û��¼ֱ������
				return "login";
			}
	
	}

}
