package com.hzyc.hzycpos.mapper;

import java.util.List;

import com.hzyc.hzycpos.domain.Orderdetail;

public interface OrderdetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Orderdetail record);

    int insertSelective(Orderdetail record);

    Orderdetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Orderdetail record);

    int updateByPrimaryKey(Orderdetail record);
    
    //插入多个订单详情
    int insertOrderdetailList(List<Orderdetail> oList);
}