package com.hzyc.hzycpos.mapper;

import com.hzyc.hzycpos.domain.VipRecord;

public interface VipRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VipRecord record);

    int insertSelective(VipRecord record);

    VipRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VipRecord record);

    int updateByPrimaryKey(VipRecord record);
}