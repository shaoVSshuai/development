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

	//����������
	private static final String LOGINJSP = "login.jsp";
	private static final String LOGINACTION = "empCon/login.hzyc";
	//��Ŀ��
	private static final String PROJECTNAME = "cchzyc";
	
	public LoginFilter() {
		
        System.out.println("������ʵ����....");
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
		//��ȡsession�ж��Ƿ��¼
		HttpSession session = req.getSession();
		//�û�
		Object userObject = session.getAttribute("employee");
		//��ɫ == jobCode
		String roleid = session.getAttribute("roleid") != null ? session.getAttribute("roleid").toString() : null;
		//���̾���·��
		String path = req.getContextPath();
		String basePath = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+path+"/";
		//��ȡ�����
		PrintWriter out =  res.getWriter();
		//����·��
		String requestURI = req.getRequestURI();
		/**
		 * ��1��Ϊ��ȥ��urlǰ�ߵ� / 
		 * 		��: /cchzyc/stuManager/add.jsp ��ȡ��: stuManager/add.jsp ����·���ȶ�
		 */
		int index = requestURI.indexOf(PROJECTNAME) + PROJECTNAME.length() + 1;
		String resource = requestURI.substring(index);
		System.out.println(resource);
		chain.doFilter(request, response);
		/*//�û��Ѿ���¼,
		if(userObject != null && roleid != null ){
			*//**
			 * �����ж��Ƿ�ӵ�и���Դ��Ȩ�ޣ�����ͨ����ַ���ƹ�����δ��Ȩҳ��
			 * *//*
			
			//�û�ӵ�����Ȩ��  ����  �û���������no_access.jsp[�ܾ�����] 
			if(JobPrivliege.selPriByJobCode(roleid, resource) ){
				chain.doFilter(request, response);
			}else{
				System.out.println("[��Ȩ��..�ܾ�����...]");
				//û��Ȩ�� ��ת����Ȩ��ҳ��
				out.print("<script>window.top.location.href='" + basePath + "no_access.jsp" +"';</script>");
				out.flush();
			}
			//��ֹget��ʽaction�ķ���
			if(req.getMethod().equals("GET") && requestURI.endsWith(".hzyc")){
				out.print("<script>window.top.location.href='"+basePath+"errorPage/404.html';</script>");
			}
		}else{
			//�û�δ��¼
			System.out.println("�û�[δ��¼]");
			if(resource.trim().equals("")) {
				
				out.print("<script>window.top.location.href='" + basePath + "index/goindex.jsp" +"';</script>");
				return ;
			}
			//�����¼ҳ�治����
			if(LOGINACTION.equals(resource) || LOGINJSP.equals(resource)){
				chain.doFilter(request, response);
			}else{
				if(resource.startsWith("index")  ) {
					chain.doFilter(request, response);
					return ;
				}
				
				//����δ��¼ȫ������
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

