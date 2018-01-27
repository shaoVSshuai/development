package com.hzyc.hzycpos.system;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.hzyc.hzycpos.domain.User;

public class SessionListen implements HttpSessionListener {

	public void sessionCreated(HttpSessionEvent arg0) {
		// session创建
		System.out.println("=====session创建");
		
	}

	public void sessionDestroyed(HttpSessionEvent arg0) {
		// 这里移除application的sesion
		System.out.println("=====session销毁了");
		HttpSession session = arg0.getSession();
		User user = (User)session.getAttribute("user");
		ServletContext application = session.getServletContext();
		application.removeAttribute(user.getId() + "");
	}

}
