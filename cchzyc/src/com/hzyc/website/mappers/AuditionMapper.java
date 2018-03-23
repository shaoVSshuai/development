package com.hzyc.website.mappers;

import java.util.List;

import com.hzyc.website.beans.Audition;

public interface AuditionMapper {
    int deleteByPrimaryKey(String id);

    int insert(Audition record);

    int insertSelective(Audition record);

    Audition selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Audition record);

    int updateByPrimaryKey(Audition record);
    
    //不同条件查询所有试听学员
    public List<Audition> selAllAudition(Audition aud);
    
    int  selAllAuditionCount(Audition aud);
    
    int delAMonthAgo();
}