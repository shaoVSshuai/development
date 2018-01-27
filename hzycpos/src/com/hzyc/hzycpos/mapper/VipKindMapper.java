package com.hzyc.hzycpos.mapper;

import java.math.BigDecimal;
import java.util.List;

import com.hzyc.hzycpos.domain.VipKind;

public interface VipKindMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VipKind record);

    int insertSelective(VipKind record);

    VipKind selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VipKind record);

    int updateByPrimaryKey(VipKind record);
    
    //查询小于等于充值金额/消费金额的数据
    List<VipKind> selByNeedMoney(double needMoney);
    
    //查询充值金额/消费金额降序的所有数据
    List<VipKind> selAllASC();
}