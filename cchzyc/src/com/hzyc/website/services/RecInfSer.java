package com.hzyc.website.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzyc.website.beans.Company;
import com.hzyc.website.beans.Dictionary;
import com.hzyc.website.beans.Employee;
import com.hzyc.website.beans.Pos;
import com.hzyc.website.mappers.CompanyMapper;
import com.hzyc.website.mappers.DictionaryMapper;
import com.hzyc.website.mappers.PosMapper;
import com.hzyc.website.system.Dict;




@Service
public class RecInfSer {

	@Autowired
	CompanyMapper cm;
	@Autowired
	PosMapper pm;
	@Autowired
	DictionaryMapper dm;
	/*public void insertCompany(Company comPo){
		
		String name = comPo.getName();
		
		if (!name.equals("0")) {
			
		}
		
	}*/
	
	/**
	 * 添加职位
	 * @author 郑斌
	 * 2017-12-22 下午10:38:06
	 * @return boolean 
	 */
	public boolean insertPos(Pos pos,HttpServletRequest request){

		boolean flag = false;
		
		Date date = new Date();
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		String time = sdf.format(date);
		
		//增加发布时间
		pos.setAnnounceTime(time);
		
		//发布人账号
		Employee e = (Employee)request.getSession().getAttribute("employee");
		String annId = e.getId() +"";
		//增加发布人
		pos.setAnnMan(annId);
		
		
		//将字典里的id转换成对应的值
		String posName = Dict.getDictNameByCode(pos.getPosName());
		pos.setPosName(posName);
		String eduRequire = Dict.getDictNameByCode(pos.getEduRequire());
		pos.setEduRequire(eduRequire);
		String posExperience = Dict.getDictNameByCode(pos.getPosExperience());
		pos.setPosExperience(posExperience);
		String recSt = Dict.getDictNameByCode(pos.getRecSt());
		pos.setRecSt(recSt);
		//10002兼职，如果为兼职则薪资为(NULL)
		if (pos.getStates().equals("10002")) {
			pos.setSalary("(NULL)");
		} else if (pos.getStates().equals("10001")) {
			String salary = Dict.getDictNameByCode(pos.getSalary());
			pos.setSalary(salary);
			pos.setPayStyle("(NULL)");
		}
		String states = Dict.getDictNameByCode(pos.getStates());
		pos.setStates(states);
		
		int result = pm.insertSelective(pos);
		
		if (result > 0) {
			flag = true;
		}
		
		return flag;
	}
	
	/**
	 * 查询id=id的公司信息
	 * @author 郑斌
	 *
	 * 2017-12-23 下午01:18:18
	 */
	public Company selCompanyById(int id){
		Company com = cm.selectByPrimaryKey(id);
		return com;
	}
	
	/**
	 * 去字典查福利
	 * @author 郑斌
	 * @return dList
	 * 2017-12-25 下午02:28:50
	 */
	public List<Dictionary> welfare(){
		String value = "company_welfare";
		List<Dictionary> dList = new ArrayList<Dictionary>();
		dList = dm.selByType(value);
		return dList;
	}
	
	/**
	 * 将职位信息修改
	 * @author 郑斌
	 * @return flag
	 * 2017-12-26 下午02:55:50
	 */
	public boolean updatePos(Pos pos){
		boolean flag = false;
		
		int result =  pm.updateByPrimaryKeySelective(pos);
		
		if (result > 0) {
			flag = true;
		}
		
		return flag;
	}
}
