package com.hzyc.hzycpos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzyc.hzycpos.domain.VipKind;
import com.hzyc.hzycpos.mapper.VipKindMapper;

/**
 * 会员种类的操作
 * 
 * @author ZHENGBIN	
 *
 */
@Service
public class VipKindSer {

	
	@Autowired
	private VipKindMapper vkm;
	
	/**
	 * 种类添加
	 * 
	 * @author ZHENGBIN	
	 * 
	 */
	public boolean addVipKind(VipKind vipKind){
		boolean flag = false;
		
		int result = vkm.insertSelective(vipKind);
		System.out.println(result);
		if (result > 0) {
			flag = true;
		}
		
		return flag;
	}
	
	/**
	 * 查询所有vip类型
	 * 
	 * @author ZHENGBIN
	 */
	public List<VipKind> selVipKind(){
		List<VipKind> vkList = vkm.selAllASC();
		return vkList;
	}
}
