package com.hzyc.hzycpos.mapper;

import java.util.List;

import com.hzyc.hzycpos.domain.storeInfo;
import com.hzyc.hzycpos.domain.storeInfoKey;

public interface storeInfoMapper {
	List<storeInfo> selAll();
    int deleteByPrimaryKey(storeInfoKey key);

    int insert(storeInfo record);

    int insertSelective(storeInfo record);

    storeInfo selectByPrimaryKey(storeInfoKey key);

    int updateByPrimaryKeySelective(storeInfo record);

    int updateByPrimaryKey(storeInfo record);
}