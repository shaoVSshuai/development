package com.hzyc.website.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public ModelAndView display() {
		ModelAndView modelAndView = new ModelAndView();
		List<Course> cList = cs.display();
		System.out.println("-------------");
		modelAndView.addObject("cList",cList);
		modelAndView.setViewName("../homepageInfoMan/result.jsp");
		return modelAndView;
	}

}
