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
 * ����������ʼ�������ֵ�Ȳ���
 * 	
 * �쳣ͳһ��Controller����try catch ,�˴�ֻ�׳�������
 * @author ��˧
 */
public class InitService extends HttpServlet{
	
	//�����ֵ�
	private LinkedHashMap<String,HashMap<String,String>> totalMap = new LinkedHashMap<String,HashMap<String,String>>();
	//װ�ط��������ֵ�
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
	
	/**
	 * ϵͳ��־��¼
	 * 
	 * @author ��˧
	 * @param log Logʵ��
	 */
	public void insertLog(Log log){
		//����
		lm.insertSelective(log);
		
	}
	
	
	
	
	/**
	 * ��ȡ��ַ��Ϣ
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
	 * ���ص�����Ϣ��
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
	 * �������н�ɫ�˵�  ��������ɫȨ�޹��ˣ�
	 * 
	 * @return
	 */
	public List<Job> selRoleMenu(){
		return jm.selAllRoleMenu();
	}
	//��ѯ����Ȩ��
	public List<Privilege> selAllPrivilege(){
		return pm.selAllPrivilege();
	}
	
	/**
	 * 
	 * @author ����
	 * �������й�˾��Ϣ(������Ƹ��Ϣ¼��)
	 * 
	 * @return
	 * */
	
	public List<Company> selAllCompany(){
		
		return companyMapper.selAllCompany();
		
	}
	/**
	 * @author ���ٸ�
	 * @return
	 * �ڳ�ʼ����ʱ��ѿγ̴���redis����
	 */
	public List<Course> selCourse(){
		List<Course> list = cm.selCourseNoImg();
		String jsonString = JSONArray.fromObject(list).toString();
		System.out.println(jsonString+"-------------------");
		RedisPool.getClient().set("jsonString", jsonString);
		System.out.println("redis�洢�ֶ�runoobkey:" + RedisPool.getClient().get("jsonString"));
		return list;
	}  
	
	
}
