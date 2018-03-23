package com.hzyc.website.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzyc.website.beans.DeptJob;
import com.hzyc.website.beans.Dictionary;
import com.hzyc.website.beans.Employee;
import com.hzyc.website.beans.EmployeeJob;
import com.hzyc.website.beans.Job;
import com.hzyc.website.beans.Log;
import com.hzyc.website.mappers.DeptJobMapper;
import com.hzyc.website.mappers.DeptMapper;
import com.hzyc.website.mappers.DictionaryMapper;
import com.hzyc.website.mappers.EmployeeJobMapper;
import com.hzyc.website.mappers.EmployeeMapper;
import com.hzyc.website.mappers.JobMapper;
import com.hzyc.website.mappers.LogMapper;
import com.hzyc.website.utils.BeanUtil;

/**
 * @author SHAOSHUAI
 *
 */
/**
 * @author SHAOSHUAI
 *
 */
@Service
public class SysSer {
	
	@Autowired
	DictionaryMapper dm;
	@Autowired
	LogMapper lm;
	@Autowired
	JobMapper jm;
	@Autowired
	DeptJobMapper djm;
	@Autowired
	EmployeeMapper em;
	@Autowired
	DeptMapper deptMapper;
	@Autowired
	EmployeeJobMapper ejm;
	/**
	 * 查询所有职位，一级部门，二级部门
	 * @author 张阳媚
	 * @param 
	 * @return List<DeptJob> 
	 */
	public List<DeptJob> selRightRole(){
		return djm.selRightRole();
	}
	public List<Employee> selJobByCode(String jobCode){
		return em.selJobByCode(jobCode);
	}
	public  String selJobCodeByJobName(String jobName){
		return jm.selJobCodeByJobName(jobName);
	}
	public  String seleDeptNameByDeptCode(String deptCode){
		return deptMapper.seleDeptNameByDeptCode(deptCode);
	}
	public  String selJobNameByJobCode(String jobCode){
		return jm.selJobNameByJobCode(jobCode);
	}
	
	/**
	 * 
	 * 根据主键删除字典项
	 * 
	 * @param id 主键
	 * @return Boolean 删除是否成功
	 * @throws NumberFormatException String转换Integer失败。抛出
	 */
	public boolean delDictByCode(String id) throws NumberFormatException{
		return dm.deleteByPrimaryKey(Integer.parseInt(id)) > 0 ? true : false;
	}
	
	//禁用字典项[或启用] 然后重新刷新缓存数据字典
	public void disableDict(String code,String status){
		 int i = dm.disableDict( code, status);
		 if(i > 0 ){
			//更新数据字典
				InitService init =  (InitService)BeanUtil.getBean("InitService");
		    	//获取初始化service执行缓存操作
		    	try {
					init.cacheDict();
					System.out.println("又更新一次");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 }
	}
	
	/**
	 * 修改字典项
	 * 
	 * @param dict
	 */
	public void updDict(Dictionary dict){
		int i = dm.updDict(dict);
		 if(i > 0 ){
				//更新数据字典
					InitService init =  (InitService)BeanUtil.getBean("InitService");
			    	//获取初始化service执行缓存操作
			    	try {
						init.cacheDict();
						System.out.println("又更新一次");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			 }
	}
	
	public Dictionary selDictByCode(String code){
		return dm.selDictByCode(code);
	}
	
	public List<Dictionary> selByType(String type){
		return dm.selByType(type);
	}
	
	
	public List<Log> selLogByTime(Log log){
		return lm.selectByTime(log);
	}
	
	public int delLogByTime(String stime,String etime){
		return lm.delLogByTime( stime,  etime);
	}
	
	public List<Job> selRole(Job job,int isMohu){
		if(isMohu==1 && job.getJobName()!=null && !job.getJobName().trim().equals("")){
			job.setJobName("%"+job.getJobName()+"%");
		}
		return jm.selRole(job);
	}
	
	public boolean addRole(Job job){
		return jm.insertSelective(job);
	}
	
	/**
	 * 实现把员工的编号和职位的编号插入表中的功能
	 * @author 马荣福
	 * @param param
	 * @return
	 */
	@Transactional(rollbackFor = { Exception.class }) 
	public boolean addEmployeeJob(String param){
		boolean flag = false;
		boolean flag1 = false;
		//首先拆分param
		String[] code = param.split(",");
		//这个是员工的code
		String employeeCode = code[code.length-1];
		//在插入之前先把已有的职位删除
		flag = ejm.deleteByEmployeeCode(employeeCode);
		//循环生成不同的对象,并且插入
		for(int i = 0;i<code.length-1;i++){
			EmployeeJob ej = new EmployeeJob();
			ej.setCode(employeeCode);
			ej.setJobCode(code[i]);
			flag1 = ejm.insertSelective(ej);
		}
		System.out.println(code[code.length-1]);
		return flag && flag1;
	}
	public static void main(String[] args) {
		String a = "1d";
		Integer.parseInt(a);
		
	}
	
	
}

