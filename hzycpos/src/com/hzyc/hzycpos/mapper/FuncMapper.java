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
    
    //���Ĺ��ܱ�����Ĺ���Ϊ����
    boolean updateFuncState(String[] funcId);
    //�ѳ�ʼ״̬����Ϊ0
    boolean updateInitState();
}