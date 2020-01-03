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

import com.hzyc.website.beans.Employee;
import com.hzyc.website.services.EmpSer;

@Controller
@RequestMapping("/EmployeeCon")
public class TeacherCon {
	/*
	@Autowired
	private EmpSer cs;
	
	*//**
	 * 
	 * 
	 * @author ZHENGBIN
	 *//*
	@RequestMapping("/EmployeeDisplay.hzyc")
	public ModelAndView display(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		List<Employee> cList = cs.display();
		modelAndView.addObject("cList",cList);
		modelAndView.setViewName("../homepageInfoMan/Employee/result.jsp");
		return modelAndView;
	}
	
	@RequestMapping("/updateEmployee.hzyc")
	public String update(Employee Employee,MultipartFile img1,HttpServletRequest request){
		//课程信息修改
		boolean b = false;
		//获取文件字节数组
		try {
			b = cs.updateEmployeeInfo(Employee, img1, request);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return b ? "EmployeeDisplay.hzyc" : "../homepageInfoMan/Employee/result.jsp";
	}
	
	@RequestMapping("/selEmployeeById.hzyc")
	public ModelAndView selEmployeeById(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		int id = Integer.parseInt(request.getParameter("id"));
		Employee Employee = cs.selEmployeeById(id);
		modelAndView.addObject("Employee",Employee);
		modelAndView.setViewName("../homepageInfoMan/Employee/result_update.jsp");
		return modelAndView;
	}*/
}
