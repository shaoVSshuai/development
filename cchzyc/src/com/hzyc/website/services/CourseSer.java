package com.hzyc.website.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzyc.website.beans.Course;
import com.hzyc.website.mappers.CourseMapper;

@Service
public class CourseSer {
	
	@Autowired
	private CourseMapper cm;
	
	/**
	 * ��ѯ��������
	 * @author ZHENGBIN
	 */
	public List<Course> display(){
		List<Course> cList = cm.selCourse();
		return cList;
	}

}
