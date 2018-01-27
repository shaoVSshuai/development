package com.hzyc.hzycpos.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.hzyc.hzycpos.domain.User;
import com.hzyc.hzycpos.service.PrivilegeSer;
import com.hzyc.hzycpos.util.BeanUtil;

/**
 * Servlet Filter implementation class LoginFilter
 */
public class LoginFilter implements Filter {
	
	static PrivilegeSer ps = null;
	
	
	
	static{
		ps = BeanUtil.getBean("privilegeSer", PrivilegeSer.class);
		
	}
	/**
	 * 不过滤请求
	 * */
	private static final String[] NOFILTURL = {
			"user/user_loginVerification.action",
			"login.html",
			"login.jsp",
			"no_access.html"
			
	};
	/***
	 * 项目名
	 * */
	private static final String PROJECTNAME = "hzycpos";

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        System.out.println("LoginFilter 实例化 ... ");
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		System.out.println("LoginFilter 销毁 ....");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		
		// TODO Auto-generated method stub
		// place your code here
		//用户是否登录的逻辑
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;
		
		//basePath
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		HttpSession session = request.getSession(true);
		User user = (User)session.getAttribute("user");
		System.out.println("过滤cookie"+request.getHeader("cookie"));
		//获取当前访问路径
		String uri = request.getRequestURI();
		System.out.println("===============进入登录过滤器================"+uri);
		/**
		 * 要截取的起始位置     /hzycpos/index.jsp
		 * 截取后  index.jsp 
		 * 从index为1开始查找是为了越过第一个 '/'
		 * 最后再加1是为了越过第二个 '/'
		 * */
		int subIndex = uri.indexOf("/", 1) + 1;
		//请求资源
		String lastUrl = uri.substring(subIndex);
		System.out.println(lastUrl);
		//登录成功
		if(user != null ){
			//进行权限的判断
			/*|| 大菜单没有  或者  大菜单里有  && 自己的菜单也有*/ 
			if( ! ps.selMenuByUrl(lastUrl) || (ps.selMenuByUrl(lastUrl) && ps.selMineUrl(lastUrl, request)) ){
				/*
				 * 放过
				 * */
				System.out.println("LOG: 用户拥有该权限...允许访问");
				chain.doFilter(req, res);
				return ;
			}else{
				System.out.println("LOG: 没有权限访问被拒绝...");
				//否则说明大菜单有 但是  自己的菜单没有 [访问被拒绝]
				response.sendRedirect(basePath +"no_access.html");
			}
		}else {
			//没有登录成功 但是登录须要特殊放过
			for(String i : NOFILTURL){
				if(i.equals(lastUrl)){
					//匹配到了不过滤url
					chain.doFilter(req, res);
					return ;
				}
			}
			//是过滤请求并且没登录直接拦截
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>alert('服务器会话超时,请重新登录');window.top.location.href='" + basePath + "login.html'</script>");
			out.flush();
			
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("LoginFilter 初始化 ... ");
	}

}
