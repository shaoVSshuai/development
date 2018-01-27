package com.hzyc.hzycpos.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzyc.hzycpos.domain.Config;
import com.hzyc.hzycpos.domain.Vip;
import com.hzyc.hzycpos.domain.VipKind;
import com.hzyc.hzycpos.mapper.ConfigMapper;
import com.hzyc.hzycpos.mapper.VipKindMapper;
import com.hzyc.hzycpos.mapper.VipMapper;

/**
 * 对vip进行处理
 * 
 * @author ZHENGBIN
 *
 */
@Service
public class VipSer {
	
	@Autowired
	private ConfigMapper cm;
	@Autowired
	private VipKindMapper vkm;
	@Autowired
	private VipMapper vm;
	
	/**
	 * 查询是什么类型的会员
	 * @return 0 为打折会员 1 为打折&储值会员
	 * 
	 * @author ZHENGBIN
	 *
	 */
	public String selType(){
		String result = "1";
		Config c = cm.selByKey("vip_type");
		
		//如果没有则数据库错了
		if (c == null) {
			result = "2";
		} else {
			//41为打折 42为储值并打折
			if (c.getValueDict() == 41) {
				result = "0";
			} else {
			}
		}
		return result;
	}
	
	public boolean addVip(Vip vip){
		boolean flag = true;
		int hyKindId;
		List<VipKind> vkList;
		
		Date date = new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSSS");
		SimpleDateFormat sdfHyDate=new SimpleDateFormat("yyyy-MM-dd");
		
		//会员号
		String hyCode = sdf.format(date);
		vip.setHyCode(hyCode);
		
		//获取充值金额
		double hyTotal = vip.getHyTotal();
		System.out.println("------------1");
		//判断是啥类型的会员
		String result = selType();
		
		if (hyTotal >= 0 && result == "1"){
			//进来了证明是打折并储值会员
			System.out.println("------------3");
			//余额
			vip.setHyRemainMoney(hyTotal);
			System.out.println("------------4");
			//查询小于等于充值金额的所有会员类型
			vkList = vkm.selByNeedMoney(hyTotal);
			System.out.println("------------2");
			if (vkList.size() != 0 && vkList != null) {
				hyKindId = vkList.get(0).getId();
				vip.setHyKindId(hyKindId);
			} else {
				//没有默认值
				flag = false;
			}
		} else if (hyTotal == 0 && result == "0") {
			//进来了证明是打折会员
			
			//降序查询所有会员类型
			vkList = vkm.selAllASC();
			if (vkList.size() != 0 && vkList != null) {
				hyKindId = vkList.get(0).getId();
				vip.setHyKindId(hyKindId);
			} else {
				//没有默认值
				flag = false;
			}
		} else {
			//错误操作
			flag = false;
		}
		
		String hyCard = vip.getHyCard();
		
		String birthday = null;
		if (hyCard != null) {
			if (hyCard.length() == 18) { 
	            birthday = hyCard.substring(6, 10) + "-" 
	                    + hyCard.substring(10, 12) + "-" 
	                    + hyCard.substring(12, 14); 
	        } else if (hyCard.length() == 15) { 
	            birthday = hyCard.substring(6, 8) + "-" 
	                    + hyCard.substring(8, 10) + "-" 
	                    + hyCard.substring(10, 12); 
	        } 
		}
		vip.setHyBirthday(birthday);
		
		String hyDate = sdfHyDate.format(date);
		vip.setHyDate(hyDate);
		
		//顶上操作失败就没必要添加到数据库了
		if (flag) {
			System.out.println("----------");
			int rs = vm.insertSelective(vip);
			System.out.println("123123");
			//添加失败
			if (rs <= 0) {
				flag = false;
			}
		}
		
		return flag;
	}
}
