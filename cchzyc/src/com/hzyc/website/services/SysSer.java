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
	 * ��ѯ����ְλ��һ�����ţ���������
	 * @author ������
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
	 * ��������ɾ���ֵ���
	 * 
	 * @param id ����
	 * @return Boolean ɾ���Ƿ�ɹ�
	 * @throws NumberFormatException Stringת��Integerʧ�ܡ��׳�
	 */
	public boolean delDictByCode(String id) throws NumberFormatException{
		return dm.deleteByPrimaryKey(Integer.parseInt(id)) > 0 ? true : false;
	}
	
	//�����ֵ���[������] Ȼ������ˢ�»��������ֵ�
	public void disableDict(String code,String status){
		 int i = dm.disableDict( code, status);
		 if(i > 0 ){
			//���������ֵ�
				InitService init =  (InitService)BeanUtil.getBean("InitService");
		    	//��ȡ��ʼ��serviceִ�л������
		    	try {
					init.cacheDict();
					System.out.println("�ָ���һ��");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 }
	}
	
	/**
	 * �޸��ֵ���
	 * 
	 * @param dict
	 */
	public void updDict(Dictionary dict){
		int i = dm.updDict(dict);
		 if(i > 0 ){
				//���������ֵ�
					InitService init =  (InitService)BeanUtil.getBean("InitService");
			    	//��ȡ��ʼ��serviceִ�л������
			    	try {
						init.cacheDict();
						System.out.println("�ָ���һ��");
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
	 * ʵ�ְ�Ա���ı�ź�ְλ�ı�Ų�����еĹ���
	 * @author ���ٸ�
	 * @param param
	 * @return
	 */
	@Transactional(rollbackFor = { Exception.class }) 
	public boolean addEmployeeJob(String param){
		boolean flag = false;
		boolean flag1 = false;
		//���Ȳ��param
		String[] code = param.split(",");
		//�����Ա����code
		String employeeCode = code[code.length-1];
		//�ڲ���֮ǰ�Ȱ����е�ְλɾ��
		flag = ejm.deleteByEmployeeCode(employeeCode);
		//ѭ�����ɲ�ͬ�Ķ���,���Ҳ���
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

