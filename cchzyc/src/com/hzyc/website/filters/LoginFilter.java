package com.hzyc.website.filters;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hzyc.website.services.InitService;
import com.hzyc.website.system.JobPrivliege;

/**
 * Servlet Filter implementation class LoginFilter
 */
@Component
public class LoginFilter implements Filter {

	@Autowired
	InitService initSer;

	//不过滤请求
	private static final String LOGINJSP = "login.jsp";
	private static final String LOGINACTION = "empCon/login.hzyc";
	//项目名
	private static final String PROJECTNAME = "cchzyc";
	
	public LoginFilter() {
		
        System.out.println("过滤器实例化....");
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//chain.doFilter(request, response);		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		//获取session判断是否登录
		HttpSession session = req.getSession();
		//用户
		Object userObject = session.getAttribute("employee");
		//角色 == jobCode
		String roleid = session.getAttribute("roleid") != null ? session.getAttribute("roleid").toString() : null;
		//工程绝对路径
		String path = req.getContextPath();
		String basePath = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+path+"/";
		//获取输出流
		PrintWriter out =  res.getWriter();
		//请求路径
		String requestURI = req.getRequestURI();
		/**
		 * 加1是为了去掉url前边的 / 
		 * 		例: /cchzyc/stuManager/add.jsp 截取后: stuManager/add.jsp 进行路径比对
		 */
		int index = requestURI.indexOf(PROJECTNAME) + PROJECTNAME.length() + 1;
		String resource = requestURI.substring(index);
		System.out.println(resource);
		chain.doFilter(request, response);
		/*//用户已经登录,
		if(userObject != null && roleid != null ){
			*//**
			 * 继续判断是否拥有该资源的权限，避免通过地址栏绕过访问未授权页面
			 * *//*
			
			//用户拥有这个权限  或者  用户被拦到了no_access.jsp[拒绝访问] 
			if(JobPrivliege.selPriByJobCode(roleid, resource) ){
				chain.doFilter(request, response);
			}else{
				System.out.println("[无权限..拒绝访问...]");
				//没有权限 跳转到无权限页面
				out.print("<script>window.top.location.href='" + basePath + "no_access.jsp" +"';</script>");
				out.flush();
			}
			//禁止get方式action的访问
			if(req.getMethod().equals("GET") && requestURI.endsWith(".hzyc")){
				out.print("<script>window.top.location.href='"+basePath+"errorPage/404.html';</script>");
			}
		}else{
			//用户未登录
			System.out.println("用户[未登录]");
			if(resource.trim().equals("")) {
				
				out.print("<script>window.top.location.href='" + basePath + "index/goindex.jsp" +"';</script>");
				return ;
			}
			//进入登录页面不过滤
			if(LOGINACTION.equals(resource) || LOGINJSP.equals(resource)){
				chain.doFilter(request, response);
			}else{
				if(resource.startsWith("index")  ) {
					chain.doFilter(request, response);
					return ;
				}
				
				//其余未登录全部过滤
				out.print("<script>window.top.location.href='" + basePath + LOGINJSP +"';</script>");
			}
			System.out.println(resource);
			
			out.flush();
		}*/
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
	
	
	public static void main(String[] args) {
		String a = "/cchzyc/index.jsp";
		int index = a.indexOf("cchzyc") + 6;
		String b = a.substring(index+1);
		System.out.println(b);
	}
}

