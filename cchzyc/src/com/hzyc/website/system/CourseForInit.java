package com.hzyc.website.system;

import java.util.ArrayList;
import java.util.List;

import com.hzyc.website.beans.Course;

public class CourseForInit {
	//用来缓存课程信息
	private static List<Course> courseList = new ArrayList<Course>();
	
	public static List<Course> getList(){
		return courseList;
	}
	public static void setList(List<Course> cList){
		courseList = cList;
	}
	
}
