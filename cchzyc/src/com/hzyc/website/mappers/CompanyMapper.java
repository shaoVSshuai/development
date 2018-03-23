package com.hzyc.website.mappers;

import java.util.List;

import com.hzyc.website.beans.Company;

public interface CompanyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Company record);

    int insertSelective(Company record);

    Company selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Company record);

    int updateByPrimaryKey(Company record);
    
    //��ѯ��˾ȫ����Ϣ
    List<Company> selAllCompany();
}