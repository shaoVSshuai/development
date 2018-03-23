package com.hzyc.website.utils;

import com.hzyc.website.beans.StudentInfo;
import com.hzyc.website.system.Dict;

/**
 * ×Öµä´úÂë --- ÎÄ±¾
 * 
 * @author SHAOSHUAI
 *
 */
public class CodeToText {

	
	public static StudentInfo getStuText(StudentInfo stu){
		if(stu.getSchool() != null){
			stu.setSchool(Dict.getDictName("university", stu.getSchool()));
		}
		return stu;
		
	}
}
