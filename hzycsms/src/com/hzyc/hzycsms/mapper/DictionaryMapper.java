package com.hzyc.hzycsms.mapper;

import java.util.List;

import com.hzyc.hzycsms.bean.Dictionary;


public interface DictionaryMapper {
	
    /**
     * ��������ɾ��
     * 
     * @param id ����
     * @return Integer ɾ��Ӱ������
     */
    int deleteByPrimaryKey(Integer id);

    int insert(Dictionary record);

    int insertSelective(Dictionary record);

    Dictionary selectByPrimaryKey(Integer id);
    
    /**
     * ��ѯ�ֵ���Ŀ
     * 
     * @author ��˧
     * @param code �ֵ����
     * @return Dictionary
     */
    Dictionary selDictByCode(String code);

    int updateByPrimaryKeySelective(Dictionary record);

    int updateByPrimaryKey(Dictionary record);
    
   
    /**
     * ��ѯ�ֵ��������
     * @return
     */
    public List<Dictionary> selAllDicType();
    
    /**
     * ����type��ѯ�ֵ���Ŀ
     * */
    public List<Dictionary> selByType(String value);
}