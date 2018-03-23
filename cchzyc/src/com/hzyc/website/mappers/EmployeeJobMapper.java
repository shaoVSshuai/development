package com.hzyc.website.mappers;

import java.util.List;

import com.hzyc.website.beans.EmployeeJob;

public interface EmployeeJobMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EmployeeJob record);

    boolean insertSelective(EmployeeJob record);

    EmployeeJob selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EmployeeJob record);

    int updateByPrimaryKey(EmployeeJob record);
    
    List<EmployeeJob> selEmployeeJobByCode(String code);
    
    boolean deleteByEmployeeCode(String employeeCode);
}