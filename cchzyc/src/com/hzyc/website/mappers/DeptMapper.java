package com.hzyc.website.mappers;

import java.util.List;

import com.hzyc.website.beans.City;
import com.hzyc.website.beans.Dept;
import com.hzyc.website.beans.Job;
import com.hzyc.website.beans.Province;

public interface DeptMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Dept record);

    int insertSelective(Dept record);

    Dept selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Dept record);

    int updateByPrimaryKey(Dept record);
    
    List<Dept> selLevel1();

    List<Dept> selLevel2(String code);
    
    
    List<Dept> seletAllDept(String deptLevel);
    
    List<Dept> selectDeptUntilEmployee(String deptCode);

    List<Province> selProvince();
    
    List<City> selCity(String provinceCode);
    
    String selProvinceCode(String name);
    //��ѯһ���ı���
    String selLevel1Code();
    //��ѯ�����ı���
    String selLevel2Code(String level);

    
    String seleDeptNameByDeptCode(String deptCode);
}