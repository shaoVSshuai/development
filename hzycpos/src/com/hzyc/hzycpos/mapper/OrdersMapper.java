package com.hzyc.hzycpos.mapper;

import com.hzyc.hzycpos.domain.Orders;
import com.hzyc.hzycpos.domain.OrdersKey;

public interface OrdersMapper {
    int deleteByPrimaryKey(OrdersKey key);

    int insert(Orders record);

    int insertSelective(Orders record);

    Orders selectByPrimaryKey(OrdersKey key);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);
}