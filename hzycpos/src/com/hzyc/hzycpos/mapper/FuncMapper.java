package com.hzyc.hzycpos.mapper;

import java.util.List;

import com.hzyc.hzycpos.domain.Func;

public interface FuncMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Func record);

    int insertSelective(Func record);

    Func selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Func record);

    int updateByPrimaryKey(Func record);
    
    List<Func> selAllFunc();
    
    //更改功能表里面的功能为启用
    boolean updateFuncState(String[] funcId);
    //把初始状态更改为0
    boolean updateInitState();
}