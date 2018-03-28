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
	 * @author ֣��
	 * @param request
	 * @return
	 * �ڳ�ʼ����ʱ��ѿγ�ͼ�껺�浽��������
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
