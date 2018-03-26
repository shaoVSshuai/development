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
