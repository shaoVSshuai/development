package com.hzyc.hzycpos.system;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.hzyc.hzycpos.domain.Dict;

/**
 * ϵͳ���������ֵ�
 * 
 * @author SHAOSHUAI
 *
 */
public class DataDict {
	
	//������������ʵ����
	private DataDict(){
		
	}
	
	//�����ֵ�
	private static LinkedHashMap<String,HashMap<String,String>> totalMap = new LinkedHashMap<String,HashMap<String,String>>();
	//װ�ط��������ֵ�
	private static HashMap<String,HashMap<String,String>>  reverseMap = new  HashMap<String,HashMap<String,String>>();
	//װ���ֵ���������
	private static  HashMap<String,String>   dictDesMap = new  HashMap<String,String> ();

	
	public static void setTotalMap(LinkedHashMap<String,HashMap<String,String>> totalMap1){
		totalMap = totalMap1;
	}
	public static void setReverseMap(HashMap<String,HashMap<String,String>> reverseMap1){
		reverseMap = reverseMap1;
	}
	public static void setDictDesMap(HashMap<String,String> dictDesMap1){
		dictDesMap = dictDesMap1;
	}
	
	
	
	/**
	 * ����type �г������ֵ���  
	 * 		����param:sex  
	 * 		return :�� , Ů
	 * 
	 * @author ��˧
	 * @param type �ֵ����
	 * @return Dict[] �����ֵ�ѡ��
	 * @throws NullPointerException ���ϽṹΪ���׳�
	 */
	public static Dict[] getDictByType(String type) throws NullPointerException{
		HashMap<String,String> getMap =  totalMap.get(type);
		if(getMap != null && getMap.size() > 0 ){
			Dict[] dictArray = new Dict[getMap.size()];
			int i = 0;
			for(Map.Entry<String, String> map : getMap.entrySet() ){
				Dict dict = new Dict();
				//�����ֵ�����
				dict.setDictCode(map.getKey());
				dict.setDictName(map.getValue());
				dictArray[i] = dict;
				i++;
			}
			/*//
			for(Dict d: dictArray){
				System.out.println(d);
			}*/
			return dictArray;
		}else{
			return null;
		}
		
	}
	
	/**
	 * ���� �ֵ���� �ֵ���� ���ҳ�Ψһ�ֵ���
	 * 		����param1:sex param2:1 
	 * 		return: ��
	 * 
	 * @author ��˧
	 * @param type �ֵ����
	 * @param code �ֵ���ϸ���루Ψһ��
	 * @return String �ֵ�����
	 */
	public static String getDictName(String type,String code){
		//��ȡvalue
		HashMap<String,String> getMap =  totalMap.get(type);
		String getValue = getMap.get(code);
		return getValue;
	}
	

	/**
	 * ֱ�Ӹ���   �ֵ���� ���ҳ�Ψһ�ֵ���
	 * 		����param1: 1 
	 * 		return: ��
	 * 
	 * @author ��˧
	 * @param code �ֵ���ϸ���루Ψһ��
	 * @return String �ֵ�����
	 */
	public static String getDictNameByCode( String code){
		String value = "";
		for(Map.Entry<String, HashMap<String,String>> entry : totalMap.entrySet()){
			HashMap<String,String> map  = entry.getValue();
			for(Map.Entry<String, String> entry2 : map.entrySet()){
				String key  = entry2.getKey();
				if(key.equals(code)){
					value = entry2.getValue();
				}
			}
		}
		return value.equals("") ? "����" : value;
	}
	
	/**
	 * �����ֵ���Ŀ��ȡ����(�����ȡ)
	 * 		���� param1:sex param2:��  
	 * 			returnCode: 1
	 * 
	 * @author ��˧
	 * @param type �ֵ����
	 * @param code �ֵ�����
	 * @return
	 */
	public static String getReverseCode(String type,String name){
		//��ȡvalue
		HashMap<String,String> getMap =  reverseMap.get(type);
		String getValue = getMap.get(name);
		return getValue;
	}
	
	
	/**
	 * ��ȡ�����ֵ����  sex  university��
	 * 
	 * @return
	 */
	public static Dict[] getAllType(){
		Dict[] array =  new Dict[dictDesMap.size()];
		int i = 0;
		for(Map.Entry<String, String> entry :dictDesMap.entrySet() ){
			Dict dict = new Dict();
			dict.setDictType(entry.getKey());
			System.out.println(entry.getValue());
			dict.setTypeName(entry.getValue());
			array[i] = dict;
			i++;
		}
		return array;
	}
	
	
	

}
