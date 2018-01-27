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
    
    //��ѯС�ڵ��ڳ�ֵ���/���ѽ�������
    List<VipKind> selByNeedMoney(double needMoney);
    
    //��ѯ��ֵ���/���ѽ������������
    List<VipKind> selAllASC();
}