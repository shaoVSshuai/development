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
 * 容器启动初始化数据字典等操作
 * 	
 * 异常统一由Controller进行try catch ,此处只抛出不处理
 * @author 邵帅
 */
public class InitService {
	
	
	@Autowired
	DictMapper dm; 
	@Autowired
	PromptMessageMapper pmm;
	@Autowired
	ConfigMapper cm;
	/**
	 * 数据字典初始化 缓存
	 * 		格式 ：  String(sex)，Map<String,String>(key:1,value:女)
	 * 			
	 * @author 邵帅
	 * @return boolean 缓存是否成功
	 * @throws Exception 统一由controller处理异常
	 */
	public boolean cacheDict() throws Exception{
		LinkedHashMap<String,HashMap<String,String>> totalMap = new LinkedHashMap<String,HashMap<String,String>>();
		//装载反向数据字典
		HashMap<String,HashMap<String,String>>  reverseMap = new  HashMap<String,HashMap<String,String>>();
    	//字典是否加载成功
		boolean isSuccess = false;
		//dao层加载数据字典
		List<Dict> typeList =  dm.selAllDicType();
		LinkedHashMap<String,String> keyValue  =  new LinkedHashMap<String,String>();;
		if(typeList != null && typeList.size() > 0){
			for(Dict dict : typeList){
				LinkedHashMap<String,String> map = new LinkedHashMap<String,String>();
				LinkedHashMap<String,String> reMap = new LinkedHashMap<String,String>();
				for(Dict dict2 : dict.getdList() ){
					//正向字典
					map.put(dict2.getDictCode(), dict2.getDictName());
					//反向字典
					reMap.put( dict2.getDictName(),dict2.getDictCode());
				}
				totalMap.put(dict.getDictType(),map);
				//反向字典装载
				reverseMap.put(dict.getDictType(),reMap);
				//字典类别描述map  sex --- 男 
				keyValue.put(dict.getDictType(), dict.getTypeName());
			}
			//赋值给dict字典操作类
			DataDict.setTotalMap(totalMap);
			DataDict.setReverseMap(reverseMap);
			DataDict.setDictDesMap(keyValue);
		}
		isSuccess = true;
		return isSuccess;
	}
	
	/**
	 * 把配置缓存起来 格式是<vip_type,41>
	 * @author 马荣福
	 * @return
	 * @throws Exception
	 */
	public boolean cacheConfig() throws Exception{
		boolean isSuccess = false;
		HashMap<String,Integer> cMap = new HashMap<String,Integer>();
		List<Config> configList = cm.selAllConfig();
		//如果查到了配置则加载
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
	 * 缓存所有系统提示信息
	 * @return
	 * @throws Exception
	 */
	public boolean cacheMessage() throws Exception{
		//所有 提示信息
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
