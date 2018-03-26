package com.hzyc.website.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzyc.website.beans.Course;
import com.hzyc.website.mappers.CourseMapper;

@Service
public class HomepageSer {
	
	@Autowired
	CourseMapper cm;
	
	/**
	 * @return
	 * ��ѯ�γ�
	 */
	public List<Course> selCourse(){
		return cm.selCourse();
	}
}
