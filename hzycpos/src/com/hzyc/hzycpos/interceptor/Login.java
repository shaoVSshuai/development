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
		System.out.println("login拦截器实例化.......");
	}
	/**
	 * 不过滤请求
	 * */
	private static final String[] NOFILTURL = {
			"user/user_loginVerification",
			"login.html",
			"login.jsp",
			"no_access.html"
	};
	/***
	 * 项目名
	 * */
	private static final String PROJECTNAME = "hzycpos";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		// 确认Session中是否存在LOGIN
		 ActionContext actionContext = invocation.getInvocationContext();   
	     HttpServletRequest request= (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST); 
	     HttpSession session = request.getSession(true);
			User user = (User)session.getAttribute("user");
			
			//获取当前访问路径
			String uri = request.getRequestURI();
			System.out.println("===============进入拦截器================"+uri);
			/**
			 * 要截取的起始位置     /hzycpos/index.jsp
			 * 截取后  index.jsp 
			 * 从index为1开始查找是为了越过第一个 '/'
			 * 最后再加1是为了越过第二个 '/'
			 * */
			int subIndex = uri.indexOf("/", 1) + 1;
			//请求资源
			String lastUrl = uri.substring(subIndex);
			System.out.println(user);
			//登录成功
			if(user != null ){
				//进行权限的判断
				/*|| 大菜单没有  或者  大菜单里有  && 自己的菜单也有*/ 
				if( ! ps.selMenuByUrl(lastUrl) || (ps.selMenuByUrl(lastUrl) && ps.selMineUrl(lastUrl, request)) ){
					/*
					 * 放过
					 * */
					System.out.println("LOG: 用户拥有该权限...允许访问");
					//放过
					return invocation.invoke();
				}else{
					//否则说明大菜单有 但是  自己的菜单没有 [访问被拒绝]
					return "no_access";
				}
			}else {
				//没有登录成功 但是登录须要特殊放过
				for(String i : NOFILTURL){
					if(i.equals(lastUrl)){
						//匹配到了不过滤url
						invocation.invoke();
					}
				}
				//是过滤请求并且没登录直接拦截
				return "login";
			}
	
	}

}
