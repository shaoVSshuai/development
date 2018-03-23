package com.hzyc.website.mappers;

import java.util.List;

import com.hzyc.website.beans.Privilege;

public interface PrivilegeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Privilege record);

    int insertSelective(Privilege record);

    Privilege selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Privilege record);

    int updateByPrimaryKey(Privilege record);
    //查询所有权限
    List<Privilege> selAllPrivilege();
}