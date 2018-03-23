package com.hzyc.website.mappers;

import com.hzyc.website.beans.CompanyPos;

public interface CompanyPosMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CompanyPos record);

    int insertSelective(CompanyPos record);

    CompanyPos selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CompanyPos record);

    int updateByPrimaryKey(CompanyPos record);
}