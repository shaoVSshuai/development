package com.hzyc.hzycpos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.hzyc.hzycpos.domain.Role;
import com.hzyc.hzycpos.mapper.RoleMapper;

@Service
public class RoleSer {
	
	@Autowired
	RoleMapper rm;
	
	public List<Role> selAll(){
		
		return rm.selAll(); 
	}
	
	
}
