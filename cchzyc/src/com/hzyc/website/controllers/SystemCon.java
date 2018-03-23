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
 * ϵͳ���ÿ��Ʋ�
 * 
 * @author ��˧
 *
 */
@Controller
@RequestMapping(value="sysCon")
public class SystemCon {

	@Autowired
	SysSer ss;
	
	/**
	 * ɾ���ֵ���
	 * 
	 * @param id Ψһ����
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
	 * ��ѯ�ֵ���Ŀby���
	 * 
	 * @author ��˧
	 * @param type �ֵ����� (sex,university��)
	 * @return dict_bottom.jsp �����ֵ��ѯҳ
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
	 * ��ѯ�����ֵ����
	 * 
	 * @param code �ֵ����
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
	 * �����ֵ���
	 * 
	 * @param code �ֵ�Ψһ����[����]
	 * @param status Ҫ�ı��Ϊ��״̬
	 */
	@RequestMapping("/disableDict.hzyc")
	public void disableDict(String id,String status){
		ss.disableDict(id,status);
	}
	
	/**
	 * �޸������ֵ���
	 * ע��ֻ�޸� dict_name sort �� �Ƿ����enabled
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
			out.print("<script>alert('���³ɹ�...����ر�');window.close();</script>");
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * ��־��ѯ
	 * 
	 * @author ��˧
	 * @param log ��־ʵ��(����ֹʱ��)
	 * @return log_bottom.jsp ��ѯ���ҳ
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
	 * ����ʱ���ɾ����־
	 * 
	 * @author ��˧
	 * @param stime ��ʼʱ��
	 * @param etime ��ֹʱ��
	 * @param response
	 * @return ����ֵ101��ɾ���ɹ�  102��ɾ��ʧ��
	 */
	@RequestMapping("/delLogByTime.hzyc")
	@SystemLog(module="ϵͳ��־����",methods="ɾ��ϵͳ��־")
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
		//�����ѡģ������
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
	@SystemLog(module="��ɫ����",methods="��ӽ�ɫ")
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
	 * @author ������
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
			//gson�������߰���������DbUtils��ʹ��ʱʵ�������ɣ����Խ��������͵�����ת��Ϊ��Json�����ַ��������͵�����
			
			//ʹ�����ķ�ʽ����Ϣ���ظ�ҳ��
			response.setCharacterEncoding("utf-8");
			PrintWriter writer = response.getWriter();
			writer.print(data1);
			
			//ǿ��ˢ�º͹ر�
			writer.flush();
			writer.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @author ������
	 * @param  Ա��ʵ��
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/selJobByCode.hzyc")
	public  void  selJobByCode(String Code,HttpServletResponse response) throws IOException{
	   
	   // System.out.println("ְλ����-------����������"+jobName);
		List<Employee> eList = ss.selJobByCode(Code);

		for(int i = 0; i<eList.size() ;i++){
			//System.out.println("zhang______"+eList.get(i).getDeptJobList());
             //String jc=ss.selJobNameByJobCode(jobCode);
             System.out.println("ְλ���ƣ�"+eList.get(i).getJobCode());
			List<DeptJob> dList = eList.get(i).getDeptJobList();
			for(int j=0;j<dList.size();j++){
			//System.out.println("�������ű�ţ�"+dList.get(j).getDeptCode());
			String deptName = ss.seleDeptNameByDeptCode(dList.get(j).getDeptCode());
			System.out.println("�������ŵ�����Ϊ��"+deptName);
			}
		}
		
	}
	
	/**
	 * @author ���ٸ�
	 * @param param ��ҳ������Ĳ���������job_code,employee_code
	 * @return 
	 */
	@RequestMapping("addDeptJob.hzyc")
	public String addDeptJob(String param){
		System.out.println(param+"++++++++++++++++++++");
		boolean flag = ss.addEmployeeJob(param);
		if(flag){
			//��ӳɹ�
			return "../sysSetting/role/rFrame_right.jsp?flag=1";
		}else{
			return "../sysSetting/role/rFrame_right.jsp?flag=1";
		}
	}
}
