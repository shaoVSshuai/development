package com.hzyc.website.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hzyc.website.aop.SystemLog;
import com.hzyc.website.beans.Audition;
import com.hzyc.website.services.AudSer;
import com.hzyc.website.utils.Page;

@Controller
@RequestMapping(value="audCon")
public class AuditionCon {
	
	@Autowired
	AudSer as;
	
	/**
	 * 试听学员查询【分页】
	 * 
	 * @author 邵帅
	 * @param aud 试听实体
	 * @return Boolean true为添加成功  false为添加失败
	 */
	@RequestMapping("/auditionSel.hzyc")
	public ModelAndView AuditionSel(Audition aud){
		ModelAndView mav = new ModelAndView();
		Page p = new Page();
		//查询最大行数
		int allrow = as.selAllAuditionCount(aud);
		Page returnP = p.fenye(allrow+"", "1", "5");
		System.out.println("----------s1");
		//设置分页属性
		aud.setMaxPage(returnP.getMaxPage());
		//查询开始行数  limit的起始字段
		aud.setStartPage(returnP.getStartPage());
		//当前页
		aud.setNowPage(returnP.getNowPage());
		aud.setPageSize(returnP.getPageSize());
		//查询试听学员
		List<Audition> list = as.selAllAudition(aud);
		System.out.println("----------s");
		//aud 是包含  最大页  当前页  
		mav.addObject("audition",aud);
		mav.addObject("aList",list);
		//设置回传的实体属性  {分页处理使用当筛选条件}
		mav.setViewName("../auditionMan/auditionSel/result.jsp");
		return mav;
	}
	
	/**
	 * 分页查询   上一页 下一页
	 * 
	 * @param aud
	 * @return
	 */
	@RequestMapping("/nextPage.hzyc")
	public ModelAndView nextPage(Audition aud){
		ModelAndView mav = new ModelAndView();
		
		//查询试听学员
		List<Audition> list = as.selAllAudition(aud);
		mav.addObject("aList",list);
		//设置回传的实体属性  {分页处理使用当筛选条件}
		mav.addObject("audition",aud);
		//returnP 是包含  最大页  当前页  
		mav.setViewName("../auditionMan/auditionSel/result.jsp");
		return mav;
	}
	
	/**
	 * 删除单个试听学员
	 * 
	 * @param id 学员id
	 * @param response
	 */
	@SystemLog(module="试听申请管理" , methods = "删除某个试听学员")
	@RequestMapping("/delOneAud.hzyc")
	public void  delOneAud(String id,HttpServletResponse response ){
		
		PrintWriter out = null;
		try {
			out = response.getWriter();
			boolean b = as.delOneAud(id);
			out.print(b);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
