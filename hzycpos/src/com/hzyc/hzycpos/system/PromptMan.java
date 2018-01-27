package com.hzyc.hzycpos.system;

import java.util.HashMap;
import java.util.Map;

import com.hzyc.hzycpos.domain.PromptMessage;

//ϵͳ��ʾ��Ϣ����
public class PromptMan {
	
	private PromptMan(){
		
	}
	private static Map<Integer,String> map = new HashMap<Integer,String>();
	
	public static void setMap(HashMap<Integer,String> map1){
		map = map1;
	}
	
	//����code����info
	public static PromptMessage convert(int code){
		PromptMessage pm = new PromptMessage();
		String info = map.get(code);
		
		if(info != null &&  !info.equals("")){
			pm.setCode(code);
			pm.setInfo(info);
		}else{
			//˵��û��ƥ���
			pm.setCode(000);
			pm.setInfo("δ֪����");
		}
		return pm;
	}
		
}
