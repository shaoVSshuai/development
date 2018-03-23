package com.hzyc.website.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hzyc.website.beans.Log;

public interface LogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Log record);

    /**
     * 选择插入日志
     * 
     * @param 
     * @return
     */
    int insertSelective(Log record);

    Log selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Log record);

    int updateByPrimaryKey(Log record);
    
    /**
     * 根据时间查询
     * @param log log实体绑定起始日期
     * @return
     */
    List<Log> selectByTime(Log log);
    
    /**
     * 根据时间段删除日志
     * 
     * @author 邵帅
     * @param stime 起始时间
     * @param etime 结束时间
     * @return
     */
    int delLogByTime(@Param("stime")String stime,@Param("etime")String etime);
}