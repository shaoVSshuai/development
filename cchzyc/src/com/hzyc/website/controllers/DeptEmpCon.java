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
			//��ѯһ������
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
			//��ѯһ������
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
			//��ѯְλ
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
			//��ѯʡ����Ϣ
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
			//��ѯ�м���Ϣ
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
			//��ѯһ������
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
	 * ��ѯԱ��[��������ѯ]
	 * @param emp
	 * @return
	 */
	@RequestMapping("/selEmp.hzyc")
	public ModelAndView selEmp(Employee emp){
		//System.out.println("==========��ѯ");
		ModelAndView mav = new ModelAndView();
		List<Employee> employee = es.selEmp(emp);
		mav.addObject("empList", employee);
		mav.setViewName("../deptMan/emp_result.jsp");
		return mav;
	}
	/**
	 * �����Ա��
	 * 
	 * @author ��˧
	 * @param emp ְ��ʵ��
	 * @param img1 ������Ƭ
	 * @param img2 ���֤��ӡ����Ƭ
	 * @return
	 */
	@RequestMapping("/addEmp.hzyc")
	@SystemLog(module="Ա��ά��" , methods = "�����Ա��" )
	public ModelAndView addEmp(Employee emp,MultipartFile img1 , MultipartFile img2 , MultipartFile img3){
		ModelAndView mav  = new ModelAndView ();
		try {
			//Ĭ��123456
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
	 * ��ѯԱ������
	 * 
	 * @param id ����
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
			//�����쳣����
			mav.setViewName("../error.jsp?errorCode="+302);
			// TODO: handle exception
		}
		return mav;
	}
	
	@Autowired
	DeptSer ds;



	/**
	 * 
	 * @author �ŵþ�
	 * @function ��ѯ����ͼһ������-��������-ְλ-Ա��
	 * @param perDept �������е�һ������
	 * @param getPerErDept �������еĶ������ţ��͹�������
	 * @param getPer �������е�Ա������
	 * @return mav
	 */
	@RequestMapping("/selAllDept.hzyc")
	public ModelAndView selAllDept(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		try {
			/*�ܵ�Ҫ�ۼӳ���:*/
			String allValues="";
			List<Dept> perDept=ds.seletAllDept("1");
			/*�������������               ��һ����                 */
			for(int i=0;i<perDept.size();i++){
				/*���������һ������һ�����ŵ�ֵ�µĶ������Ŷ�Ӧ�ģ����������е�ְλ��ְλ�����Ӧ����*/
				List<Dept> getPerErDept=ds.selectDeptUntilEmployee(perDept.get(i).getDeptCode());
				String getPerlists=getPerErDept+"1";
				/*�ж�����ж������ŵ�����£�*/
				if(getPerlists.length()!=3){
					/*�������ۼ����е�һ������:*/
					allValues+= "{ name :'"+perDept.get(i).getDeptName()+"',id:"+perDept.get(i).getId()+",href:'../deptEmpCon/treeUntilRight.hzyc?classes=dept1&id="+perDept.get(i).getId()+"',children:[";
					/*������ѭ��                     �������ŵ�                        */
					for(int j=0;j<getPerErDept.size();j++){
								/*�������������      ��Ϊ      ���һ���������:*/
								if(j!=getPerErDept.size()-1){
									allValues+="{name:'"+getPerErDept.get(j).getDeptName()+"',id:"+getPerErDept.get(j).getId()+",href:'../deptEmpCon/treeUntilRight.hzyc?classes=dept2&id="+getPerErDept.get(j).getId()+"',children:[";
										/*����   ÿ�����������µĶ��         ְλ*/
										for(int k=0;k<getPerErDept.get(j).getJobList().size();k++){
											/*�������ְλΪ           ��Ϊ         ���һλ*/
											if(k!=getPerErDept.get(j).getJobList().size()-1){
												allValues+="{name:'"+getPerErDept.get(j).getJobList().get(k).getJobName()+"',id:"+getPerErDept.get(j).getJobList().get(k).getJobCode()+",href:'../deptEmpCon/treeUntilRight.hzyc?classes=job&id="+getPerErDept.get(j).getJobList().get(k).getJobCode()+"',children:[";
												/*������Ƕ��:����������ʲôԱ����*/
												List<Job> getPer=ds.selectJobEmplee(getPerErDept.get(j).getJobList().get(k).getJobCode());
												/*�������������ְλ�µ���Ա��Ϣ!*/
												for(int s=0;s<getPer.size();s++){
													/*���������         ����              ���һλ:*/
													if(s!=getPer.size()-1){
														allValues+="{name:'"+getPer.get(s).getEmployeeList().get(0).getName()+"',id:"+getPer.get(s).getEmployeeList().get(0).getId()+",href:'../deptEmpCon/treeUntilRight.hzyc?classes=employee&id="+getPer.get(s).getEmployeeList().get(0).getId()+"'},";
													/*���������           ��              ���һλ:*/	
													}else{
														allValues+="{name:'"+getPer.get(s).getEmployeeList().get(0).getName()+"',id:"+getPer.get(s).getEmployeeList().get(0).getId()+",href:'../deptEmpCon/treeUntilRight.hzyc?classes=employee&id="+getPer.get(s).getEmployeeList().get(0).getId()+"'},]},]},";
													}
												}
											/*�������ְλΪ              Ϊ         ���һλ*/	
											}else{
												allValues+="{name:'"+getPerErDept.get(j).getJobList().get(k).getJobName()+"',id:"+getPerErDept.get(j).getJobList().get(k).getJobCode()+",href:'../deptEmpCon/treeUntilRight.hzyc?classes=job&id="+getPerErDept.get(j).getJobList().get(k).getJobCode()+"',children:[";
												/*������Ƕ��:����������ʲôԱ����*/
												List<Job> getPer=ds.selectJobEmplee(getPerErDept.get(j).getJobList().get(k).getJobCode());
												/*�������������ְλ�µ���Ա��Ϣ!*/
												/*�������Ա�����е�ʱ��:*/
												if(getPer.size()!=0){
												for(int s=0;s<getPer.size();s++){
													/*�����������         ����              ���һλ:*/
													if(s!=getPer.size()-1){
														allValues+="{name:'"+getPer.get(s).getEmployeeList().get(0).getName()+"',id:"+getPer.get(s).getEmployeeList().get(0).getId()+",href:'../deptEmpCon/treeUntilRight.hzyc?classes=employee&id="+getPer.get(s).getEmployeeList().get(0).getId()+"'},";
													/*�����������           ��             ��ʱ��:*/	
													}else{
														allValues+="{name:'"+getPer.get(getPer.size()-1).getEmployeeList().get(0).getName()+"',id:"+getPer.get(getPer.size()-1).getEmployeeList().get(0).getId()+",href:'../deptEmpCon/treeUntilRight.hzyc?classes=employee&id="+getPer.get(s).getEmployeeList().get(0).getId()+"'},]},]},";
													}
												}	
													/*�������Ա��û�е�ʱ��*/
												}else{
													allValues+="]},]},";
												}
											}
										}
								/*�������������        Ϊ          ���һ���������:*/
								}else{
									/*��������Ƕ������ŵ����һ����ʱ�������һ��������ж��ְ�������£����б���*/
									allValues+="{name:'"+getPerErDept.get(j).getDeptName()+"',id:"+getPerErDept.get(j).getId()+",href:'../deptEmpCon/treeUntilRight.hzyc?classes=dept2&id="+getPerErDept.get(j).getId()+"',children:[";
										/*����   ÿ�����������µĶ��         ְλ*/
										for(int k=0;k<getPerErDept.get(getPerErDept.size()-1).getJobList().size();k++){
											/*�������ְλΪ           ��Ϊ         ���һλ*/
											if(k!=getPerErDept.get(getPerErDept.size()-1).getJobList().size()-1){
												allValues+="{name:'"+getPerErDept.get(getPerErDept.size()-1).getJobList().get(k).getJobName()+"',id:"+getPerErDept.get(getPerErDept.size()-1).getJobList().get(k).getJobCode()+",href:'../deptEmpCon/treeUntilRight.hzyc?classes=job&id="+getPerErDept.get(j).getJobList().get(k).getJobCode()+"',children:[";
												/*������Ƕ��:����������ʲôԱ����*/
												List<Job> getPer=ds.selectJobEmplee(getPerErDept.get(j).getJobList().get(k).getJobCode());
												/*�������������ְλ�µ���Ա��Ϣ!*/
												/*�������Ա�����е�ʱ��:*/
												if(getPer.size()!=0){
												for(int s=0;s<getPer.size();s++){
													/*�����������         ����              ���һλ:*/
													if(s!=getPer.size()-1){
														allValues+="{name:'"+getPer.get(s).getEmployeeList().get(0).getName()+"',id:"+getPer.get(s).getEmployeeList().get(0).getId()+",href:'../deptEmpCon/treeUntilRight.hzyc?classes=employee&id="+getPer.get(s).getEmployeeList().get(0).getId()+"'},";
													/*�����������           Ϊ���һλ             ��ʱ��:*/	
													}else{
														allValues+="{name:'"+getPer.get(getPer.size()-1).getEmployeeList().get(0).getName()+"',id:"+getPer.get(getPer.size()-1).getEmployeeList().get(0).getId()+",href:'../deptEmpCon/treeUntilRight.hzyc?classes=employee&id="+getPer.get(s).getEmployeeList().get(0).getId()+"'},]},]},]},";
													}
												}	
													/*�������Ա��û�е�ʱ��*/
												}else{
													allValues+="]},]},]},";
												}
											/*�������ְλΪ              Ϊ         ���һλ*/	
											}else{
												allValues+="{name:'"+getPerErDept.get(getPerErDept.size()-1).getJobList().get(k).getJobName()+"',id:"+getPerErDept.get(getPerErDept.size()-1).getJobList().get(k).getJobCode()+",href:'../deptEmpCon/treeUntilRight.hzyc?classes=job&id="+getPerErDept.get(j).getJobList().get(k).getJobCode()+"',children:[";
												/*������Ƕ��:����������ʲôԱ����*/
												List<Job> getPer=ds.selectJobEmplee(getPerErDept.get(j).getJobList().get(k).getJobCode());
												/*�������������ְλ�µ���Ա��Ϣ!*/
												/*�������Ա�����е�ʱ��:*/
												if(getPer.size()!=0){
												for(int s=0;s<getPer.size();s++){
													/*�����������         ����              ���һλ:*/
													if(s!=getPer.size()-1){	
														allValues+="{name:'"+getPer.get(s).getEmployeeList().get(0).getName()+"',id:"+getPer.get(s).getEmployeeList().get(0).getId()+",href:'../deptEmpCon/treeUntilRight.hzyc?classes=employee&id="+getPer.get(s).getEmployeeList().get(0).getId()+"'},";
													/*�����������           Ϊ���һλ             ��ʱ��:*/	
													}else{
														allValues+="{name:'"+getPer.get(getPer.size()-1).getEmployeeList().get(0).getName()+"',id:"+getPer.get(getPer.size()-1).getEmployeeList().get(0).getId()+",href:'../deptEmpCon/treeUntilRight.hzyc?classes=employee&id="+getPer.get(s).getEmployeeList().get(0).getId()+"'},]},]},]},";
													}
												}	
													/*�������Ա��û�е�ʱ��*/
												}else{
													allValues+="]},]},]},";
												}
											}
									}
								}
					}
				/*������û�ж������ŵ������ƴ��*/
				}else{
					allValues+= "{name :'"+perDept.get(i).getDeptName()+"',id:"+perDept.get(i).getId()+",href:'../deptEmpCon/treeUntilRight.hzyc?classes=dept1&id="+perDept.get(i).getId()+"'},";
				}
			}
			request.setAttribute("erwei",allValues);
			mav.setViewName("../deptEmpMan/deptMan/tree.jsp");
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
			//�����쳣����
			mav.setViewName("../error.jsp?errorCode="+302);
			// TODO: handle exception
		}
		return mav;
	}
	/**
	 * @author �ŵþ�
	 * @function ʵ������ͼ�������������ת����·�����ȡֵ���������ݿ�ȡֵ���ظ�tree.jspҳ�� 
	 * @param request
	 * @param deptOne ����һ������
	 * @param deptOneCode	����һ��code����Ӷ�������ʱ��
	 * @param deptTwo ���Ŷ�������
	 * @param remark	��ע
	 * @return
	 */
	@RequestMapping("treeUntilRight.hzyc")
	public ModelAndView treeUntilRightJsp(HttpServletRequest request){
		ModelAndView model=new ModelAndView();
		try{
			/*�������ȡ����Ӧһ��/�������ŵ�Id,���ݲ���id��ѯ������������*/
			String id=request.getParameter("id");
			String classes=request.getParameter("classes");
			System.out.println("���Id����:"+id);
			System.out.println("����������"+classes);
			
			request.setAttribute("judgeShow",classes);
			model.setViewName("../deptEmpMan/deptMan/right.jsp");
			
		}catch(Exception e){
			
			e.printStackTrace();
		}
		
		
		return model;
	}
	
	
	
	
	
	/**
	 * @author ���ٸ�
	 * @param request
	 * @param deptOne ����һ������
	 * @param deptOneCode	����һ��code����Ӷ�������ʱ��
	 * @param deptTwo ���Ŷ�������
	 * @param remark	��ע
	 * @return
	 */
	@RequestMapping("addDept.hzyc")
	public String addDept(HttpServletRequest request,String deptOne,String deptOneCode,String deptTwo,String remark){
		System.out.println("--------");
		int flag = ds.addDept(deptOne,deptOneCode,deptTwo,remark);
		if(flag !=0){
			//��ӳɹ�
			return "../deptEmpMan/deptMan/frame.jsp?flag=1";
		}else{
			return "../deptEmpMan/deptMan/frame.jsp?flag=2";
		}
		
	}
	
	/**
	 * 
	 * @author ���ٸ�
	 * @param request
	 * @param deptTwo �������ŵı���
	 * @param job	  job���ʵ��
	 * @return
	 */
	@RequestMapping("addJob.hzyc")
	public String addJob(HttpServletRequest request,String deptTwo,Job job){
		boolean flag = ds.addJob(deptTwo,job);
		System.out.println(flag);
		if(flag){
			//��ӳɹ�
			return "../deptEmpMan/deptMan/frame.jsp?flag=1";
		}else{
			return "../deptEmpMan/deptMan/frame.jsp?flag=2";
		}
		
	}
	
}
