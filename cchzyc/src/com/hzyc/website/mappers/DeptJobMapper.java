package com.hzyc.website.mappers;

import java.util.List;

import com.hzyc.website.beans.DeptJob;

public interface DeptJobMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DeptJob record);

    boolean insertSelective(DeptJob record);

    DeptJob selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DeptJob record);

    int updateByPrimaryKey(DeptJob record);

    
    List<DeptJob> selectJobCodeByDeptCode(String DeptCode);

    
    List<DeptJob> selRightRole();

}