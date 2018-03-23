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
	 * @author ֣��
	 *
	 * 2017-12-22 ����03:50:48
	 * 
	 * @return ../recInfAnnounce/recInf/recInf_entry.jsp
	 */
	@RequestMapping("/insertInf.hzyc")
	public ModelAndView insertInf(Pos pos,HttpServletRequest request,HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView();
		
		//�����ݴ���RecInfSer����
		boolean flag = ris.insertPos(pos,request);
		if (flag) {
			//��ӳɹ�
			modelAndView.setViewName("../recInfAnnounce/recInf/recInf_entry.jsp?flag=1");
		} else {
			modelAndView.setViewName("../recInfAnnounce/recInf/recInf_entry.jsp?flag=2");
		}
		
		return modelAndView;
	}
	
	/**
	 * ��ѯid=id�Ĺ�˾��Ϣ
	 * @author ֣��
	 *
	 * 2017-12-23 ����01:18:18
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
			//ʹ�����ķ�ʽ����Ϣ���ظ�ҳ��
			PrintWriter writer = response.getWriter();
			writer.print(data);
			
			//ǿ��ˢ�º͹ر�
			writer.flush();
			writer.close();
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * ��ת��¼��ҳ��
	 * @author ֣��
	 *
	 * 2017-12-24 ����04:11:21
	 * @return ../recInfAnnounce/recInf/recInf_add.jsp
	 */
	@RequestMapping("/recInfAdd.hzyc")
	public ModelAndView recInfAdd(){
		ModelAndView modelAndView = new ModelAndView();
		//��ȡ����������Ϣ
		List<Dictionary> dList = ris.welfare();
		modelAndView.addObject("dList" , dList);
		modelAndView.setViewName("../recInfAnnounce/recInf/recInf_add.jsp");
		
		return modelAndView;
	}
	
	/**
	 * ���Ÿ�����Ϣ��ת�޸�ҳ��
	 * @author ֣��
	 * @return ../recInfAnnounce/recInf/recInf_update.jsp
	 * 2017-12-26 ����02:28:23
	 */
	@RequestMapping("/recInfUpdate.hzyc")
	public ModelAndView recInfUpdate(){
		ModelAndView modelAndView = new ModelAndView();
		//��ȡ����������Ϣ
		List<Dictionary> dList = ris.welfare();
		modelAndView.addObject("dList" , dList);
		modelAndView.setViewName("../recInfAnnounce/recInf/recInf_update.jsp");
		
		return modelAndView;
	}
	
	/**
	 * �����޸�ְλ��Ϣ
	 * @author ֣��
	 * @return ../recInfAnnounce/recInf/recInf_entry.jsp
	 * 2017-12-26 ����02:28:50
	 */
	@RequestMapping("/recUpdate.hzyc")
	public ModelAndView recUpdate(Pos pos){
		ModelAndView modelAndView = new ModelAndView();
		
		//boolean flag = ris.updatePos(pos); ��Ҫ����id
		boolean flag = false;
		if (flag) {
			//��ӳɹ�
			modelAndView.setViewName("../recInfAnnounce/recInf/recInf_entry.jsp?flag=1");
		} else {
			modelAndView.setViewName("../recInfAnnounce/recInf/recInf_entry.jsp?flag=2");
		}
		return modelAndView;
	}
	
}
