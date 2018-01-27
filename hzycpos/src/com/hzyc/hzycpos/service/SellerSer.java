package com.hzyc.hzycpos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzyc.hzycpos.domain.Seller;
import com.hzyc.hzycpos.mapper.SellerMapper;


/**
 * �����̵���Ϣ
 * 
 * @author ZHENGBIN
 *
 */
@Service
public class SellerSer {

	@Autowired
	private SellerMapper sm;
	
	/**
	 * ��ӵ�����Ϣ
	 * 
	 * @author ZHENGBIN
	 *
	 */
	public boolean addSeller(Seller seller){
		boolean flag = false;
		int result = sm.insertSelective(seller);
		
		if (result > 0) {
			
			flag = true;
			
		}
		
		return flag;
	}
	
	
}
