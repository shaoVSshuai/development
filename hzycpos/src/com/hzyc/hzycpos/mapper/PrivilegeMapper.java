package com.hzyc.hzycpos.mapper;

import java.util.List;

import com.hzyc.hzycpos.domain.Privilege;

public interface PrivilegeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Privilege record);

    int insertSelective(Privilege record);

    Privilege selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Privilege record);

    int updateByPrimaryKey(Privilege record);
	
	//查询一级菜单
    public List<Privilege> selLevel1();
    //查询二级菜单
    public List<Privilege> selLevel2(String id);
    
    //查询所有权限
    public List<Privilege> selAll();
    
    //设置选择的权限
    boolean setPrivilege(String[] funcId);
    //把未选中的权限置0
    boolean updatePrivilege(String[] funcId);
    //通过用户的角色id查权限
    List<Privilege> selectByRid(int rId);
    
    //排序
    public int updateSort(List<Privilege> list);
}