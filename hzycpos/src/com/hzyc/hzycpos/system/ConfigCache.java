package com.hzyc.hzycpos.system;

import java.util.HashMap;

/**
 * 系统缓存配置
 * 
 * @author 马荣福
 *
 */
public class ConfigCache {
	
	//不允许其他类实例化
	private ConfigCache(){
		
	}
	
	private static HashMap<String,Integer> configMap = new HashMap<String,Integer>();
	
	public static void setconfigMap(HashMap<String,Integer> configMapper1){
		configMap = configMapper1;
	}
	/**
	 * 根据传入的类型查找对应的配置的值
	 * @param type
	 * @return
	 */
	public static Integer getValueDict(String type){
		return configMap.get(type);
	}
}
