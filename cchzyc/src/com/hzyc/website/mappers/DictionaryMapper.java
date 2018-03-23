package com.hzyc.website.mappers;

import java.util.List;

import com.hzyc.website.beans.Address;
import com.hzyc.website.beans.Dictionary;

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
    //�����ֵ���Ŀ
    public int disableDict(String code,String status);
    
    public int updDict(Dictionary dict);
/*<<<<<<< .mine*/
    
    
    /**
     * ���ص�����Ϣ���ڴ�
     * 
     * @author Lee
     * 
     */
    public List<Address> selAllAddress();
    
    
    public List<Address> selAllCounty();
    
/*=======*/
    
    /**
     * ����type��ѯ�ֵ���Ŀ
     * */
    public List<Dictionary> selByType(String value);
/*>>>>>>> .r93*/
}