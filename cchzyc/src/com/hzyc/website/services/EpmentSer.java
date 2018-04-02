package com.hzyc.website.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzyc.website.beans.EmploymentNewsWithBLOBs;
import com.hzyc.website.mappers.EmploymentNewsMapper;

@Service
public class EpmentSer {

	@Autowired
	private EmploymentNewsMapper enm;
	
	/**
	 * 多条件查询
	 * @author 郑斌
	 * @param enw
	 * @return List<EmploymentNewsWithBLOBs>
	 */
	public List<EmploymentNewsWithBLOBs> selConditionEmp(EmploymentNewsWithBLOBs enw){
		List<EmploymentNewsWithBLOBs> enList = enm.selConditionEmp(enw);
		
		return enList;
	}
}
