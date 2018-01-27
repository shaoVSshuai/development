package com.hzyc.hzycpos.mapper;

import com.hzyc.hzycpos.domain.Vip;

public interface VipMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Vip record);

    int insertSelective(Vip record);

    Vip selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Vip record);

    int updateByPrimaryKeyWithBLOBs(Vip record);

    int updateByPrimaryKey(Vip record);
}