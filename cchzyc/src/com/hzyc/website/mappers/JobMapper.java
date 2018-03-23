package com.hzyc.website.mappers;


import java.util.List;

import com.hzyc.website.beans.Job;

public interface JobMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Job record);

    boolean insertSelective(Job record);

    Job selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Job record);

    int updateByPrimaryKey(Job record);
    
    List<Job> selRole(Job job);
    
    //���ݶ������Ų���ְλ
    List<Job> selJobByDept2(String code);
    //��ѯ���н�ɫ��ӵ�еĲ˵�
    List<Job> selAllRoleMenu();
    //��ѯ����code
    String selMaxCode();

    
    List<Job> selectJobEmplee(String jobCode);
    

    String selJobCodeByJobName(String jobName);
    String selJobNameByJobCode(String jobCode);
    
    List<Job> selDeptByLevel2(String code2);

}