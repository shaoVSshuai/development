package com.hzyc.website.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hzyc.website.beans.Audition;
import com.hzyc.website.beans.EmploymentNewsWithBLOBs;
import com.hzyc.website.services.EpmentSer;
import com.hzyc.website.utils.Page;

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
		Page p = new Page();
		//查询最大行数
		int allrow = es.selConditionEmpCount(enw);
		Page returnP = p.fenye(allrow+"", "1", "5");
		//设置分页属性
		enw.setMaxPage(returnP.getMaxPage());
		//查询开始行数  limit的起始字段
		enw.setStartPage(returnP.getStartPage());
		//当前页
		enw.setNowPage(returnP.getNowPage());
		enw.setPageSize(returnP.getPageSize());
		List<EmploymentNewsWithBLOBs> enList = es.selConditionEmp(enw);
		modelAndView.addObject("enList", enList);
		modelAndView.addObject("enw", enw);
		modelAndView.setViewName("../");
		return modelAndView;
	}
	/**
	 * 分页查询   上一页 下一页
	 * 
	 * @param enw
	 * @return
	 */
	@RequestMapping("/nextPage.hzyc")
	public ModelAndView nextPage(EmploymentNewsWithBLOBs enw){
		ModelAndView modelAndView = new ModelAndView();
		
		//查询试听学员
		List<EmploymentNewsWithBLOBs> eList = es.selConditionEmp(enw);
		modelAndView.addObject("eList",eList);
		//设置回传的实体属性  {分页处理使用当筛选条件}
		modelAndView.addObject("enw",enw);
		//returnP 是包含  最大页  当前页  
		modelAndView.setViewName("../");
		return modelAndView;
	}
	/**
	 * 录入
	 * @author 郑斌
	 * @param enw
	 * @return
	 */
	@RequestMapping("/insertEmp.hzyc")
	public String insertEmp(EmploymentNewsWithBLOBs enw,MultipartFile img1,MultipartFile img2,HttpServletRequest request) {
		boolean flag = false;
		try {
			flag = es.insertEmp(enw, img1, img2, request);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return flag ? "" : "";
	}
	/**
	 * 删除
	 * @author 郑斌
	 * @param id
	 * @param response
	 */
	@RequestMapping("/delEp.hzyc")
	public void delEp(int id,HttpServletResponse response) {
		boolean flag = es.delEp(id);
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(flag  ? 1 : 0);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if( out != null ){
				out.close();
			}
		}
	}
	/**
	 * 修改
	 * @author 郑斌
	 * @return
	 */
	@RequestMapping("/updateEp.hzyc")
	public String updateEp(EmploymentNewsWithBLOBs enw,MultipartFile img1,MultipartFile img2,HttpServletRequest request) {
		boolean flag = false;
		try {
			flag = es.updateEp(enw, img1, img2, request);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return flag ? "" : "";
	}
}
