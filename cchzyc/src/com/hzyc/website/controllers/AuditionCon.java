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
	 * ����ѧԱ��ѯ����ҳ��
	 * 
	 * @author ��˧
	 * @param aud ����ʵ��
	 * @return Boolean trueΪ��ӳɹ�  falseΪ���ʧ��
	 */
	@RequestMapping("/auditionSel.hzyc")
	public ModelAndView AuditionSel(Audition aud){
		ModelAndView mav = new ModelAndView();
		Page p = new Page();
		//��ѯ�������
		int allrow = as.selAllAuditionCount(aud);
		Page returnP = p.fenye(allrow+"", "1", "5");
		System.out.println("----------s1");
		//���÷�ҳ����
		aud.setMaxPage(returnP.getMaxPage());
		//��ѯ��ʼ����  limit����ʼ�ֶ�
		aud.setStartPage(returnP.getStartPage());
		//��ǰҳ
		aud.setNowPage(returnP.getNowPage());
		aud.setPageSize(returnP.getPageSize());
		//��ѯ����ѧԱ
		List<Audition> list = as.selAllAudition(aud);
		System.out.println("----------s");
		//aud �ǰ���  ���ҳ  ��ǰҳ  
		mav.addObject("audition",aud);
		mav.addObject("aList",list);
		//���ûش���ʵ������  {��ҳ����ʹ�õ�ɸѡ����}
		mav.setViewName("../auditionMan/auditionSel/result.jsp");
		return mav;
	}
	
	/**
	 * ��ҳ��ѯ   ��һҳ ��һҳ
	 * 
	 * @param aud
	 * @return
	 */
	@RequestMapping("/nextPage.hzyc")
	public ModelAndView nextPage(Audition aud){
		ModelAndView mav = new ModelAndView();
		
		//��ѯ����ѧԱ
		List<Audition> list = as.selAllAudition(aud);
		mav.addObject("aList",list);
		//���ûش���ʵ������  {��ҳ����ʹ�õ�ɸѡ����}
		mav.addObject("audition",aud);
		//returnP �ǰ���  ���ҳ  ��ǰҳ  
		mav.setViewName("../auditionMan/auditionSel/result.jsp");
		return mav;
	}
	
	/**
	 * ɾ����������ѧԱ
	 * 
	 * @param id ѧԱid
	 * @param response
	 */
	@SystemLog(module="�����������" , methods = "ɾ��ĳ������ѧԱ")
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
