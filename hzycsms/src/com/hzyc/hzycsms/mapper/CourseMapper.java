package com.hzyc.hzycsms.mapper;

import java.util.List;

import com.hzyc.hzycsms.bean.Course;


public interface CourseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Course record);

    int insertSelective(Course record);

    Course selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKeyWithBLOBs(Course record);

    int updateByPrimaryKey(Course record);
    
    List<Course> selCourse();
}