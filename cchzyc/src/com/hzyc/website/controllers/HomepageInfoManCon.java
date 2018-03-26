package com.hzyc.website.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hzyc.website.beans.Course;
import com.hzyc.website.services.HomepageSer;

@Controller
@RequestMapping("HomepageInfoManCon")
public class HomepageInfoManCon {
	
	@Autowired
	HomepageSer hs;
	
	/**
	 * @return
	 * ²éÑ¯¿Î³Ì
	 */
	@RequestMapping("/selCourse.hzyc")
	public List<Course> selCourse(){
		return hs.selCourse();
	}
}
