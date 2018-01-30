package com.hzyc.hzycpos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzyc.hzycpos.domain.VipKind;
import com.hzyc.hzycpos.mapper.VipKindMapper;

/**
 * ��Ա����Ĳ���
 * 
 * @author ZHENGBIN	
 *
 */
@Service
public class VipKindSer {

	
	@Autowired
	private VipKindMapper vkm;
	
	/**
	 * �������
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
	 * ��ѯ����vip����
	 * 
	 * @author ZHENGBIN
	 */
	public List<VipKind> selVipKind(){
		List<VipKind> vkList = vkm.selAllASC();
		return vkList;
	}
}
