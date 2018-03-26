package com.hzyc.hzycsms.mapper;

import java.util.List;

import com.hzyc.hzycsms.bean.Dictionary;


public interface DictionaryMapper {
	
    /**
     * 根据主键删除
     * 
     * @param id 主键
     * @return Integer 删除影响行数
     */
    int deleteByPrimaryKey(Integer id);

    int insert(Dictionary record);

    int insertSelective(Dictionary record);

    Dictionary selectByPrimaryKey(Integer id);
    
    /**
     * 查询字典项目
     * 
     * @author 邵帅
     * @param code 字典编码
     * @return Dictionary
     */
    Dictionary selDictByCode(String code);

    int updateByPrimaryKeySelective(Dictionary record);

    int updateByPrimaryKey(Dictionary record);
    
   
    /**
     * 查询字典所有类别
     * @return
     */
    public List<Dictionary> selAllDicType();
    
    /**
     * 根据type查询字典项目
     * */
    public List<Dictionary> selByType(String value);
}