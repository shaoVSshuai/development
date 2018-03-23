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
 * 学员信息管理controller
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
	 * 学员信息添加
	 * 
	 * @param stu  学员信息实体
	 * @param img1   学员本人图片
	 * @return ""
	 */
	//StudentInfo [id=null, code=201317030214, name=张三, idcard=371325199404126910, sex=1, tel=18686314385, qq=null, wx=18686314385, province=22, city=220100, address=220102, birthday=1994-04-12, education=null, eLength=null, school=null, dept=null, major=网络工程, period=null, classes=null, classPath=null, planner=李吉智, plannerPath=null, teacher=null, teacherPath=null, time=null, state=null, remark=null, img=null, workImg=null, imgName=null, age=0]
	//fced8a89-6345-48ee-a1b9-a0455ea3bef1.png
	@RequestMapping("/add.hzyc")
	public String add(StudentInfo student,MultipartFile img1){
		System.out.println(student);
		//学生信息添加
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
	 * 删除学员
	 * 
	 * @author 邵帅
	 * @param id 学生主键
	 * @param response ajax请求响应
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
	 * 学员信息修改
	 * 
	 * @author 邵帅
	 * @param stu
	 * @param req
	 * @return
	 */
	@RequestMapping("/updStudentInfo.hzyc")
	public String updStudentInfo(StudentInfo stu,MultipartFile img1,HttpServletRequest req){
		//学生信息修改
		boolean b = false;
		//获取文件字节数组
		try {
			
			b = stuSer.updateStudentInfo(stu, img1, req);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return b ? "../../success.jsp" : "../../fail.jsp";
	}
	
	/**
	 * 查询学员
	 * 
	 * @author 邵帅
	 * @param stu 学生信息实体
	 * @return stuMan/stu_sel_result.jsp 查询结果视图
	 */
	@RequestMapping("/selectiveStudent.hzyc")
	public ModelAndView selectiveStudent(StudentInfo stu){
		ModelAndView mav = new ModelAndView();
		List<StudentInfo> list =  stuSer.selective(stu);
		mav.addObject("stuList",list);
		//查询结果视图
		mav.setViewName("../stuMan/stuSel/stu_sel_result.jsp");
		return mav;
	}
	
	/**
	 * 分页查询
	 * @param stu
	 * @return
	 */
	@RequestMapping("/selectiveByPage.hzyc")
	public ModelAndView selectiveByPage(StudentInfo stu){
		ModelAndView mav = new ModelAndView();
		Page p = new Page();
		//查询最大行数
		int allrow = stuSer.selAllCount(stu);
		Page returnP = p.fenye(allrow+"", "1", "5");
		//设置分页属性
		stu.setMaxPage(returnP.getMaxPage());
		//查询开始行数  limit的起始字段
		stu.setStartPage(returnP.getStartPage());
		//当前页
		stu.setNowPage(returnP.getNowPage());
		stu.setPageSize(returnP.getPageSize());
		mav.addObject("student",stu);
		//查询
		//查询试听学员
		List<StudentInfo> list = stuSer.selectiveByPage(stu);
		mav.addObject("stuList",list);
		mav.setViewName("../stuMan/stuSel/stu_sel_result.jsp");
		return mav;
	}
	
	/**
	 * 分页查询
	 * @param stu
	 * @return
	 */
	@RequestMapping("/nextPage.hzyc")
	public ModelAndView nextPage(StudentInfo stu){
		ModelAndView mav = new ModelAndView();
		
		//查询试听学员
		List<StudentInfo> list = stuSer.selectiveByPage(stu);
		mav.addObject("stuList",list);
		//设置回传的实体属性  {分页处理使用当筛选条件}
		mav.addObject("student",stu);
		//returnP 是包含  最大页  当前页  
		mav.setViewName("../stuMan/stuSel/stu_sel_result.jsp");
		return mav;
	}
	
	/**
	 * 单个学员查询
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
	 *  学员分布查询（单省份测试）
	 * 	ajax 请求版
	 * 	
	 * @author Lee
	 */
	
	@RequestMapping("/selStudentDistribution.hzyc")
	public void selStudentDistribution(HttpServletRequest request,HttpServletResponse response){
		
			//各地区信息..人数比例，前10个学员
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
	 * 下载导入excel模版demo文件
	 * @param response
	 * @param request
	 */
	@RequestMapping("/downloadDemo.hzyc")
	public void downloadDemo(HttpServletResponse response,HttpServletRequest request){
		response.reset();
        stuSer.downloadDemo(response, request);
	}
	
	/**
	 * 导入excel
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
	 * 导出excel
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
		String a  = "{name: '人力资源部',id: 1,alias: 'changyong',children: [{name: '所有未读（设置跳转）' ,id: 11,href: 'http://www.layui.com/' ,alias: 'weidu' }, {name: '置顶邮件',id: 12}, { name: '标签邮件' ,id: 13 }}";
		System.out.println(a);
		
		
		Dictionary[] b = Dict.getDictByType("period");
					System.out.println(b.length);
	}
}
