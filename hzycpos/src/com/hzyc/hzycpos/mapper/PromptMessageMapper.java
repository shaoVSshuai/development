package com.hzyc.hzycpos.mapper;

import java.util.List;

import com.hzyc.hzycpos.domain.PromptMessage;

public interface PromptMessageMapper {
    int deleteByPrimaryKey(PromptMessage key);

    int insert(PromptMessage record);

    int insertSelective(PromptMessage record);

    PromptMessage selectByPrimaryKey(PromptMessage key);

    int updateByPrimaryKeySelective(PromptMessage record);

    int updateByPrimaryKey(PromptMessage record);
    
    //����ϵͳ��ʾ��Ϣ
    List<PromptMessage> selectAll();
}