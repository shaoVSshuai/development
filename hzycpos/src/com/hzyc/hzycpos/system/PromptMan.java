package com.hzyc.hzycpos.system;

import java.util.HashMap;
import java.util.Map;

import com.hzyc.hzycpos.domain.PromptMessage;

//系统提示信息管理
public class PromptMan {
	
	private PromptMan(){
		
	}
	private static Map<Integer,String> map = new HashMap<Integer,String>();
	
	public static void setMap(HashMap<Integer,String> map1){
		map = map1;
	}
	
	//根据code查找info
	public static PromptMessage convert(int code){
		PromptMessage pm = new PromptMessage();
		String info = map.get(code);
		
		if(info != null &&  !info.equals("")){
			pm.setCode(code);
			pm.setInfo(info);
		}else{
			//说明没有匹配的
			pm.setCode(000);
			pm.setInfo("未知错误");
		}
		return pm;
	}
		
}
