package com.hzyc.hzycpos.system;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.hzyc.hzycpos.domain.User;

public class SessionListen implements HttpSessionListener {

	public void sessionCreated(HttpSessionEvent arg0) {
		// session����
		System.out.println("=====session����");
		
	}

	public void sessionDestroyed(HttpSessionEvent arg0) {
		// �����Ƴ�application��sesion
		System.out.println("=====session������");
		HttpSession session = arg0.getSession();
		User user = (User)session.getAttribute("user");
		ServletContext application = session.getServletContext();
		application.removeAttribute(user.getId() + "");
	}

}
