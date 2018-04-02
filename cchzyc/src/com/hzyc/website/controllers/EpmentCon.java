package com.hzyc.website.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hzyc.website.beans.EmploymentNewsWithBLOBs;
import com.hzyc.website.services.EpmentSer;

@Controller
@RequestMapping("/epmentCon")
public class EpmentCon {

	@Autowired
	private EpmentSer es;
	
	/**
	 * 多条件查询
	 * @author 郑斌
	 * @param enw
	 * @return 
	 */
	@RequestMapping("/selConditionEmp.hzyc")
	public ModelAndView selConditionEmp(EmploymentNewsWithBLOBs enw) {
		ModelAndView modelAndView = new ModelAndView();
		List<EmploymentNewsWithBLOBs> enList = es.selConditionEmp(enw);
		modelAndView.addObject("enList", enList);
		modelAndView.setViewName("../");
		return modelAndView;
	}
	
	/**
	 * @author 郑斌
	 * @param enw
	 * @return
	 */
	@RequestMapping("/insertEmp.hzyc")
	public String insertEmp(EmploymentNewsWithBLOBs enw) {
		boolean flag = false;
		
		return flag ? "" : "";
	}
}
