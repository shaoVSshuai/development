package com.hzyc.hzycpos.mapper;

import com.hzyc.hzycpos.domain.Seller;

public interface SellerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Seller record);

    int insertSelective(Seller record);

    Seller selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Seller record);

    int updateByPrimaryKeyWithBLOBs(Seller record);

    int updateByPrimaryKey(Seller record);
    
}