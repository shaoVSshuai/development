package com.hzyc.website.controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.hzyc.website.beans.Audition;
import com.hzyc.website.beans.Dictionary;
import com.hzyc.website.beans.StudentInfo;
import com.hzyc.website.services.StudentSer;
import com.hzyc.website.system.Dict;
import com.hzyc.website.utils.Page;

/**
 * ѧԱ��Ϣ����controller
 * 
 * @author SHAOSHUAI
 *
 */
@Controller
@RequestMapping(value="stuCon")
public class StudentCon {
	
	@Autowired
	StudentSer stuSer;
	
	/**
	 * ѧԱ��Ϣ���
	 * 
	 * @param stu  ѧԱ��Ϣʵ��
	 * @param img1   ѧԱ����ͼƬ
	 * @return ""
	 */
	//StudentInfo [id=null, code=201317030214, name=����, idcard=371325199404126910, sex=1, tel=18686314385, qq=null, wx=18686314385, province=22, city=220100, address=220102, birthday=1994-04-12, education=null, eLength=null, school=null, dept=null, major=���繤��, period=null, classes=null, classPath=null, planner=���, plannerPath=null, teacher=null, teacherPath=null, time=null, state=null, remark=null, img=null, workImg=null, imgName=null, age=0]
	//fced8a89-6345-48ee-a1b9-a0455ea3bef1.png
	@RequestMapping("/add.hzyc")
	public String add(StudentInfo student,MultipartFile img1){
		System.out.println(student);
		//ѧ����Ϣ���
		boolean b = false;
		try {
			//service
			b = stuSer.addStudentInfo(student,img1);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b ? "../stuMan/stuAdd/stu_add.jsp?flag=1" : "../stuMan/stuAdd/stu_add.jsp?flag=2";
	}
	
	/**
	 * ɾ��ѧԱ
	 * 
	 * @author ��˧
	 * @param id ѧ������
	 * @param response ajax������Ӧ
	 */
	@RequestMapping("/delStudent.hzyc")
	public void delStudent(String id,HttpServletResponse response){
		boolean i = stuSer.deleteStudentInfo(id);
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(i  ? 1 : 0);
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
	 * ѧԱ��Ϣ�޸�
	 * 
	 * @author ��˧
	 * @param stu
	 * @param req
	 * @return
	 */
	@RequestMapping("/updStudentInfo.hzyc")
	public String updStudentInfo(StudentInfo stu,MultipartFile img1,HttpServletRequest req){
		//ѧ����Ϣ�޸�
		boolean b = false;
		//��ȡ�ļ��ֽ�����
		try {
			
			b = stuSer.updateStudentInfo(stu, img1, req);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return b ? "../../success.jsp" : "../../fail.jsp";
	}
	
	/**
	 * ��ѯѧԱ
	 * 
	 * @author ��˧
	 * @param stu ѧ����Ϣʵ��
	 * @return stuMan/stu_sel_result.jsp ��ѯ�����ͼ
	 */
	@RequestMapping("/selectiveStudent.hzyc")
	public ModelAndView selectiveStudent(StudentInfo stu){
		ModelAndView mav = new ModelAndView();
		List<StudentInfo> list =  stuSer.selective(stu);
		mav.addObject("stuList",list);
		//��ѯ�����ͼ
		mav.setViewName("../stuMan/stuSel/stu_sel_result.jsp");
		return mav;
	}
	
	/**
	 * ��ҳ��ѯ
	 * @param stu
	 * @return
	 */
	@RequestMapping("/selectiveByPage.hzyc")
	public ModelAndView selectiveByPage(StudentInfo stu){
		ModelAndView mav = new ModelAndView();
		Page p = new Page();
		//��ѯ�������
		int allrow = stuSer.selAllCount(stu);
		Page returnP = p.fenye(allrow+"", "1", "5");
		//���÷�ҳ����
		stu.setMaxPage(returnP.getMaxPage());
		//��ѯ��ʼ����  limit����ʼ�ֶ�
		stu.setStartPage(returnP.getStartPage());
		//��ǰҳ
		stu.setNowPage(returnP.getNowPage());
		stu.setPageSize(returnP.getPageSize());
		mav.addObject("student",stu);
		//��ѯ
		//��ѯ����ѧԱ
		List<StudentInfo> list = stuSer.selectiveByPage(stu);
		mav.addObject("stuList",list);
		mav.setViewName("../stuMan/stuSel/stu_sel_result.jsp");
		return mav;
	}
	
	/**
	 * ��ҳ��ѯ
	 * @param stu
	 * @return
	 */
	@RequestMapping("/nextPage.hzyc")
	public ModelAndView nextPage(StudentInfo stu){
		ModelAndView mav = new ModelAndView();
		
		//��ѯ����ѧԱ
		List<StudentInfo> list = stuSer.selectiveByPage(stu);
		mav.addObject("stuList",list);
		//���ûش���ʵ������  {��ҳ����ʹ�õ�ɸѡ����}
		mav.addObject("student",stu);
		//returnP �ǰ���  ���ҳ  ��ǰҳ  
		mav.setViewName("../stuMan/stuSel/stu_sel_result.jsp");
		return mav;
	}
	
	/**
	 * ����ѧԱ��ѯ
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/selOneStudent.hzyc")
	public ModelAndView selOneStudent(int id,HttpServletRequest request){
		StudentInfo student;
		ModelAndView mav = new ModelAndView();
		try {
			student = stuSer.selectOneStudent(id,request);
			mav.addObject("student",student);
			mav.setViewName("../stuMan/stuSel/stu_update.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mav;
	}
	
	/**/
	@RequestMapping("/selStudentDetail.hzyc")
	public ModelAndView selStudentDetail(int id,HttpServletRequest request){
		ModelAndView mav = selOneStudent(id,request);
		mav.setViewName("../stuMan/stuSel/stu_detail.jsp");
		return mav;
	}
	
	/**
	 * 
	 *  ѧԱ�ֲ���ѯ����ʡ�ݲ��ԣ�
	 * 	ajax �����
	 * 	
	 * @author Lee
	 */
	
	@RequestMapping("/selStudentDistribution.hzyc")
	public void selStudentDistribution(HttpServletRequest request,HttpServletResponse response){
		
			//��������Ϣ..����������ǰ10��ѧԱ
			List<StudentInfo> values = stuSer.getDistribution();
		try {
			
			Gson json = new Gson();
			String info = json.toJson(values);
			PrintWriter print = response.getWriter();
			print.print(info);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	
	/**
	 * ���ص���excelģ��demo�ļ�
	 * @param response
	 * @param request
	 */
	@RequestMapping("/downloadDemo.hzyc")
	public void downloadDemo(HttpServletResponse response,HttpServletRequest request){
		response.reset();
        stuSer.downloadDemo(response, request);
	}
	
	/**
	 * ����excel
	 * @return
	 */
	@RequestMapping("/importExcel.hzyc")
	public ModelAndView importExcel(MultipartFile file){
		ModelAndView mav = new ModelAndView();
		System.out.println("==========================");
		boolean b = false;
		try {
			b =  stuSer.importExcel(file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(b){
			mav.setViewName("../success.jsp");
			
		}else{
			mav.setViewName("../fail.jsp");
		}
		return mav;
	}
	
	/**
	 * ����excel
	 * 
	 * @param student
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/exportExcel.hzyc")
	public ModelAndView exportExcel(StudentInfo student,HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		boolean b = stuSer.exportExcel(student,response);
		return mav;
	}
	 
	
	public static void main(String[] args) {
		String a  = "{name: '������Դ��',id: 1,alias: 'changyong',children: [{name: '����δ����������ת��' ,id: 11,href: 'http://www.layui.com/' ,alias: 'weidu' }, {name: '�ö��ʼ�',id: 12}, { name: '��ǩ�ʼ�' ,id: 13 }}";
		System.out.println(a);
		
		
		Dictionary[] b = Dict.getDictByType("period");
					System.out.println(b.length);
	}
}
