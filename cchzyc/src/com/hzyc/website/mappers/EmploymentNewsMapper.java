package com.hzyc.website.mappers;

import java.util.List;

import com.hzyc.website.beans.EmploymentNews;
import com.hzyc.website.beans.EmploymentNewsWithBLOBs;
import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;

public interface EmploymentNewsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EmploymentNewsWithBLOBs record);

    int insertSelective(EmploymentNewsWithBLOBs record);

    EmploymentNewsWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EmploymentNewsWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(EmploymentNewsWithBLOBs record);

    int updateByPrimaryKey(EmploymentNews record);
    
    //多条件查询
    List<EmploymentNewsWithBLOBs> selAllEmp(EmploymentNewsWithBLOBs enw);
    
    int selAllEmpCount(EmploymentNewsWithBLOBs enw);
    
    List<EmploymentNewsWithBLOBs> selAll();
    
    public  List<EmploymentNewsWithBLOBs> fenye(int start , int pageSize);
}