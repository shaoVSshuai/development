package com.hzyc.hzycpos.mapper;

import java.util.List;

import com.hzyc.hzycpos.domain.User;

public interface UserMapper {
	
	public int validUserName(String username);
	//ɾ���û���������
    int deleteByPrimaryKey(Integer id);
    //ѡ���Բ�ѯ
    List<User> selective(User user);
    int insert(User record);

    //ѡ���Բ����û�
    int insertSelective(User record);
    //�����û�  - ��ɫ��
    int insertUserRole(int id,int rid);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
	
	//�����û�����ѯȫ��
    List<User> selectByUname(User user);
}