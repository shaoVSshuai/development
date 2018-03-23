package com.hzyc.website.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hzyc.website.beans.Log;

public interface LogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Log record);

    /**
     * ѡ�������־
     * 
     * @param 
     * @return
     */
    int insertSelective(Log record);

    Log selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Log record);

    int updateByPrimaryKey(Log record);
    
    /**
     * ����ʱ���ѯ
     * @param log logʵ�����ʼ����
     * @return
     */
    List<Log> selectByTime(Log log);
    
    /**
     * ����ʱ���ɾ����־
     * 
     * @author ��˧
     * @param stime ��ʼʱ��
     * @param etime ����ʱ��
     * @return
     */
    int delLogByTime(@Param("stime")String stime,@Param("etime")String etime);
}