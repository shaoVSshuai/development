package com.hzyc.hzycpos.mapper;

import java.util.List;

import com.hzyc.hzycpos.domain.Dict;

public interface DictMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Dict record);

    int insertSelective(Dict record);

    Dict selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Dict record);

    int updateByPrimaryKey(Dict record);
    
    /**
     * ��ѯ�ֵ��������
     * @return
     */
    public List<Dict> selAllDicType();
}