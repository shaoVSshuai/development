package com.hzyc.hzycsms.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hzyc.hzycsms.bean.Dictionary;
import com.hzyc.hzycsms.mapper.DictionaryMapper;
import com.hzyc.hzycsms.system.Dict;

/**
 * ����������ʼ�������ֵ�Ȳ���
 * 	
 * �쳣ͳһ��Controller����try catch ,�˴�ֻ�׳�������
 * @author ��˧
 */
public class InitService {
	
	//�����ֵ�
	private LinkedHashMap<String,HashMap<String,String>> totalMap = new LinkedHashMap<String,HashMap<String,String>>();
	//װ�ط��������ֵ�
	private  HashMap<String,HashMap<String,String>>  reverseMap = new  HashMap<String,HashMap<String,String>>();
	
	@Autowired
	DictionaryMapper dm;
	
	
	/**
	 * �����ֵ��ʼ�� ����
	 * 		��ʽ ��  String(sex)��Map<String,String>(key:1,value:Ů)
	 * 			
	 * @author ��˧
	 * @return boolean �����Ƿ�ɹ�
	 * @throws Exception ͳһ��controller�����쳣
	 */
	public boolean cacheDict() throws Exception{
    	//�ֵ��Ƿ���سɹ�
		boolean isSuccess = false;
		//dao����������ֵ�
		List<Dictionary> typeList =  dm.selAllDicType();
		LinkedHashMap<String,String> keyValue  =  new LinkedHashMap<String,String>();;
		if(typeList != null && typeList.size() > 0){
			for(Dictionary dict : typeList){
				LinkedHashMap<String,String> map = new LinkedHashMap<String,String>();
				LinkedHashMap<String,String> reMap = new LinkedHashMap<String,String>();
				for(Dictionary dict2 : dict.getdList() ){
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
			Dict.setTotalMap(totalMap);
			Dict.setReverseMap(reverseMap);
			Dict.setDictDesMap(keyValue);
		}
		isSuccess = true;
		return isSuccess;
	}
	
	
	
	  
	
}
