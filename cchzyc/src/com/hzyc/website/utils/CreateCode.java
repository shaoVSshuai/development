package com.hzyc.website.utils;

import java.util.HashMap;
import java.util.Map;

public class CreateCode {
	
	static Map<String,String> map = null;
	static Map<String,String> reverMap = null;
	static{
		
		map = new HashMap<String,String>();
		reverMap = new HashMap<String,String>();
		//添加
		map.put("0", "0");
		map.put("1", "1");
		map.put("2", "2");
		map.put("3", "3");
		map.put("4", "4");
		map.put("5", "5");
		map.put("6", "6");
		map.put("7", "7");
		map.put("8", "8");
		map.put("9", "9");
		map.put("A", "10");
		map.put("B", "11");
		map.put("C", "12");
		map.put("D", "13");
		map.put("E", "14");
		map.put("F", "15");
		map.put("G", "16");
		map.put("H", "17");
		map.put("I", "18");
		map.put("G", "29");
		map.put("K", "20");
		map.put("L", "21");
		map.put("M", "22");
		map.put("N", "23");
		map.put("O", "24");
		map.put("P", "25");
		map.put("Q", "26");
		map.put("R", "27");
		map.put("S", "28");
		map.put("T", "29");
		map.put("U", "30");
		map.put("V", "31");
		map.put("W", "32");
		map.put("X", "33");
		map.put("Y", "34");
		map.put("Z", "35");
		
		//反向字典
		for(Map.Entry<String, String> entry : map.entrySet()){
			reverMap.put(entry.getValue(),entry.getKey() );
			
		}
	}
	
	
	/**
	 * 传的值是三位的
	 * @param code
	 * @return
	 */
	public String addCodeSanwei(String code){
		String one= String.valueOf(code.charAt(0));
		String two= String.valueOf(code.charAt(1));
		String three= String.valueOf(code.charAt(2));
		//渠道了01
		System.out.println(one+two+three);
		int a = Integer.parseInt(map.get(three)) +1;
		int b = Integer.parseInt(map.get(two));
		//c是第一位
		int c = Integer.parseInt(map.get(one));
		if(a > 35){
			//进第二位
			b += 1;
			a = 0;
		}
		if( b > 35){
			c +=1;
			b = 0;
		}
		//逆转回来被
		String afterone = reverMap.get(String.valueOf(c));
		String aftertwo = reverMap.get(String.valueOf(b));
		String after3 = reverMap.get(String.valueOf(a));
		
		return afterone+ aftertwo + after3;
	}
	
	
	/**	传的值是两位的
	 * @param code
	 * @return
	 */
	public String addCodeLiangwei(String code){
		String one= String.valueOf(code.charAt(0));
		String two= String.valueOf(code.charAt(1));
		//渠道了01
		int b = Integer.parseInt(map.get(two))+1;
		//c是第一位
		int c = Integer.parseInt(map.get(one));
		if( b > 35){
			c +=1;
			b = 0;
		}
		System.out.println(b+"-"+c);
		//逆转回来被
		String afterone = reverMap.get(String.valueOf(c));
		String aftertwo = reverMap.get(String.valueOf(b));
		return afterone+ aftertwo;
	}
	
}	
