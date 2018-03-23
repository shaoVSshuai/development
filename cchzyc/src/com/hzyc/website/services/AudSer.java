package com.hzyc.website.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzyc.website.beans.Audition;
import com.hzyc.website.mappers.AuditionMapper;

@Service
public class AudSer {
	
	@Autowired
	AuditionMapper am;
	
	
	/**
	 * ��ѯ��������ѧԱ[��������ѯ]
	 * 
	 * @param aud
	 * @return
	 */
	public List<Audition> selAllAudition(Audition aud){
		if(aud.getEtime() != null && !aud.getEtime().equals("")){
			aud.setEtime(aud.getEtime() +" 23:59");
		}
		//���ѡ����ȫ�� ��������Ϊ�� sql��ƴ��
		if(aud.getApplyLesson() != null && aud.getApplyLesson().equals("ȫ��")){
			aud.setApplyLesson(null);
		}
		if(aud.getSchool() != null && aud.getSchool().equals("ȫ��")){
			aud.setSchool(null);
		}
		if(aud.getStuDept() != null && aud.getStuDept().equals("ȫ��")){
			aud.setStuDept(null);
		}
		return am.selAllAudition(aud);
		
	}
	
	public int selAllAuditionCount(Audition aud){
		if(aud.getEtime() != null && !aud.getEtime().equals("")){
			aud.setEtime(aud.getEtime() +" 23:59");
		}
		//���ѡ����ȫ�� ��������Ϊ�� sql��ƴ��
		if(aud.getApplyLesson() != null && aud.getApplyLesson().equals("ȫ��")){
			aud.setApplyLesson(null);
		}
		if(aud.getSchool() != null && aud.getSchool().equals("ȫ��")){
			aud.setSchool(null);
		}
		if(aud.getStuDept() != null && aud.getStuDept().equals("ȫ��")){
			aud.setStuDept(null);
		}
		return am.selAllAuditionCount(aud);
	}
	/**
	 * ɾ������ѧԱ����id
	 * 
	 * @param id  ����
	 * @return
	 */
	public boolean delOneAud(String id){
		int i = am.deleteByPrimaryKey(id);
		return i > 0 ? true : false; 
		
	}
	
	/**
	 * ɾ��һ������ǰ������ѧԱ����
	 */
	public void delAMonthAgo(){
		am.delAMonthAgo();
	}
}
