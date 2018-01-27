package com.hzyc.hzycpos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzyc.hzycpos.domain.storeInfo;
import com.hzyc.hzycpos.mapper.storeInfoMapper;

@Service
public class StoreInfoSer {
	
	@Autowired
	storeInfoMapper sim;
	//加载所有商店
	public List<storeInfo> selAll(){
		
		return sim.selAll();
		
	}
}
