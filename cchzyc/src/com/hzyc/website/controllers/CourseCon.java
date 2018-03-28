package com.hzyc.website.controllers;

import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
	public ModelAndView display(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		List<Course> cList = cs.display();
		for (int i=0; i<cList.size(); i++) {
			if (cList.get(i).getIcon() != null && !cList.get(i).getIcon().equals("")) {
				FileOutputStream fos;
				try {
					String path = request.getSession().getServletContext().getRealPath("/");
					String finalPathAndName = path +"images/course/"+cList.get(i).getIconName();
					fos = new FileOutputStream(finalPathAndName);
					fos.write(cList.get(i).getIcon());
					fos.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		modelAndView.addObject("cList",cList);
		modelAndView.setViewName("../homepageInfoMan/result.jsp");
		return modelAndView;
	}

}
