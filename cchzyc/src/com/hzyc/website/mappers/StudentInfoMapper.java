package com.hzyc.website.mappers;


import java.util.List;

import com.hzyc.website.beans.StudentInfo;
import com.hzyc.website.beans.StudentInfoWithBLOBs;

public interface StudentInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StudentInfoWithBLOBs record);

    /**
     * ѧԱ��Ϣ��ӣ�ѡ������ӣ�
     * 
     * @param record ѧ����Ϣʵ��
     * @return Integer �����ݿ�Ӱ������
     */
    int insertSelective(StudentInfo record);

    StudentInfo selectByPrimaryKey(Integer id);
    
    /**
     * ѡ���Բ�ѯѧԱ[ȫ����ѯ]
     * 
     * @param student
     * @return
     */
    List<StudentInfo> selective(StudentInfo student);
    
    /**
     * ѡ���Բ�ѯѧԱ[��ҳ��ѯ]
     * 
     * @param student
     * @return
     */
    List<StudentInfo> selectiveByPage(StudentInfo student);

    /**
     * ѧ����Ϣ�޸ģ�ѡ�����޸ģ�
     * @param record ѧ����Ϣʵ��
     * @return Integer �����ݿ�Ӱ������
     */
    int updateByPrimaryKeySelective(StudentInfo record);
    //��������ѧԱ
    public int batchInsert(List<StudentInfo> list);

    int updateByPrimaryKeyWithBLOBs(StudentInfoWithBLOBs record);

    int updateByPrimaryKey(StudentInfo record);

	List<StudentInfo> getDistribution();
}