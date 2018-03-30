package com.hzyc.website.services;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.hzyc.website.beans.Address;
import com.hzyc.website.beans.Company;
import com.hzyc.website.beans.Course;
import com.hzyc.website.beans.Dictionary;
import com.hzyc.website.beans.Job;
import com.hzyc.website.beans.Log;
import com.hzyc.website.beans.Privilege;
import com.hzyc.website.mappers.CompanyMapper;
import com.hzyc.website.mappers.CourseMapper;
import com.hzyc.website.mappers.DictionaryMapper;
import com.hzyc.website.mappers.JobMapper;
import com.hzyc.website.mappers.LogMapper;
import com.hzyc.website.mappers.PrivilegeMapper;
import com.hzyc.website.system.Dict;
import com.hzyc.website.utils.RedisPool;

import net.sf.json.JSONArray;
/**
 * 容器启动初始化数据字典等操作
 * 	
 * 异常统一由Controller进行try catch ,此处只抛出不处理
 * @author 邵帅
 */
public class InitService extends HttpServlet{
	
	//正向字典
	private LinkedHashMap<String,HashMap<String,String>> totalMap = new LinkedHashMap<String,HashMap<String,String>>();
	//装载反向数据字典
	private  HashMap<String,HashMap<String,String>>  reverseMap = new  HashMap<String,HashMap<String,String>>();
	
	@Autowired
	DictionaryMapper dm;
	@Autowired
	LogMapper lm;
	@Autowired
	JobMapper jm;
	@Autowired
	PrivilegeMapper pm;
	@Autowired
	CompanyMapper companyMapper;
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
	 * 系统日志记录
	 * 
	 * @author 邵帅
	 * @param log Log实体
	 */
	public void insertLog(Log log){
		//插入
		lm.insertSelective(log);
		
	}
	
	
	
	
	/**
	 * 获取地址信息
	 * 
	 * @author Lee
	 */
	
	public LinkedHashMap<String, HashMap<String, String>> getAddMap() {
		return addMap;
	}

	public void setAddMap(LinkedHashMap<String, HashMap<String, String>> addMap) {
		this.addMap = addMap;
	}

	public LinkedHashMap<String, HashMap<String, String>> getCountyMap() {
		return countyMap;
	}

	public void setCountyMap(
			LinkedHashMap<String, HashMap<String, String>> countyMap) {
		this.countyMap = countyMap;
	}
	
	
	private LinkedHashMap<String,HashMap<String,String>> addMap = new LinkedHashMap<String,HashMap<String,String>>();
	
	public LinkedHashMap<String,HashMap<String,String>> getAllAddress(){
		
		
		List<Address> aList = dm.selAllAddress();
		
		if(aList != null && aList.size() > 0){
			
			for(Address a : aList){
			
				LinkedHashMap<String,String> keyValue  =  new LinkedHashMap<String,String>();
				
				for( Address key : a.getaList()){
					keyValue.put(key.getCode(),key.getName());
					addMap.put(a.getpCode(), keyValue);
				}
			}
		}
		
		return addMap;
	}

	/**
	 * @author Lee
	 * 
	 * 加载地区信息…
	 */

	private LinkedHashMap<String,HashMap<String,String>> countyMap = new LinkedHashMap<String,HashMap<String,String>>();
	
	public LinkedHashMap<String,HashMap<String,String>>  getAllCounty(){
		
		List<Address> aList = dm.selAllCounty();
		
		if(aList != null && aList.size() > 0){
			
			for(Address a : aList){
			
				LinkedHashMap<String,String> keyValue  =  new LinkedHashMap<String,String>();
				
				for( Address key : a.getaList()){
					keyValue.put(key.getCode(),key.getName());
					countyMap.put(a.getpCode(), keyValue);
				}
			}
		}
		
		return countyMap;
		
	}
	
	/**
	 * 加载所有角色菜单  （用来角色权限过滤）
	 * 
	 * @return
	 */
	public List<Job> selRoleMenu(){
		return jm.selAllRoleMenu();
	}
	//查询所有权限
	public List<Privilege> selAllPrivilege(){
		return pm.selAllPrivilege();
	}
	
	/**
	 * 
	 * @author 何明
	 * 加载所有公司信息(用于招聘信息录入)
	 * 
	 * @return
	 * */
	
	public List<Company> selAllCompany(){
		
		return companyMapper.selAllCompany();
		
	}
	/**
	 * @author 马荣福
	 * @return
	 * 在初始化的时候把课程存在redis里面
	 */
	public List<Course> selCourse(){
		List<Course> list = cm.selCourseNoImg();
		String jsonString = JSONArray.fromObject(list).toString();
		System.out.println(jsonString+"-------------------");
		RedisPool.getClient().set("jsonString", jsonString);
		System.out.println("redis存储字段runoobkey:" + RedisPool.getClient().get("jsonString"));
		return list;
	}  
	
	
}
