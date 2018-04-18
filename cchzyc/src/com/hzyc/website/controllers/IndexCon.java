package com.hzyc.website.controllers;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aliyuncs.exceptions.ClientException;
import com.hzyc.website.beans.Audition;
import com.hzyc.website.beans.Course;
import com.hzyc.website.services.AudSer;
import com.hzyc.website.system.CourseForInit;

@Controller
@RequestMapping("index")
public class IndexCon {

	@RequestMapping("/load.hzyc")
	public String load(HttpServletRequest request) {
		System.out.println("0000000-----------000000000");
		List<Course> cList = CourseForInit.getList();
		request.setAttribute("courseList", cList);
		return "sms_index.jsp";
	}

	@Autowired
	AudSer as;
	
	/**
	 * �������
	 * 
	 * @param aud
	 * @param request
	 * @param response
	 * @return String 101:��ӳɹ� 102:��֤ʧ��  103�����쳣,��ʾʧ��
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
			//����Ϊ103״̬��
			out.print(103);
		}
		out.flush();
	}
	
	@RequestMapping("/getMessage.hzyc")
	public void getMessage(HttpServletRequest request){
		
		try {
			//������֤��
			as.getMessage(request);
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
