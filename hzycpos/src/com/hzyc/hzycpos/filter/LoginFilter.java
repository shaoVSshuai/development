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
	 * ����������
	 * */
	private static final String[] NOFILTURL = {
			"user/user_loginVerification.action",
			"login.html",
			"login.jsp",
			"no_access.html"
			
	};
	/***
	 * ��Ŀ��
	 * */
	private static final String PROJECTNAME = "hzycpos";

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        System.out.println("LoginFilter ʵ���� ... ");
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		System.out.println("LoginFilter ���� ....");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		
		// TODO Auto-generated method stub
		// place your code here
		//�û��Ƿ��¼���߼�
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;
		
		//basePath
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		HttpSession session = request.getSession(true);
		User user = (User)session.getAttribute("user");
		System.out.println("����cookie"+request.getHeader("cookie"));
		//��ȡ��ǰ����·��
		String uri = request.getRequestURI();
		System.out.println("===============�����¼������================"+uri);
		/**
		 * Ҫ��ȡ����ʼλ��     /hzycpos/index.jsp
		 * ��ȡ��  index.jsp 
		 * ��indexΪ1��ʼ������Ϊ��Խ����һ�� '/'
		 * ����ټ�1��Ϊ��Խ���ڶ��� '/'
		 * */
		int subIndex = uri.indexOf("/", 1) + 1;
		//������Դ
		String lastUrl = uri.substring(subIndex);
		System.out.println(lastUrl);
		//��¼�ɹ�
		if(user != null ){
			//����Ȩ�޵��ж�
			/*|| ��˵�û��  ����  ��˵�����  && �Լ��Ĳ˵�Ҳ��*/ 
			if( ! ps.selMenuByUrl(lastUrl) || (ps.selMenuByUrl(lastUrl) && ps.selMineUrl(lastUrl, request)) ){
				/*
				 * �Ź�
				 * */
				System.out.println("LOG: �û�ӵ�и�Ȩ��...�������");
				chain.doFilter(req, res);
				return ;
			}else{
				System.out.println("LOG: û��Ȩ�޷��ʱ��ܾ�...");
				//����˵����˵��� ����  �Լ��Ĳ˵�û�� [���ʱ��ܾ�]
				response.sendRedirect(basePath +"no_access.html");
			}
		}else {
			//û�е�¼�ɹ� ���ǵ�¼��Ҫ����Ź�
			for(String i : NOFILTURL){
				if(i.equals(lastUrl)){
					//ƥ�䵽�˲�����url
					chain.doFilter(req, res);
					return ;
				}
			}
			//�ǹ���������û��¼ֱ������
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>alert('�������Ự��ʱ,�����µ�¼');window.top.location.href='" + basePath + "login.html'</script>");
			out.flush();
			
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("LoginFilter ��ʼ�� ... ");
	}

}
