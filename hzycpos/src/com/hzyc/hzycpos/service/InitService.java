package com.hzyc.hzycpos.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hzyc.hzycpos.domain.Config;
import com.hzyc.hzycpos.domain.Dict;
import com.hzyc.hzycpos.domain.PromptMessage;
import com.hzyc.hzycpos.mapper.ConfigMapper;
import com.hzyc.hzycpos.mapper.DictMapper;
import com.hzyc.hzycpos.mapper.PromptMessageMapper;
import com.hzyc.hzycpos.system.ConfigCache;
import com.hzyc.hzycpos.system.DataDict;
import com.hzyc.hzycpos.system.PromptMan;

/**
 * ����������ʼ�������ֵ�Ȳ���
 * 	
 * �쳣ͳһ��Controller����try catch ,�˴�ֻ�׳�������
 * @author ��˧
 */
public class InitService {
	
	
	@Autowired
	DictMapper dm; 
	@Autowired
	PromptMessageMapper pmm;
	@Autowired
	ConfigMapper cm;
	/**
	 * �����ֵ��ʼ�� ����
	 * 		��ʽ ��  String(sex)��Map<String,String>(key:1,value:Ů)
	 * 			
	 * @author ��˧
	 * @return boolean �����Ƿ�ɹ�
	 * @throws Exception ͳһ��controller�����쳣
	 */
	public boolean cacheDict() throws Exception{
		LinkedHashMap<String,HashMap<String,String>> totalMap = new LinkedHashMap<String,HashMap<String,String>>();
		//װ�ط��������ֵ�
		HashMap<String,HashMap<String,String>>  reverseMap = new  HashMap<String,HashMap<String,String>>();
    	//�ֵ��Ƿ���سɹ�
		boolean isSuccess = false;
		//dao����������ֵ�
		List<Dict> typeList =  dm.selAllDicType();
		LinkedHashMap<String,String> keyValue  =  new LinkedHashMap<String,String>();;
		if(typeList != null && typeList.size() > 0){
			for(Dict dict : typeList){
				LinkedHashMap<String,String> map = new LinkedHashMap<String,String>();
				LinkedHashMap<String,String> reMap = new LinkedHashMap<String,String>();
				for(Dict dict2 : dict.getdList() ){
					//�����ֵ�
					map.put(dict2.getDictCode(), dict2.getDictName());
					//�����ֵ�
					reMap.put( dict2.getDictName(),dict2.getDictCode());
				}
				totalMap.put(dict.getDictType(),map);
				//�����ֵ�װ��
				reverseMap.put(dict.getDictType(),reMap);
				//�ֵ��������map  sex --- �� 
				keyValue.put(dict.getDictType(), dict.getTypeName());
			}
			//��ֵ��dict�ֵ������
			DataDict.setTotalMap(totalMap);
			DataDict.setReverseMap(reverseMap);
			DataDict.setDictDesMap(keyValue);
		}
		isSuccess = true;
		return isSuccess;
	}
	
	/**
	 * �����û������� ��ʽ��<vip_type,41>
	 * @author ���ٸ�
	 * @return
	 * @throws Exception
	 */
	public boolean cacheConfig() throws Exception{
		boolean isSuccess = false;
		HashMap<String,Integer> cMap = new HashMap<String,Integer>();
		List<Config> configList = cm.selAllConfig();
		//����鵽�����������
		if(configList != null && configList.size()>0){
			for(Config c :configList){
				cMap.put(c.getKey(), c.getValueDict());
			}
			ConfigCache.setconfigMap(cMap);
		}
		isSuccess = true;
		System.out.println(ConfigCache.getValueDict("vip_type")+"--------");
		return isSuccess;
	}
	/**
	 * ��������ϵͳ��ʾ��Ϣ
	 * @return
	 * @throws Exception
	 */
	public boolean cacheMessage() throws Exception{
		//���� ��ʾ��Ϣ
		List<PromptMessage> me = pmm.selectAll();
		//HashMap<>
		HashMap<Integer,String> map =  new HashMap<Integer,String>();
		for(PromptMessage pm : me){
			map.put(pm.getCode(),pm.getInfo());
		}
		PromptMan.setMap(map);
		return true;
	}
	
	 
	 
	
}
