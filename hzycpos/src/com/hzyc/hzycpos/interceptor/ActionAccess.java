package com.hzyc.hzycpos.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class ActionAccess extends MethodFilterInterceptor{

	@Override
	protected String doIntercept(ActionInvocation arg0) throws Exception {
		// TODO Auto-generated method stub
		//System.out.println("�������ȶ�token");
		//HttpServletRequest request = (HttpServletRequest)arg0.getInvocationContext().get(StrutsStatics.HTTP_REQUEST); 
		//String userToken = request.getParameter("token");
		//Object session = request.getSession().getAttribute("token");
		///if(session != null && userToken != null){
		//	System.out.println("session��token"+session.toString());
		//	System.out.println("�û���token"+userToken);
		//	if(userToken.equals(session.toString())){
				//����ȶ�
				return arg0.invoke();
		//	}else{
				//return "error";
		//	}
		//}else{
		//	return "error";
		//}
		
		
	}

}
