package com.hzyc.hzycsms.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aliyuncs.exceptions.ClientException;
import com.hzyc.hzycsms.bean.Audition;
import com.hzyc.hzycsms.service.AudSer;
import com.hzyc.hzycsms.util.SmsUtils;

@Controller
@RequestMapping("audCon")
public class AuditionCon {
	
	@Autowired
	AudSer as;
	
	/**
	 * 添加申请
	 * 
	 * @param aud
	 * @param request
	 * @param response
	 * @return String 101:添加成功 102:验证失败  103：有异常,提示失败
	 */
	@RequestMapping("/addAudition.hzyc")
	public void addAudition(Audition aud,HttpServletRequest request,HttpServletResponse response){
		PrintWriter out  = null;
		try {
			out =  response.getWriter();
			boolean b = as.insertAudition(aud, request);
			
			out.print(b ? 101 : 102);
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			//出错为103状态码
			out.print(103);
		}
		out.flush();
		out.close();
	}
	
	@RequestMapping("/getMessage.hzyc")
	public void getMessage(HttpServletRequest request){
		
		try {
			//发送验证码
			as.getMessage(request);
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
