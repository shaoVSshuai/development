package com.hzyc.website.controllers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hzyc.website.beans.Course;
import com.hzyc.website.services.CourseSer;

@Controller
@RequestMapping("/courseCon")
public class CourseCon {
	
	@Autowired
	private CourseSer cs;
	
	/**
	 * 
	 * 
	 * @author ZHENGBIN
	 */
	@RequestMapping("/courseDisplay.hzyc")
	public ModelAndView display(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		List<Course> cList = cs.display();
		modelAndView.addObject("cList",cList);
		modelAndView.setViewName("../homepageInfoMan/result.jsp");
		return modelAndView;
	}
	
	@RequestMapping("/updateCourse.hzyc")
	public String update(Course course,MultipartFile img1,HttpServletRequest request){
		//课程信息修改
		boolean b = false;
		//获取文件字节数组
		try {
			System.out.println("----");
			b = cs.updateCourseInfo(course, img1, request);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return b ? "courseDisplay.hzyc" : "../homepageInfoMan/result.jsp";
	}
	
	@RequestMapping("/selCourseById.hzyc")
	public ModelAndView selCourseById(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		int id = Integer.parseInt(request.getParameter("id"));
		Course course = cs.selCourseById(id);
		modelAndView.addObject("course",course);
		modelAndView.setViewName("../homepageInfoMan/result_update.jsp");
		return modelAndView;
	}
}
