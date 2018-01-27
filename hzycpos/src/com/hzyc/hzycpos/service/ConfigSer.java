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
	 * ���Ĺ��ܱ��״̬,����config�������,����Ȩ�ޱ�Ĳ˵���״̬
	 * @author ���ٸ�
	 * @param funcId ҳ����ѡ��Ĺ���
	 * @param vipCodeType ��Ա�������ɹ����ֵ
	 * @param vipType ��Ա���͵�ֵ
	 * @param vipMessage ��Ա���ŵ�ֵ
	 * @param vipIntegral ��Ա���ֵ�ֵ
	 * @return true Ϊִ�гɹ� false Ϊִ��ʧ��
	 */
	@Transactional
	public boolean updateConfig(String[] funcId,String vipCodeType,String vipType,String vipMessage,String vipIntegral){
		
		//���޸�֮ǰ�����еĹ��ܶ���0
		fm.updateInitState();
		//����ѡ�еĹ���״̬Ϊ1
		fm.updateFuncState(funcId);
		//����ѡ�еĹ��ܶ�Ӧ��Ȩ�޶�Ӧ��enabled
		pm.setPrivilege(funcId);
		//Ȩ��δѡ�е���0
		pm.updatePrivilege(funcId);
		int flag1 = 0;
		int flag2 = 0;
		int flag3 = 0;
		int flag4 = 0;
		//Ҫ�Ȳ�ѯconfig �������Ƿ���vipCodeType��vipType
		int temp1 = cm.selKey("vip_code_type");
		//����������ֵ�����û�������
		if(temp1 != 0){
			flag1 = cm.updateValueDict("vip_code_type", vipCodeType);
		}else{
			Config record = new Config();
			record.setKey("vip_code_type");
			record.setValueDict(Integer.parseInt(vipCodeType));
			flag1 = cm.insertSelective(record);
		}
		//����������ֵ�����û�������
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
		//�����Ա���ſ��������״̬Ϊ1
		if(vipMessage.equals("true")){
			record.setEnabled(1);
			record.setId(12);
			flag3 = pm.updateByPrimaryKeySelective(record);
		}else{
			record.setEnabled(0);
			record.setId(12);
			flag3 = pm.updateByPrimaryKeySelective(record);
		}
		//�����Ա���ֿ��������״̬Ϊ1
		if(vipIntegral.equals("true")){
			record.setEnabled(1);
			record.setId(11);
			flag4 = pm.updateByPrimaryKeySelective(record);
		}else{
			record.setEnabled(0);
			record.setId(11);
			flag4 = pm.updateByPrimaryKeySelective(record);
		}
		//���ִ�гɳɹ��ˣ���ô��¼Ϊ4 ������true
		if(flag1+flag2+flag3+flag4 == 4){
			return true;
		}else{
			return false;
		}
		
	}
}
