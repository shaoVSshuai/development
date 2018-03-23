package com.hzyc.website.mappers;

import java.util.List;

import com.hzyc.website.beans.Address;
import com.hzyc.website.beans.Dictionary;

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
    //禁用字典项目
    public int disableDict(String code,String status);
    
    public int updDict(Dictionary dict);
/*<<<<<<< .mine*/
    
    
    /**
     * 加载地区信息到内存
     * 
     * @author Lee
     * 
     */
    public List<Address> selAllAddress();
    
    
    public List<Address> selAllCounty();
    
/*=======*/
    
    /**
     * 根据type查询字典项目
     * */
    public List<Dictionary> selByType(String value);
/*>>>>>>> .r93*/
}