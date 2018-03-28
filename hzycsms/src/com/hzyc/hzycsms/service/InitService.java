package com.hzyc.hzycsms.service;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import javax.servlet.ServletContextEvent;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import com.hzyc.hzycsms.bean.Course;
import com.hzyc.hzycsms.bean.Dictionary;
import com.hzyc.hzycsms.mapper.CourseMapper;
import com.hzyc.hzycsms.mapper.DictionaryMapper;
import com.hzyc.hzycsms.system.Dict;
import com.hzyc.hzycsms.util.RedisPool;

import net.sf.json.JSONArray;

/**
 * 容器启动初始化数据字典等操作
 * 	
 * 异常统一由Controller进行try catch ,此处只抛出不处理
 * @author 邵帅
 */
public class InitService {
	
	//正向字典
	private LinkedHashMap<String,HashMap<String,String>> totalMap = new LinkedHashMap<String,HashMap<String,String>>();
	//装载反向数据字典
	private  HashMap<String,HashMap<String,String>>  reverseMap = new  HashMap<String,HashMap<String,String>>();
	
	@Autowired
	DictionaryMapper dm;
	@Autowired
	CourseMapper cm;
	
	/**
	 * 数据字典初始化 缓存
	 * 		格式 ：  String(sex)，Map<String,String>(key:1,value:女)
	 * 			
	 * @author 邵帅
	 * @return boolean 缓存是否成功
	 * @throws Exception 统一由controller处理异常
	 */
	public boolean cacheDict() throws Exception{
    	//字典是否加载成功
		boolean isSuccess = false;
		//dao层加载数据字典
		List<Dictionary> typeList =  dm.selAllDicType();
		LinkedHashMap<String,String> keyValue  =  new LinkedHashMap<String,String>();;
		if(typeList != null && typeList.size() > 0){
			for(Dictionary dict : typeList){
				LinkedHashMap<String,String> map = new LinkedHashMap<String,String>();
				LinkedHashMap<String,String> reMap = new LinkedHashMap<String,String>();
				for(Dictionary dict2 : dict.getdList() ){
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
			Dict.setTotalMap(totalMap);
			Dict.setReverseMap(reverseMap);
			Dict.setDictDesMap(keyValue);
		}
		isSuccess = true;
		return isSuccess;
	}
	
	
	/**
	 * @author 郑斌
	 * @param request
	 * @return
	 * 在初始化的时候把课程图标缓存到服务器下
	 */
	public List<Course> courseIcon(ServletContextEvent sce){
		List<Course> cList = cm.selCourse();
		for (int i=0; i<cList.size(); i++) {
			if (cList.get(i).getIcon() != null && !cList.get(i).getIcon().equals("")) {
				FileOutputStream fos;
				try {
					String path = sce.getServletContext().getRealPath("/");
					String finalPathAndName = path +"img/course/"+cList.get(i).getIconName();
					fos = new FileOutputStream(finalPathAndName);
					fos.write(cList.get(i).getIcon());
					fos.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return cList;
	}
}
