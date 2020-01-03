package com.hzyc.website.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.hzyc.website.aop.SystemLog;
import com.hzyc.website.beans.City;
import com.hzyc.website.beans.Dept;
import com.hzyc.website.beans.DeptJob;
import com.hzyc.website.beans.Employee;
import com.hzyc.website.beans.Job;
import com.hzyc.website.beans.Province;
import com.hzyc.website.services.DeptSer;
import com.hzyc.website.services.EmpSer;

@Controller
@RequestMapping(value="deptEmpCon")
public class DeptEmpCon {
	
	@RequestMapping("/selLevel1.hzyc")
	public void selLevel1(HttpServletResponse response){
		try {
			System.out.println("1111111111111111");
			PrintWriter out = response.getWriter();
			Gson j = new Gson();
			//查询一级部门
			List<Dept> list = ds.selLevel1();
			String dept = j.toJson(list);
			out.print(dept);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping("/selLevel2.hzyc")
	public void selLevel2(HttpServletResponse response,HttpServletRequest request){
		try {
			System.out.println("2222222222222222");
			String code = request.getParameter("code");
			PrintWriter out = response.getWriter();
			Gson j = new Gson();
			//查询一级部门
			List<Dept> list = ds.selLevel2(code);
			String dept = j.toJson(list);
			out.print(dept);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping("/selDeptByLevel2.hzyc")
	public void selDeptByLevel2(HttpServletResponse response,HttpServletRequest request){
		PrintWriter out = null;
		try {
			System.out.println("=========-----");
			String code2 = request.getParameter("code");
			System.out.println(code2);
			 out = response.getWriter();
			Gson j = new Gson();
			//查询职位
			List<Job> list = ds.selDeptByLevel2(code2);
			String job = j.toJson(list);
			System.out.println(job);
			out.print(job);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(out != null){
				out.close();
			}
		}
	}
	@RequestMapping("/selProvince.hzyc")
	public void selProvince(HttpServletResponse response){
		try {
			System.out.println("=======");
			PrintWriter out = response.getWriter();
			Gson j = new Gson();
			//查询省级信息
			List<Province> list = ds.selProvince();
			String province = j.toJson(list);
			System.out.println("province:");
			System.out.println(province);
			out.print(province);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping("/selCity.hzyc")
	public void selCity(HttpServletResponse response,HttpServletRequest request){
		try {
			String name = request.getParameter("code");
			String provinceCode = ds.selProvinceCode(name);
			System.out.println(provinceCode);
			PrintWriter out = response.getWriter();
			Gson j = new Gson();
			//查询市级信息
			List<City> list = ds.selCity(provinceCode);
			String city = j.toJson(list);
			out.print(city);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@RequestMapping("/selJob.hzyc")
	public void selJob(HttpServletResponse response,HttpServletRequest request){
		try {
			String code = request.getParameter("code");
			PrintWriter out = response.getWriter();
			Gson j = new Gson();
			//查询一级部门
			List<Job> list = ds.selJobByDept2(code);
			String dept = j.toJson(list);
			out.print(dept);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Autowired
	EmpSer es;
	
	/**
	 * 查询员工[多条件查询]
	 * @param emp
	 * @return
	 */
	@RequestMapping("/selEmp.hzyc")
	public ModelAndView selEmp(Employee emp){
		//System.out.println("==========查询");
		ModelAndView mav = new ModelAndView();
		List<Employee> employee = es.selEmp(emp);
		mav.addObject("empList", employee);
		mav.setViewName("../deptMan/emp_result.jsp");
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
	public ModelAndView addEmp(Employee emp,MultipartFile img1 , MultipartFile img2 , MultipartFile img3){
		ModelAndView mav  = new ModelAndView ();
		try {
			//默认123456
			emp.setPassword("e10adc3949ba59abbe56e057f20f883e");
			es.addEmp(emp,img1,img2 , img3);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mav.setViewName("../deptMan/emp_sel.jsp");
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
			mav.setViewName("../deptEmpMan/deptMan/emp_detail.jsp");
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
			//程序异常出错
			mav.setViewName("../error.jsp?errorCode="+302);
			// TODO: handle exception
		}
		return mav;
	}
	
	@Autowired
	DeptSer ds;



	/**
	 * 
	 * @author 张得钧
	 * @function 查询树形图一级部门-二级部门-职位-员工
	 * @param perDept 包含所有的一级部门
	 * @param getPerErDept 包含所有的二级部门，和工作名称
	 * @param getPer 包含所有的员工名称
	 * @return mav
	 */
	@RequestMapping("/selAllDept.hzyc")
	public ModelAndView selAllDept(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		try {
			/*总的要累加长度:*/
			String allValues="";
			List<Dept> perDept=ds.seletAllDept("1");
			/*这里面遍历的是               多一级的                 */
			for(int i=0;i<perDept.size();i++){
				/*这里面遍历一遍所有一级部门的值下的二级部门对应的，二级部门有的职位，职位下面对应的人*/
				List<Dept> getPerErDept=ds.selectDeptUntilEmployee(perDept.get(i).getDeptCode());
				String getPerlists=getPerErDept+"1";
				/*判断这个有二级部门的情况下：*/
				if(getPerlists.length()!=3){
					/*这里是累加所有的一级部门:*/
					allValues+= "{ name :'"+perDept.get(i).getDeptName()+"',id:"+perDept.get(i).getId()+",href:'../deptEmpCon/treeUntilRight.hzyc?classes=dept1&id="+perDept.get(i).getId()+"',children:[";
					/*这里是循环                     二级部门的                        */
					for(int j=0;j<getPerErDept.size();j++){
								/*当这个二级部门      不为      最后一个的情况下:*/
								if(j!=getPerErDept.size()-1){
									allValues+="{name:'"+getPerErDept.get(j).getDeptName()+"',id:"+getPerErDept.get(j).getId()+",href:'../deptEmpCon/treeUntilRight.hzyc?classes=dept2&id="+getPerErDept.get(j).getId()+"',children:[";
										/*遍历   每个二级部门下的多个         职位*/
										for(int k=0;k<getPerErDept.get(j).getJobList().size();k++){
											/*当这里的职位为           不为         最后一位*/
											if(k!=getPerErDept.get(j).getJobList().size()-1){
												allValues+="{name:'"+getPerErDept.get(j).getJobList().get(k).getJobName()+"',id:"+getPerErDept.get(j).getJobList().get(k).getJobCode()+",href:'../deptEmpCon/treeUntilRight.hzyc?classes=job&id="+getPerErDept.get(j).getJobList().get(k).getJobCode()+"',children:[";
												/*这里是嵌套:部门下面有什么员工：*/
												List<Job> getPer=ds.selectJobEmplee(getPerErDept.get(j).getJobList().get(k).getJobCode());
												/*这里遍历出所有职位下的人员信息!*/
												for(int s=0;s<getPer.size();s++){
													/*当这里面的         不是              最后一位:*/
													if(s!=getPer.size()-1){
														allValues+="{name:'"+getPer.get(s).getEmployeeList().get(0).getName()+"',id:"+getPer.get(s).getEmployeeList().get(0).getId()+",href:'../deptEmpCon/treeUntilRight.hzyc?classes=employee&id="+getPer.get(s).getEmployeeList().get(0).getId()+"'},";
													/*当这里面的           是              最后一位:*/	
													}else{
														allValues+="{name:'"+getPer.get(s).getEmployeeList().get(0).getName()+"',id:"+getPer.get(s).getEmployeeList().get(0).getId()+",href:'../deptEmpCon/treeUntilRight.hzyc?classes=employee&id="+getPer.get(s).getEmployeeList().get(0).getId()+"'},]},]},";
													}
												}
											/*当这里的职位为              为         最后一位*/	
											}else{
												allValues+="{name:'"+getPerErDept.get(j).getJobList().get(k).getJobName()+"',id:"+getPerErDept.get(j).getJobList().get(k).getJobCode()+",href:'../deptEmpCon/treeUntilRight.hzyc?classes=job&id="+getPerErDept.get(j).getJobList().get(k).getJobCode()+"',children:[";
												/*这里是嵌套:部门下面有什么员工：*/
												List<Job> getPer=ds.selectJobEmplee(getPerErDept.get(j).getJobList().get(k).getJobCode());
												/*这里遍历出所有职位下的人员信息!*/
												/*当这里的员工还有的时候:*/
												if(getPer.size()!=0){
												for(int s=0;s<getPer.size();s++){
													/*当这里面的人         不是              最后一位:*/
													if(s!=getPer.size()-1){
														allValues+="{name:'"+getPer.get(s).getEmployeeList().get(0).getName()+"',id:"+getPer.get(s).getEmployeeList().get(0).getId()+",href:'../deptEmpCon/treeUntilRight.hzyc?classes=employee&id="+getPer.get(s).getEmployeeList().get(0).getId()+"'},";
													/*当这里面的人           无             有时候:*/	
													}else{
														allValues+="{name:'"+getPer.get(getPer.size()-1).getEmployeeList().get(0).getName()+"',id:"+getPer.get(getPer.size()-1).getEmployeeList().get(0).getId()+",href:'../deptEmpCon/treeUntilRight.hzyc?classes=employee&id="+getPer.get(s).getEmployeeList().get(0).getId()+"'},]},]},";
													}
												}	
													/*当这里的员工没有的时候：*/
												}else{
													allValues+="]},]},";
												}
											}
										}
								/*当这个二级部门        为          最后一个的情况下:*/
								}else{
									/*当这里的是二级部门的最后一个的时候：在最后一栏里面会有多个职务的情况下：进行遍历*/
									allValues+="{name:'"+getPerErDept.get(j).getDeptName()+"',id:"+getPerErDept.get(j).getId()+",href:'../deptEmpCon/treeUntilRight.hzyc?classes=dept2&id="+getPerErDept.get(j).getId()+"',children:[";
										/*遍历   每个二级部门下的多个         职位*/
										for(int k=0;k<getPerErDept.get(getPerErDept.size()-1).getJobList().size();k++){
											/*当这里的职位为           不为         最后一位*/
											if(k!=getPerErDept.get(getPerErDept.size()-1).getJobList().size()-1){
												allValues+="{name:'"+getPerErDept.get(getPerErDept.size()-1).getJobList().get(k).getJobName()+"',id:"+getPerErDept.get(getPerErDept.size()-1).getJobList().get(k).getJobCode()+",href:'../deptEmpCon/treeUntilRight.hzyc?classes=job&id="+getPerErDept.get(j).getJobList().get(k).getJobCode()+"',children:[";
												/*这里是嵌套:部门下面有什么员工：*/
												List<Job> getPer=ds.selectJobEmplee(getPerErDept.get(j).getJobList().get(k).getJobCode());
												/*这里遍历出所有职位下的人员信息!*/
												/*当这里的员工还有的时候:*/
												if(getPer.size()!=0){
												for(int s=0;s<getPer.size();s++){
													/*当这里面的人         不是              最后一位:*/
													if(s!=getPer.size()-1){
														allValues+="{name:'"+getPer.get(s).getEmployeeList().get(0).getName()+"',id:"+getPer.get(s).getEmployeeList().get(0).getId()+",href:'../deptEmpCon/treeUntilRight.hzyc?classes=employee&id="+getPer.get(s).getEmployeeList().get(0).getId()+"'},";
													/*当这里面的人           为最后一位             有时候:*/	
													}else{
														allValues+="{name:'"+getPer.get(getPer.size()-1).getEmployeeList().get(0).getName()+"',id:"+getPer.get(getPer.size()-1).getEmployeeList().get(0).getId()+",href:'../deptEmpCon/treeUntilRight.hzyc?classes=employee&id="+getPer.get(s).getEmployeeList().get(0).getId()+"'},]},]},]},";
													}
												}	
													/*当这里的员工没有的时候：*/
												}else{
													allValues+="]},]},]},";
												}
											/*当这里的职位为              为         最后一位*/	
											}else{
												allValues+="{name:'"+getPerErDept.get(getPerErDept.size()-1).getJobList().get(k).getJobName()+"',id:"+getPerErDept.get(getPerErDept.size()-1).getJobList().get(k).getJobCode()+",href:'../deptEmpCon/treeUntilRight.hzyc?classes=job&id="+getPerErDept.get(j).getJobList().get(k).getJobCode()+"',children:[";
												/*这里是嵌套:部门下面有什么员工：*/
												List<Job> getPer=ds.selectJobEmplee(getPerErDept.get(j).getJobList().get(k).getJobCode());
												/*这里遍历出所有职位下的人员信息!*/
												/*当这里的员工还有的时候:*/
												if(getPer.size()!=0){
												for(int s=0;s<getPer.size();s++){
													/*当这里面的人         不是              最后一位:*/
													if(s!=getPer.size()-1){	
														allValues+="{name:'"+getPer.get(s).getEmployeeList().get(0).getName()+"',id:"+getPer.get(s).getEmployeeList().get(0).getId()+",href:'../deptEmpCon/treeUntilRight.hzyc?classes=employee&id="+getPer.get(s).getEmployeeList().get(0).getId()+"'},";
													/*当这里面的人           为最后一位             有时候:*/	
													}else{
														allValues+="{name:'"+getPer.get(getPer.size()-1).getEmployeeList().get(0).getName()+"',id:"+getPer.get(getPer.size()-1).getEmployeeList().get(0).getId()+",href:'../deptEmpCon/treeUntilRight.hzyc?classes=employee&id="+getPer.get(s).getEmployeeList().get(0).getId()+"'},]},]},]},";
													}
												}	
													/*当这里的员工没有的时候：*/
												}else{
													allValues+="]},]},]},";
												}
											}
									}
								}
					}
				/*这里是没有二级部门的情况的拼接*/
				}else{
					allValues+= "{name :'"+perDept.get(i).getDeptName()+"',id:"+perDept.get(i).getId()+",href:'../deptEmpCon/treeUntilRight.hzyc?classes=dept1&id="+perDept.get(i).getId()+"'},";
				}
			}
			request.setAttribute("erwei",allValues);
			mav.setViewName("../deptEmpMan/deptMan/tree.jsp");
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
			//程序异常出错
			mav.setViewName("../error.jsp?errorCode="+302);
			// TODO: handle exception
		}
		return mav;
	}
	/**
	 * @author 张得钧
	 * @function 实现树形图点击任意类下跳转到该路径里获取值，并从数据库取值返回给tree.jsp页面 
	 * @param request
	 * @param deptOne 部门一的名称
	 * @param deptOneCode	部门一的code（添加二级部门时）
	 * @param deptTwo 部门二的名称
	 * @param remark	备注
	 * @return
	 */
	@RequestMapping("treeUntilRight.hzyc")
	public ModelAndView treeUntilRightJsp(HttpServletRequest request){
		ModelAndView model=new ModelAndView();
		try{
			/*这里面获取到相应一级/二级部门的Id,根据部门id查询出来所有属性*/
			String id=request.getParameter("id");
			String classes=request.getParameter("classes");
			System.out.println("这个Id数是:"+id);
			System.out.println("这里的类别是"+classes);
			
			request.setAttribute("judgeShow",classes);
			model.setViewName("../deptEmpMan/deptMan/right.jsp");
			
		}catch(Exception e){
			
			e.printStackTrace();
		}
		
		
		return model;
	}
	
	
	
	
	
	/**
	 * @author 马荣福
	 * @param request
	 * @param deptOne 部门一的名称
	 * @param deptOneCode	部门一的code（添加二级部门时）
	 * @param deptTwo 部门二的名称
	 * @param remark	备注
	 * @return
	 */
	@RequestMapping("addDept.hzyc")
	public String addDept(HttpServletRequest request,String deptOne,String deptOneCode,String deptTwo,String remark){
		System.out.println("--------");
		int flag = ds.addDept(deptOne,deptOneCode,deptTwo,remark);
		if(flag !=0){
			//添加成功
			return "../deptEmpMan/deptMan/frame.jsp?flag=1";
		}else{
			return "../deptEmpMan/deptMan/frame.jsp?flag=2";
		}
		
	}
	
	/**
	 * 
	 * @author 马荣福
	 * @param request
	 * @param deptTwo 二级部门的编码
	 * @param job	  job表的实体
	 * @return
	 */
	@RequestMapping("addJob.hzyc")
	public String addJob(HttpServletRequest request,String deptTwo,Job job){
		boolean flag = ds.addJob(deptTwo,job);
		System.out.println(flag);
		if(flag){
			//添加成功
			return "../deptEmpMan/deptMan/frame.jsp?flag=1";
		}else{
			return "../deptEmpMan/deptMan/frame.jsp?flag=2";
		}
		
	}
	
}
