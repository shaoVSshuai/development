package com.hzyc.hzycpos.mapper;

import java.util.List;

import com.hzyc.hzycpos.domain.User;

public interface UserMapper {
	
	public int validUserName(String username);
	//删除用户根据主键
    int deleteByPrimaryKey(Integer id);
    //选择性查询
    List<User> selective(User user);
    int insert(User record);

    //选择性插入用户
    int insertSelective(User record);
    //插入用户  - 角色表
    int insertUserRole(int id,int rid);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
	
	//根据用户名查询全部
    List<User> selectByUname(User user);
}