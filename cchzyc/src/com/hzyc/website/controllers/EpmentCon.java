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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.hzyc.website.beans.EmploymentNewsWithBLOBs;
import com.hzyc.website.services.EpmentSer;
import com.hzyc.website.utils.Page;

@Controller
@RequestMapping("/epmentCon")
public class EpmentCon {

	@Autowired
	private EpmentSer es;
	
	/**
	 * ��������ѯ
	 * @author ֣��
	 * @param enw
	 * @return 
	 */
	@RequestMapping("/selConditionEmp.hzyc")
	public ModelAndView selConditionEmp(EmploymentNewsWithBLOBs enw) {
		System.out.println("--------------");
		ModelAndView modelAndView = new ModelAndView();
		Page p = new Page();
		//��ѯ�������
		int allrow = es.selConditionEmpCount(enw);
		Page returnP = p.fenye(allrow+"", "1", "5");
		//���÷�ҳ����
		enw.setMaxPage(returnP.getMaxPage());
		//��ѯ��ʼ����  limit����ʼ�ֶ�
		enw.setStartPage(returnP.getStartPage());
		//��ǰҳ
		enw.setNowPage(returnP.getNowPage());
		enw.setPageSize(returnP.getPageSize());
		List<EmploymentNewsWithBLOBs> enList = es.selConditionEmp(enw);
		modelAndView.addObject("enList", enList);
		modelAndView.addObject("enw", enw);
		modelAndView.setViewName("../homepageInfoMan/employmentNews/result.jsp");
		return modelAndView;
	}
	/**
	 * ��ҳ��ѯ   ��һҳ ��һҳ
	 * 
	 * @param enw
	 * @return
	 */
	@RequestMapping("/nextPage.hzyc")
	public ModelAndView nextPage(EmploymentNewsWithBLOBs enw){
		ModelAndView modelAndView = new ModelAndView();
		
		//��ѯ����ѧԱ
		List<EmploymentNewsWithBLOBs> eList = es.selConditionEmp(enw);
		modelAndView.addObject("enList",eList);
		//���ûش���ʵ������  {��ҳ����ʹ�õ�ɸѡ����}
		modelAndView.addObject("enw",enw);
		//returnP �ǰ���  ���ҳ  ��ǰҳ  
		modelAndView.setViewName("../homepageInfoMan/employmentNews/result.jsp");
		return modelAndView;
	}
	/**
	 * ¼��
	 * @author ֣��
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
		return flag ? "../homepageInfoMan/employmentNews/news_entry.jsp" : "../homepageInfoMan/employmentNews/false.jsp";
	}
	/**
	 * ɾ��
	 * @author ֣��
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
	 * �޸�
	 * @author ֣��
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
	
	
	/**
	 * ��һҳ��һҳ
	 * @param req
	 * @param res 
	 * nowPage �� ��ǰҳ
	 * Ŀǰÿҳ��ʾ4��
	 */
	@RequestMapping("fenye.hzyc")
	public void fenye(HttpServletRequest req , HttpServletResponse res ,   String nowPage) {
		System.out.println("������ҳ��");
		res.setContentType("text/html;charset=utf-8");
		int now = 1;
		if(nowPage != null) {
			try {
				now = Integer.parseInt(nowPage);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} 
		//��һҳ
		//��ʼ����
		int start = (now-1) * 4;
		//ÿҳ��ʾ����
		int pageSize = 4;
		//select * from table limit 0  ,4
		List<EmploymentNewsWithBLOBs> list = es.fenye(start , pageSize);
		Gson g = new Gson();
		String re  = g.toJson(list);
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(re);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			out.close();
		}
		
		
	}
	
	/**
	 * �޸ĵ�ʱ����ͨ��id��ѯ
	 * @param request
	 * @return
	 */
	@RequestMapping("/selById.hzyc")
	public ModelAndView selById(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		
		int id = Integer.parseInt(request.getParameter("id"));
		EmploymentNewsWithBLOBs enw = es.selById(id);
		
		modelAndView.addObject("enw",enw);
		modelAndView.setViewName("");
		
		return modelAndView;
	}
}
