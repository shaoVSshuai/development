package com.hzyc.website.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hzyc.website.beans.Course;
import com.hzyc.website.system.CourseForInit;

@Controller
public class LoadCon {
	
	@RequestMapping("/load.hzyc")
	public String load(HttpServletRequest request) {
		System.out.println("0000000-----------000000000");
		List<Course> cList = CourseForInit.getList();
		request.setAttribute("courseList", cList);
		return "sms_index.jsp";
	}
}
