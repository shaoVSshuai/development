package com.hzyc.website.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.hzyc.website.aop.SystemLog;
import com.hzyc.website.beans.Dept;
import com.hzyc.website.beans.DeptJob;
import com.hzyc.website.beans.Dictionary;
import com.hzyc.website.beans.Employee;
import com.hzyc.website.beans.Job;
import com.hzyc.website.beans.JobFirstSecondDept;
import com.hzyc.website.beans.Log;
import com.hzyc.website.services.SysSer;

/**
 * 系统设置控制层
 * 
 * @author 邵帅
 *
 */
@Controller
@RequestMapping(value="sysCon")
public class SystemCon {

	@Autowired
	SysSer ss;
	
	/**
	 * 删除字典项
	 * 
	 * @param id 唯一主键
	 * @throws IOException 
	 */
	public void delDictByCode(String id,HttpServletResponse response)  {
		
		boolean b = ss.delDictByCode(id);
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print( b ? 1 : 0 );
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询字典项目by类别
	 * 
	 * @author 邵帅
	 * @param type 字典类型 (sex,university等)
	 * @return dict_bottom.jsp 数据字典查询页
	 */
	@RequestMapping("/selDict.hzyc")
	public ModelAndView selDict(String type){
		ModelAndView mav = new ModelAndView();
		List<Dictionary> dict = ss.selByType(type);
		mav.addObject("dict",dict);
		mav.setViewName("../sysSetting/dictMan/dict_bottom.jsp");
		return mav;
		
	}
	
	/**
	 * 查询具体字典类别
	 * 
	 * @param code 字典代码
	 * @return
	 */
	@RequestMapping("/selDictByCode.hzyc")
	public ModelAndView selDictByCode(String code){
		ModelAndView mav = new ModelAndView();
		Dictionary dict = ss.selDictByCode(code);
		mav.addObject("dict",dict);
		mav.setViewName("../sysSetting/dictMan/dict_update.jsp");
		return mav;
	}
	
	/**
	 * 禁用字典项
	 * 
	 * @param code 字典唯一代码[主键]
	 * @param status 要改变成为的状态
	 */
	@RequestMapping("/disableDict.hzyc")
	public void disableDict(String id,String status){
		ss.disableDict(id,status);
	}
	
	/**
	 * 修改数据字典项
	 * 注：只修改 dict_name sort 和 是否可用enabled
	 * @param dict
	 */
	@RequestMapping("/updDict.hzyc")
	public void updDict(Dictionary dict,HttpServletResponse response){
		response.setHeader("Content-type", "text/html;charset=UTF-8");  
		response.setCharacterEncoding("utf-8");
		ss.updDict(dict);
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print("<script>alert('更新成功...点击关闭');window.close();</script>");
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 日志查询
	 * 
	 * @author 邵帅
	 * @param log 日志实体(绑定起止时间)
	 * @return log_bottom.jsp 查询结果页
	 */
	@RequestMapping("/selLog.hzyc")
	public ModelAndView selLog(Log log,HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		String[] fails = request.getParameterValues("isfail");
		if(fails != null && fails.length > 0 ){
			log.setState((short)-1);
		}
		List<Log> llist = ss.selLogByTime(log);
		mav.addObject("logList",llist);
		mav.setViewName("../sysSetting/logMan/log_bottom.jsp");
		return mav;
		
	}
	
	
	/**
	 * 根据时间段删除日志
	 * 
	 * @author 邵帅
	 * @param stime 起始时间
	 * @param etime 截止时间
	 * @param response
	 * @return 返回值101：删除成功  102：删除失败
	 */
	@RequestMapping("/delLogByTime.hzyc")
	@SystemLog(module="系统日志管理",methods="删除系统日志")
	public void delLogByTime(String stime,String etime,HttpServletResponse response){
		System.out.println(stime+etime);
		boolean b = false;
		PrintWriter out = null;
		try {
			ss.delLogByTime(stime,etime);
			out = response.getWriter();
			b = true;
		}catch(Exception e){
			b = false;
			 e.printStackTrace();
		}
		out.print(b ? "101" : "102");
		out.flush();
		out.close();
	}
	
	/**
	 * 
	 * @param job
	 * @return
	 */
	@RequestMapping("/selRole.hzyc")
	public ModelAndView selRole(Job job,HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		String[] ff = request.getParameterValues("isMohu");
		int isMohu = 0;
		//如果勾选模糊搜索
		if(ff!=null && ff.length> 0 ){
			isMohu = Integer.parseInt(ff[0]);
		}
		List<Job> rList = ss.selRole(job,isMohu);
		mav.addObject("rList",rList);
		mav.setViewName("../sysSetting/roleMan/role_bottom.jsp");
		return mav;
	}
	/*@RequestMapping("/selRole.hzyc")
	public ModelAndView selRole(Role role){
		
	}*/
	@RequestMapping("/addRole.hzyc")
	@SystemLog(module="角色管理",methods="添加角色")
	public ModelAndView addRole(Job job,HttpServletResponse response){
		boolean b = false;
		try {
			b = ss.addRole(job);
		} catch (Exception e) {
			// TODO: handle exception
		}
		ModelAndView mav = new ModelAndView();
		System.out.println("-------------------");
		if(b){
			mav.setViewName("../sysSetting/roleMan/add.jsp?flag=101");
		}else{
			mav.setViewName("../sysSetting/roleMan/add.jsp?flag=102");
		}
		return mav;
	}
	
	/**
	 * @author 张阳媚
	 * @param 
	 * @return
	 */
	@RequestMapping("/selRightRole.hzyc")
	public ModelAndView selRightRole(){
		List<DeptJob> list = ss.selRightRole();
		ModelAndView mv = new ModelAndView();
		for(int i =0;i<list.size();i++){
			
			List<Job> jlist = list.get(i).getJobList();
			List<JobFirstSecondDept> alist =  list.get(i).getDeptList();
			
			for(int j =0;j<jlist.size();j++){
			String jobName  =	jlist.get(j).getJobName();
			}
			
			for(int j =0 ;j <alist.size();j++){
				String dept1Name = alist.get(j).getDept1Name();
				String dept2Name = alist.get(j).getDept2Name();
			}
			mv.addObject("jobList",jlist);
		}
		
		
		mv.addObject("deptList",list);
		
		mv.setViewName("../sysSetting/role/rFrame_right.jsp");
		return mv;
	}
	@RequestMapping("/selRightRole1.hzyc")
	public void sel(HttpServletRequest request,HttpServletResponse response){
		
		try {
			request.setCharacterEncoding("utf-8");
			Gson gson = new Gson();
			
			List<DeptJob> list = ss.selRightRole();
			System.out.println(list.get(0).getDeptList().get(0).getJobCode()+"------------");
			for(int i =0;i<list.size();i++){
				
				List<Job> jlist = list.get(i).getJobList();
				List<JobFirstSecondDept> alist =  list.get(i).getDeptList();
				
				for(int j =0;j<jlist.size();j++){
				String jobName  =	jlist.get(j).getJobName();
				}
				
				for(int j =0 ;j <alist.size();j++){
					String dept1Name = alist.get(j).getDept1Name();
					String dept2Name = alist.get(j).getDept2Name();
				}
				String data = gson.toJson(jlist);
			}
			
			
			String data1 = gson.toJson(list);
			//gson包：工具包，类似于DbUtils，使用时实例化即可，可以将任意类型的数据转化为【Json类型字符串】类型的数据
			
			//使用流的方式将信息返回给页面
			response.setCharacterEncoding("utf-8");
			PrintWriter writer = response.getWriter();
			writer.print(data1);
			
			//强制刷新和关闭
			writer.flush();
			writer.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @author 张阳媚
	 * @param  员工实体
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/selJobByCode.hzyc")
	public  void  selJobByCode(String Code,HttpServletResponse response) throws IOException{
	   
	   // System.out.println("职位名称-------》》》》》"+jobName);
		List<Employee> eList = ss.selJobByCode(Code);

		for(int i = 0; i<eList.size() ;i++){
			//System.out.println("zhang______"+eList.get(i).getDeptJobList());
             //String jc=ss.selJobNameByJobCode(jobCode);
             System.out.println("职位名称："+eList.get(i).getJobCode());
			List<DeptJob> dList = eList.get(i).getDeptJobList();
			for(int j=0;j<dList.size();j++){
			//System.out.println("二级部门编号："+dList.get(j).getDeptCode());
			String deptName = ss.seleDeptNameByDeptCode(dList.get(j).getDeptCode());
			System.out.println("二级部门的名称为："+deptName);
			}
		}
		
	}
	
	/**
	 * @author 马荣福
	 * @param param 从页面出来的参数，包括job_code,employee_code
	 * @return 
	 */
	@RequestMapping("addDeptJob.hzyc")
	public String addDeptJob(String param){
		System.out.println(param+"++++++++++++++++++++");
		boolean flag = ss.addEmployeeJob(param);
		if(flag){
			//添加成功
			return "../sysSetting/role/rFrame_right.jsp?flag=1";
		}else{
			return "../sysSetting/role/rFrame_right.jsp?flag=1";
		}
	}
}
