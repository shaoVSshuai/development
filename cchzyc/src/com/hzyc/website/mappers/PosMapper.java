package com.hzyc.website.mappers;

import com.hzyc.website.beans.Pos;

public interface PosMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Pos record);

    int insertSelective(Pos record);

    Pos selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Pos record);

    int updateByPrimaryKey(Pos record);
}