package com.hzyc.website.mappers;

import com.hzyc.website.beans.EmploymentNews;
import com.hzyc.website.beans.EmploymentNewsWithBLOBs;

public interface EmploymentNewsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EmploymentNewsWithBLOBs record);

    int insertSelective(EmploymentNewsWithBLOBs record);

    EmploymentNewsWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EmploymentNewsWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(EmploymentNewsWithBLOBs record);

    int updateByPrimaryKey(EmploymentNews record);
}