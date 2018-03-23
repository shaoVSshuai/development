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
	 * 查询一级部门
	 * 
	 * @return
	 */
	public List<Dept> selLevel1(){
		return dm.selLevel1();
	}

	/**
	 * 查询二级部门
	 * 
	 * @param code 一级部门代码
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
	* 查询省市
	*@param provinceCode 省级代码
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
	 * 查询每个二级部门下的职位
	 * 
	 * @param code 一级部门代码
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
	 * 如果插入的是一级部门，默认插一个二级部门叫做（隶属）
	 * @author 马荣福
	 * @param request
	 * @param deptOne 部门一的名称
	 * @param deptOneCode	部门一的code（添加二级部门时）
	 * @param deptTwo 部门二的名称
	 * @param remark	备注
	 */
	@Transactional
	public int addDept(String deptOne,String deptOneCode,String deptTwo,String remark){
		
		Dept dept = new Dept();
		//此对象是用来存储插入一级部门的时候默认插一个本部的
		Dept dept2 = new Dept();
		CreateCode cc = new CreateCode();
		//一级部门的名称
		if(deptOne!=null && deptOne.length()>0){
			dept.setDeptName(deptOne);
			dept.setDeptLevel("1");
		}
		dept.setDeptRemark(remark);
		
		//首先查询一级部门的编号
		String MaxCode = dm.selLevel1Code();
		//证明查到了数据，A1,A0
		if(MaxCode != null && MaxCode.length()>0){
			String deptCode = "";
			//如果获得的值是A1,A2,那么调用两位的方法
			if(MaxCode.length()==2){
				deptCode = cc.addCodeLiangwei(MaxCode);
			}else{
			}
			dept2.setDeptCode(deptCode+"0");
			dept2.setDeptLevel("2");
			dept2.setDeptName("隶属");
			dept2.setDeptRemark("本部");
			dept.setDeptCode(deptCode);
		}else{
			dept.setDeptCode("A1");
		}
		
		//二级部门的对象
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
		//如果添加的是二级部门那么不插入隶属
		if(dept.getDeptLevel().equals("1")){
			dm.insertSelective(dept2);
		}
		return dm.insertSelective(dept);
	}
	
	/**
	 * @author 这里用了事务，保证能同时向两个表里面插入数据
	 * @param deptTwo
	 * @param job
	 * @return
	 */
	@Transactional(rollbackFor = { Exception.class }) 
	public boolean addJob(String deptTwo,Job job){
		String maxCode = jm.selMaxCode();
		String newCode = Integer.parseInt(maxCode)+1+"";
		job.setJobCode(newCode);
		
		//新建一个dept_job
		DeptJob deptJob = new DeptJob();
		deptJob.setJobCode(newCode);
		deptJob.setDeptCode(deptTwo);
		//两个实体已经准备完毕了,同时成功之后
		boolean sucess1 = false;
		boolean sucess2 = false;
		try {  
			sucess1 = jm.insertSelective(job);
			sucess2 = djm.insertSelective(deptJob);
	        } catch (Exception e) {  
	            e.printStackTrace();     
	        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//就是这一句了，加上之后，如果doDbStuff2()抛了异常,                                                                                       //doDbStuff1()是会回滚的  
	        }  
		return sucess1&&sucess2;
	}
}
