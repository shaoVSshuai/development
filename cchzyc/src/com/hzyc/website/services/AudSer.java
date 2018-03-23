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
	 * 查询所有试听学员[多条件查询]
	 * 
	 * @param aud
	 * @return
	 */
	public List<Audition> selAllAudition(Audition aud){
		if(aud.getEtime() != null && !aud.getEtime().equals("")){
			aud.setEtime(aud.getEtime() +" 23:59");
		}
		//如果选择是全部 设置属性为空 sql则不拼接
		if(aud.getApplyLesson() != null && aud.getApplyLesson().equals("全部")){
			aud.setApplyLesson(null);
		}
		if(aud.getSchool() != null && aud.getSchool().equals("全部")){
			aud.setSchool(null);
		}
		if(aud.getStuDept() != null && aud.getStuDept().equals("全部")){
			aud.setStuDept(null);
		}
		return am.selAllAudition(aud);
		
	}
	
	public int selAllAuditionCount(Audition aud){
		if(aud.getEtime() != null && !aud.getEtime().equals("")){
			aud.setEtime(aud.getEtime() +" 23:59");
		}
		//如果选择是全部 设置属性为空 sql则不拼接
		if(aud.getApplyLesson() != null && aud.getApplyLesson().equals("全部")){
			aud.setApplyLesson(null);
		}
		if(aud.getSchool() != null && aud.getSchool().equals("全部")){
			aud.setSchool(null);
		}
		if(aud.getStuDept() != null && aud.getStuDept().equals("全部")){
			aud.setStuDept(null);
		}
		return am.selAllAuditionCount(aud);
	}
	/**
	 * 删除试听学员根据id
	 * 
	 * @param id  主键
	 * @return
	 */
	public boolean delOneAud(String id){
		int i = am.deleteByPrimaryKey(id);
		return i > 0 ? true : false; 
		
	}
	
	/**
	 * 删除一个月以前的试听学员数据
	 */
	public void delAMonthAgo(){
		am.delAMonthAgo();
	}
}
