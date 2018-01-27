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
	
	//��ѯһ���˵�
    public List<Privilege> selLevel1();
    //��ѯ�����˵�
    public List<Privilege> selLevel2(String id);
    
    //��ѯ����Ȩ��
    public List<Privilege> selAll();
    
    //����ѡ���Ȩ��
    boolean setPrivilege(String[] funcId);
    //��δѡ�е�Ȩ����0
    boolean updatePrivilege(String[] funcId);
    //ͨ���û��Ľ�ɫid��Ȩ��
    List<Privilege> selectByRid(int rId);
    
    //����
    public int updateSort(List<Privilege> list);
}