package com.hzyc.website.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.hzyc.website.beans.City;
import com.hzyc.website.beans.Dept;
import com.hzyc.website.beans.DeptJob;
import com.hzyc.website.beans.Job;

import com.hzyc.website.mappers.DeptJobMapper;
import com.hzyc.website.beans.Province;
import com.hzyc.website.mappers.DeptJobMapper;
import com.hzyc.website.mappers.DeptMapper;
import com.hzyc.website.mappers.JobMapper;
import com.hzyc.website.utils.CreateCode;

@Service
public class DeptSer {
	
	@Autowired
	DeptMapper dm;
	
	@Autowired
	JobMapper jm;
	
	@Autowired
	DeptJobMapper djm;
	
	/**
	 * ��ѯһ������
	 * 
	 * @return
	 */
	public List<Dept> selLevel1(){
		return dm.selLevel1();
	}

	/**
	 * ��ѯ��������
	 * 
	 * @param code һ�����Ŵ���
	 * @return
	 * 
	 */
	public List<Dept> selLevel2(String code){
		return dm.selLevel2(code);
	}
	
	public List<Job> selDeptByLevel2(String code2){
		return jm.selDeptByLevel2(code2);
	}
   /**
	* ��ѯʡ��
	*@param provinceCode ʡ������
	*/
	public  List<Province> selProvince(){
		return dm.selProvince();
	}
	    
	
	public List<City> selCity(String provinceCode){
		return dm.selCity(provinceCode);
	}
	
	public String selProvinceCode(String name){
		return dm.selProvinceCode(name);
	}
	
	public List<Job> selJobByDept2(String code){
		return jm.selJobByDept2(code);
	}
	public  List<Dept> seletAllDept(String deptLevel){
		
		return dm.seletAllDept(deptLevel);
	}
	/**
	 * ��ѯÿ�����������µ�ְλ
	 * 
	 * @param code һ�����Ŵ���
	 * @return
	 * 
	 */
	public List<DeptJob> selectJobCodeByDeptCode(String DeptCode){
		
		return djm.selectJobCodeByDeptCode(DeptCode);
		
	}
	public List<Dept> selectDeptUntilEmployee(String deptCode){
		
		
		return dm.selectDeptUntilEmployee(deptCode);
	}
	public List<Job> selectJobEmplee(String jobcode){
		
		return jm.selectJobEmplee(jobcode);
	}
	/**
	 * ����������һ�����ţ�Ĭ�ϲ�һ���������Ž�����������
	 * @author ���ٸ�
	 * @param request
	 * @param deptOne ����һ������
	 * @param deptOneCode	����һ��code����Ӷ�������ʱ��
	 * @param deptTwo ���Ŷ�������
	 * @param remark	��ע
	 */
	@Transactional
	public int addDept(String deptOne,String deptOneCode,String deptTwo,String remark){
		
		Dept dept = new Dept();
		//�˶����������洢����һ�����ŵ�ʱ��Ĭ�ϲ�һ��������
		Dept dept2 = new Dept();
		CreateCode cc = new CreateCode();
		//һ�����ŵ�����
		if(deptOne!=null && deptOne.length()>0){
			dept.setDeptName(deptOne);
			dept.setDeptLevel("1");
		}
		dept.setDeptRemark(remark);
		
		//���Ȳ�ѯһ�����ŵı��
		String MaxCode = dm.selLevel1Code();
		//֤���鵽�����ݣ�A1,A0
		if(MaxCode != null && MaxCode.length()>0){
			String deptCode = "";
			//�����õ�ֵ��A1,A2,��ô������λ�ķ���
			if(MaxCode.length()==2){
				deptCode = cc.addCodeLiangwei(MaxCode);
			}else{
			}
			dept2.setDeptCode(deptCode+"0");
			dept2.setDeptLevel("2");
			dept2.setDeptName("����");
			dept2.setDeptRemark("����");
			dept.setDeptCode(deptCode);
		}else{
			dept.setDeptCode("A1");
		}
		
		//�������ŵĶ���
		System.out.println(deptOneCode+"---");
		if(deptOneCode !=null && deptOneCode.length()>0){
			if(deptTwo !=null && deptTwo.length() >0){
				String level2Code = dm.selLevel2Code(deptOneCode);
				System.out.println("-------+++++");
				level2Code = cc.addCodeSanwei(level2Code);
				dept.setDeptName(deptTwo);
				dept.setDeptLevel("2");
				dept.setDeptCode(level2Code);
			}
		}else{
			
		}
		//�����ӵ��Ƕ���������ô����������
		if(dept.getDeptLevel().equals("1")){
			dm.insertSelective(dept2);
		}
		return dm.insertSelective(dept);
	}
	
	/**
	 * @author �����������񣬱�֤��ͬʱ�������������������
	 * @param deptTwo
	 * @param job
	 * @return
	 */
	@Transactional(rollbackFor = { Exception.class }) 
	public boolean addJob(String deptTwo,Job job){
		String maxCode = jm.selMaxCode();
		String newCode = Integer.parseInt(maxCode)+1+"";
		job.setJobCode(newCode);
		
		//�½�һ��dept_job
		DeptJob deptJob = new DeptJob();
		deptJob.setJobCode(newCode);
		deptJob.setDeptCode(deptTwo);
		//����ʵ���Ѿ�׼�������,ͬʱ�ɹ�֮��
		boolean sucess1 = false;
		boolean sucess2 = false;
		try {  
			sucess1 = jm.insertSelective(job);
			sucess2 = djm.insertSelective(deptJob);
	        } catch (Exception e) {  
	            e.printStackTrace();     
	        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//������һ���ˣ�����֮�����doDbStuff2()�����쳣,                                                                                       //doDbStuff1()�ǻ�ع���  
	        }  
		return sucess1&&sucess2;
	}
}
