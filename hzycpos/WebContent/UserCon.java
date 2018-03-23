package cn.com.hzyc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.com.hzyc.model.User;
import cn.com.hzyc.service.UserService;

@Controller
public class UserCon {
	@Autowired
	UserService userService;

	@RequestMapping("login.action")
	public ModelAndView login(User user,HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		User user1 = userService.login(user);
		if(user1 != null){
			request.getSession().setAttribute("userinfo", user1);
			mav.setViewName("index.jsp");
		}else{
			mav.setViewName("login.jsp");
		}
		return mav;
	}
	
	
	@RequestMapping("selAllUser.action")
	public ModelAndView sellAllUser(){
		ModelAndView mav = new ModelAndView();
		List<User> userList = userService.selAllUser();
		mav.addObject("userList", userList);
		mav.setViewName("selAllUser.jsp");
		return mav;
	}
	
	@RequestMapping("regist.action")
	public void regist(HttpServletRequest request,HttpServletResponse response,User user) throws IOException{
		int flag = userService.regist(user);
		
		if(flag == 1){
			response.sendRedirect("login.jsp");
		}else{
			
			response.sendRedirect("regist.jsp");
		}
		
	}
	
	
	@RequestMapping("delUserById.action")
	public void delUserById(Integer id,HttpServletResponse response) throws IOException{
		userService.delUserById(id);
		response.sendRedirect("selAllUser.action");
	}
	
	@RequestMapping("selOneForUpd.action")
	public ModelAndView selOneForUpd(Integer id){
		ModelAndView mav = new ModelAndView();
		
		User user = userService.selOneForUpd(id);
		
		mav.addObject("user", user);
		mav.setViewName("updUser.jsp");
		return mav;
		
	}
	@RequestMapping("updUser.action")
	public void updUser(HttpServletResponse response,User user) throws IOException{
		
		userService.updUser(user);
		response.sendRedirect("selAllUser.action");
	}
}
