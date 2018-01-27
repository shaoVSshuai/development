package com.hzyc.hzycpos.mapper;

import com.hzyc.hzycpos.domain.User;
import com.hzyc.hzycpos.domain.UserRole;

public interface UserRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);
    
    UserRole selectByUid(User user);
    
}