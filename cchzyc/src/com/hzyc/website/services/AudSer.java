package com.hzyc.website.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aliyuncs.exceptions.ClientException;
import com.hzyc.website.beans.Audition;
import com.hzyc.website.mappers.AuditionMapper;
import com.hzyc.website.system.Dict;
import com.hzyc.website.utils.SmsUtils;

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
	
	
	/**
	 * ����������¼
	 * 
	 * @param aud ����ʵ��
	 * @return
	 * @throws ClientException session��û��ֵ(�û�δ������֤����ύ)�׳�NullPointer
	 */
	public boolean insertAudition(Audition aud,HttpServletRequest request) throws ClientException,NullPointerException{
		
		System.out.println(aud.getSchool());
		//��֤��
		String validCode = aud.getValidCode();
		//session�б������֤��
		String vcode = request.getSession().getAttribute("validCode").toString();
		System.out.println(vcode +"==="+ validCode);
		if(validCode != null  && validCode.equals(vcode) ){
			//idΪUUID
			String id = UUID.randomUUID().toString();
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String nowDate = sdf.format(date);
			//idΪUUID
			aud.setId(id);
			//���������ʱ����ϵͳʱ��  ��ȷ���ּ���
			aud.setApplyTime(nowDate);
			//uuidǰ��λ ��ת��Ϊ��д
			aud.setApplyCode(id.split("-")[0].toUpperCase());
			boolean b = am.insertSelective(aud) > 0 ? true : false;
			if(b){
				//�������������� �������� uuid���İ�λ parm���绰�� ������ ����γ�
				String lessonName = Dict.getDictNameByCode(aud.getApplyLesson());
				System.out.println(lessonName+"=����γ�==");
				SmsUtils.sendApply(aud.getName(),aud.getTel(),aud.getApplyCode(),lessonName);
			}
			return b;
		}else{
			//������֤����дʧ�ܵı���
			return false;
		}
		
	}
	
	/**
	 * ������֤��
	 * 
	 * @param request
	 * @throws ClientException
	 */
	public void getMessage(HttpServletRequest request) throws ClientException{
		
		// ��ȡ�绰����
		String phone = request.getParameter("phone");
		//��֤������ 6λ�����
		Random rand= new Random();
		int tmp = rand.nextInt(899999);
		//��10000ȷ��������Ϊ6λ�����Ҳ��ᳬ��999999
		tmp= tmp+100000;;
		//�绰�� , �����
		SmsUtils.sendSms(phone,tmp);
		System.out.println("��֤���Ѿ�����"+tmp);
		//����֤�뱣��session , �Ա�ȶ�
		request.getSession().setAttribute("validCode", tmp);
		
	}
}
