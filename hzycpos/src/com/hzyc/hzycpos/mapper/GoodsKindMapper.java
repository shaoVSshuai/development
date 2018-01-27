package com.hzyc.hzycpos.mapper;

import java.util.List;

import com.hzyc.hzycpos.domain.Goods;
import com.hzyc.hzycpos.domain.GoodsKind;

public interface GoodsKindMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsKind record);

    int insertSelective(GoodsKind record);

    GoodsKind selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodsKind record);

    int updateByPrimaryKey(GoodsKind record);
    
    //��ѯ����С���µ���Ʒ
    List<GoodsKind>  selSmallKindGoods();
}