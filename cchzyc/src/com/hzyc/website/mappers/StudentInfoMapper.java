package com.hzyc.website.mappers;


import java.util.List;

import com.hzyc.website.beans.StudentInfo;
import com.hzyc.website.beans.StudentInfoWithBLOBs;

public interface StudentInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StudentInfoWithBLOBs record);

    /**
     * 学员信息添加（选择性添加）
     * 
     * @param record 学生信息实体
     * @return Integer 对数据库影响行数
     */
    int insertSelective(StudentInfo record);

    StudentInfo selectByPrimaryKey(Integer id);
    
    /**
     * 选择性查询学员[全部查询]
     * 
     * @param student
     * @return
     */
    List<StudentInfo> selective(StudentInfo student);
    
    /**
     * 选择性查询学员[分页查询]
     * 
     * @param student
     * @return
     */
    List<StudentInfo> selectiveByPage(StudentInfo student);

    /**
     * 学生信息修改（选择性修改）
     * @param record 学生信息实体
     * @return Integer 对数据库影响行数
     */
    int updateByPrimaryKeySelective(StudentInfo record);
    //批量导入学员
    public int batchInsert(List<StudentInfo> list);

    int updateByPrimaryKeyWithBLOBs(StudentInfoWithBLOBs record);

    int updateByPrimaryKey(StudentInfo record);

	List<StudentInfo> getDistribution();
}