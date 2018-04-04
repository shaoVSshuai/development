package com.hzyc.website.system;

import java.util.ArrayList;
import java.util.List;

import com.hzyc.website.beans.EmploymentNewsWithBLOBs;

public class EpmentForInit {
	//用来缓存课程信息
	private static List<EmploymentNewsWithBLOBs> courseList = new ArrayList<EmploymentNewsWithBLOBs>();

	public static List<EmploymentNewsWithBLOBs> getCourseList() {
		return courseList;
	}

	public static void setCourseList(List<EmploymentNewsWithBLOBs> courseList) {
		EpmentForInit.courseList = courseList;
	}
}
