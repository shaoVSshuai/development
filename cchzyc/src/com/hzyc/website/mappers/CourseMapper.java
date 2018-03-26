package com.hzyc.website.mappers;

import java.util.List;

import com.hzyc.website.beans.Course;

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