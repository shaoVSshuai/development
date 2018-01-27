package com.hzyc.hzycpos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzyc.hzycpos.domain.Config;
import com.hzyc.hzycpos.domain.Privilege;
import com.hzyc.hzycpos.mapper.ConfigMapper;
import com.hzyc.hzycpos.mapper.FuncMapper;
import com.hzyc.hzycpos.mapper.PrivilegeMapper;

@Service
public class ConfigSer {
	
	@Autowired
	ConfigMapper cm;
	
	@Autowired
	PrivilegeMapper pm;
	
	@Autowired
	FuncMapper fm;
	
	/**
	 * 更改功能表的状态,更改config表的配置,更改权限表的菜单的状态
	 * @author 马荣福
	 * @param funcId 页面上选择的功能
	 * @param vipCodeType 会员卡号生成规则的值
	 * @param vipType 会员类型的值
	 * @param vipMessage 会员短信的值
	 * @param vipIntegral 会员积分的值
	 * @return true 为执行成功 false 为执行失败
	 */
	@Transactional
	public boolean updateConfig(String[] funcId,String vipCodeType,String vipType,String vipMessage,String vipIntegral){
		
		//在修改之前把所有的功能都置0
		fm.updateInitState();
		//更改选中的功能状态为1
		fm.updateFuncState(funcId);
		//更改选中的功能对应的权限对应的enabled
		pm.setPrivilege(funcId);
		//权限未选中的置0
		pm.updatePrivilege(funcId);
		int flag1 = 0;
		int flag2 = 0;
		int flag3 = 0;
		int flag4 = 0;
		//要先查询config 表里面是否有vipCodeType，vipType
		int temp1 = cm.selKey("vip_code_type");
		//如果有则更改值，如果没有则添加
		if(temp1 != 0){
			flag1 = cm.updateValueDict("vip_code_type", vipCodeType);
		}else{
			Config record = new Config();
			record.setKey("vip_code_type");
			record.setValueDict(Integer.parseInt(vipCodeType));
			flag1 = cm.insertSelective(record);
		}
		//如果有则更改值，如果没有则添加
		int temp2 =cm.selKey("vip_type");
		if(temp2 != 0){
			flag2 = cm.updateValueDict("vip_type", vipType);
		}else{
			Config record = new Config();
			record.setKey("vip_type");
			record.setValueDict(Integer.parseInt(vipType));
			flag2 = cm.insertSelective(record);
		}
		
		Privilege record = new Privilege();
		//如果会员短信开启则更改状态为1
		if(vipMessage.equals("true")){
			record.setEnabled(1);
			record.setId(12);
			flag3 = pm.updateByPrimaryKeySelective(record);
		}else{
			record.setEnabled(0);
			record.setId(12);
			flag3 = pm.updateByPrimaryKeySelective(record);
		}
		//如果会员积分开启则更改状态为1
		if(vipIntegral.equals("true")){
			record.setEnabled(1);
			record.setId(11);
			flag4 = pm.updateByPrimaryKeySelective(record);
		}else{
			record.setEnabled(0);
			record.setId(11);
			flag4 = pm.updateByPrimaryKeySelective(record);
		}
		//如果执行成成功了，那么记录为4 ，返回true
		if(flag1+flag2+flag3+flag4 == 4){
			return true;
		}else{
			return false;
		}
		
	}
}
