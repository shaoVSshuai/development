package com.hzyc.hzycpos.system;

import java.util.HashMap;

/**
 * ϵͳ��������
 * 
 * @author ���ٸ�
 *
 */
public class ConfigCache {
	
	//������������ʵ����
	private ConfigCache(){
		
	}
	
	private static HashMap<String,Integer> configMap = new HashMap<String,Integer>();
	
	public static void setconfigMap(HashMap<String,Integer> configMapper1){
		configMap = configMapper1;
	}
	/**
	 * ���ݴ�������Ͳ��Ҷ�Ӧ�����õ�ֵ
	 * @param type
	 * @return
	 */
	public static Integer getValueDict(String type){
		return configMap.get(type);
	}
}
