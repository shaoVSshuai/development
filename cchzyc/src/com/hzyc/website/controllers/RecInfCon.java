package com.hzyc.website.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.hzyc.website.beans.Company;
import com.hzyc.website.beans.Dictionary;
import com.hzyc.website.beans.Pos;
import com.hzyc.website.services.RecInfSer;





@Controller
@RequestMapping(value="recInfCon")
public class RecInfCon {

	@Autowired
	RecInfSer ris;
	

	/**
	 * @author 郑斌
	 *
	 * 2017-12-22 下午03:50:48
	 * 
	 * @return ../recInfAnnounce/recInf/recInf_entry.jsp
	 */
	@RequestMapping("/insertInf.hzyc")
	public ModelAndView insertInf(Pos pos,HttpServletRequest request,HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView();
		
		//将数据传到RecInfSer处理
		boolean flag = ris.insertPos(pos,request);
		if (flag) {
			//添加成功
			modelAndView.setViewName("../recInfAnnounce/recInf/recInf_entry.jsp?flag=1");
		} else {
			modelAndView.setViewName("../recInfAnnounce/recInf/recInf_entry.jsp?flag=2");
		}
		
		return modelAndView;
	}
	
	/**
	 * 查询id=id的公司信息
	 * @author 郑斌
	 *
	 * 2017-12-23 下午01:18:18
	 * @throws IOException 
	 */
	@RequestMapping("/selCompanyById.hzyc")
	public void selCompanyById(Company com,HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		try {
			request.setCharacterEncoding("UTF-8");
			int id = Integer.parseInt(request.getParameter("id"));
			com = ris.selCompanyById(id);
			Gson gson = new Gson();
			String data = gson.toJson(com);
			//使用流的方式将信息返回给页面
			PrintWriter writer = response.getWriter();
			writer.print(data);
			
			//强制刷新和关闭
			writer.flush();
			writer.close();
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 跳转到录入页面
	 * @author 郑斌
	 *
	 * 2017-12-24 下午04:11:21
	 * @return ../recInfAnnounce/recInf/recInf_add.jsp
	 */
	@RequestMapping("/recInfAdd.hzyc")
	public ModelAndView recInfAdd(){
		ModelAndView modelAndView = new ModelAndView();
		//获取到福利的信息
		List<Dictionary> dList = ris.welfare();
		modelAndView.addObject("dList" , dList);
		modelAndView.setViewName("../recInfAnnounce/recInf/recInf_add.jsp");
		
		return modelAndView;
	}
	
	/**
	 * 带着福利信息跳转修改页面
	 * @author 郑斌
	 * @return ../recInfAnnounce/recInf/recInf_update.jsp
	 * 2017-12-26 下午02:28:23
	 */
	@RequestMapping("/recInfUpdate.hzyc")
	public ModelAndView recInfUpdate(){
		ModelAndView modelAndView = new ModelAndView();
		//获取到福利的信息
		List<Dictionary> dList = ris.welfare();
		modelAndView.addObject("dList" , dList);
		modelAndView.setViewName("../recInfAnnounce/recInf/recInf_update.jsp");
		
		return modelAndView;
	}
	
	/**
	 * 处理修改职位信息
	 * @author 郑斌
	 * @return ../recInfAnnounce/recInf/recInf_entry.jsp
	 * 2017-12-26 下午02:28:50
	 */
	@RequestMapping("/recUpdate.hzyc")
	public ModelAndView recUpdate(Pos pos){
		ModelAndView modelAndView = new ModelAndView();
		
		//boolean flag = ris.updatePos(pos); 需要给个id
		boolean flag = false;
		if (flag) {
			//添加成功
			modelAndView.setViewName("../recInfAnnounce/recInf/recInf_entry.jsp?flag=1");
		} else {
			modelAndView.setViewName("../recInfAnnounce/recInf/recInf_entry.jsp?flag=2");
		}
		return modelAndView;
	}
	
}
