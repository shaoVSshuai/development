package com.hzyc.website.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import sun.awt.geom.AreaOp.AddOp;

import com.google.gson.Gson;
import com.hzyc.website.aop.SystemLog;
import com.hzyc.website.beans.Employee;
import com.hzyc.website.beans.EmployeeJob;
import com.hzyc.website.beans.Job;
import com.hzyc.website.services.EmpSer;

@Controller
@RequestMapping("empCon")
public class EmpCon {
	
	@Autowired
	EmpSer es;
	
	/**
	 * 查询员工[多条件查询]
	 * @param emp
	 * @return
	 */
	@RequestMapping("/selEmp.hzyc")
	public ModelAndView selEmp(Employee emp){
		ModelAndView mav = new ModelAndView();
		List<Employee> employee = es.selEmp(emp);
		mav.addObject("empList", employee);
		mav.setViewName("../deptMan/emp_result.jsp");
		return mav;
	}
	
	/**
	 * 查询员工详情
	 * 
	 * @param id 主键
	 * @return
	 */
	@RequestMapping("/selEmpDetail.hzyc")
	public ModelAndView selEmpDetail(String id,HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		try {
			Employee employee = es.selEmpById(id,request);
			mav.addObject("employee",employee);
			mav.setViewName("../deptMan/emp_detail.jsp");
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
			//程序异常出错
			mav.setViewName("../error.jsp?errorCode="+302);
			// TODO: handle exception
		}
		return mav;
	}
	
	/**
	 * 添加新员工
	 * 
	 * @author 邵帅
	 * @param emp 职工实体
	 * @param img1 二寸照片
	 * @param img2 身份证复印件照片
	 * @return
	 */
	@RequestMapping("/addEmp.hzyc")
	@SystemLog(module="员工维护" , methods = "添加新员工" )
	public ModelAndView addEmp(Employee emp,MultipartFile img1 , MultipartFile img2, MultipartFile img3){
		ModelAndView mav  = new ModelAndView ();
		try {
			es.addEmp(emp,img1,img2 , img3);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mav.setViewName("");
		return mav;
	}
	
	/**
	 * ajax加载所有规划师
	 * 
	 * @param response
	 */
	@RequestMapping("/selPlanner.hzyc")
	public void selPlanner(HttpServletResponse response){
		try {
			PrintWriter out = response.getWriter();
			Gson j = new Gson();
			//查询一级部门
			List<Employee> list = es.selPlanner();
			String emp = j.toJson(list);
			out.print(emp);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 登录
	 * 
	 * @param emp
	 * @param response
	 */
	@RequestMapping("/login.hzyc")
	public ModelAndView Login(Employee emp,HttpServletRequest request){
			//PrintWriter out = response.getWriter();
			ModelAndView mav = new ModelAndView();
			Employee e = es.login(emp);
			
			//102登录失败
			int flag = 102;
			if(e != null){
				flag = 101;
				mav.setViewName("redirect:/index.jsp");
				//记录session
				HttpSession session = request.getSession();
				
				//存储员工所有信息
				session.setAttribute("employee",e);
				session.setAttribute("roleid", e.getJobCode());
				//查询角色ID（就是职位代码
				//加载所有角色的菜单
				//SELECT job_id, privilege.*  FROM job_privilege,privilege WHERE  job_privilege.p_id = privilege.id  
				
			}else{
				flag = 102;
				mav.setViewName("redirect:/login.jsp?flag="+flag);
			}
			return mav;
			
			/*out.print(flag);
			out.flush();*/
		
	}
	/**
	 * 通过姓名和工号查询员工
	 * 
	 * @author 张阳媚
	 * @param emp 职工实体
	 * @return 
	 */
	@RequestMapping("/selEmpByNameOrCode.hzyc")
	public ModelAndView selEmpByNameOrCode(Employee emp){
		List<Employee> eList = es.selEmpByNameOrCode(emp);
		/*for(int i = 0; i < eList.size() ; i++){
			System.out.println("张----->>>>" + eList.get(i).getName());
			System.out.println("张----->>>>" + eList.get(i).getCode());	
		}
		*/
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", eList);
		mav.setViewName("../sysSetting/role/roleAdmin_result.jsp");
		return mav;
	}
	
	/**
	 * #@author 马荣福
	 * @param code 员工的工号
	 * @return 返回右侧显示职位的界面
	 */
	@RequestMapping("selEmpJob.hzyc")
	public ModelAndView selEmpJob(HttpServletRequest request,String code){
		List<EmployeeJob> ejList = es.selEmployeeJobByCode(code);
		ModelAndView mav = new ModelAndView();
		StringBuffer supplierExpressids = new StringBuffer("");
		for(int i = 0;i<ejList.size();i++){
			supplierExpressids.append(ejList.get(i).getJobCode()+",");
		}
		mav.addObject("supplierExpressids",supplierExpressids);
		mav.addObject("EmployeeCode",code);
		mav.setViewName("../sysSetting/role/rFrame_right.jsp");
		return mav;
	}
}
