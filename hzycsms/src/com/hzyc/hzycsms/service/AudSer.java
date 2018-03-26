package com.hzyc.hzycsms.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aliyuncs.exceptions.ClientException;
import com.hzyc.hzycsms.bean.Audition;
import com.hzyc.hzycsms.mapper.AuditionMapper;
import com.hzyc.hzycsms.system.Dict;
import com.hzyc.hzycsms.util.SmsUtils;

@Service
public class AudSer {

	@Autowired
	AuditionMapper am;
	
	/**
	 * 插入试听记录
	 * 
	 * @param aud 试听实体
	 * @return
	 * @throws ClientException session中没有值(用户未发送验证码就提交)抛出NullPointer
	 */
	public boolean insertAudition(Audition aud,HttpServletRequest request) throws ClientException,NullPointerException{
		
		System.out.println(aud.getSchool());
		//验证码
		String validCode = aud.getValidCode();
		//session中保存的验证码
		String vcode = request.getSession().getAttribute("validCode").toString();
		System.out.println(vcode +"==="+ validCode);
		if(validCode != null  && validCode.equals(vcode) ){
			//id为UUID
			String id = UUID.randomUUID().toString();
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String nowDate = sdf.format(date);
			//id为UUID
			aud.setId(id);
			//这里的申请时间是系统时间  精确到分即可
			aud.setApplyTime(nowDate);
			//uuid前八位 并转换为大写
			aud.setApplyCode(id.split("-")[0].toUpperCase());
			boolean b = am.insertSelective(aud) > 0 ? true : false;
			if(b){
				//这里生成申请码 申请码是 uuid最后的八位 parm：电话号 申请码 申请课程
				String lessonName = Dict.getDictNameByCode(aud.getApplyLesson());
				System.out.println(lessonName+"=申请课程==");
				SmsUtils.sendApply(aud.getName(),aud.getTel(),aud.getApplyCode(),lessonName);
			}
			return b;
		}else{
			//这是验证码填写失败的表现
			return false;
		}
		
	}
	
	/**
	 * 发送验证码
	 * 
	 * @param request
	 * @throws ClientException
	 */
	public void getMessage(HttpServletRequest request) throws ClientException{
		
		// 获取电话号码
		String phone = request.getParameter("phone");
		//验证码生成 6位随机数
		Random rand= new Random();
		int tmp = rand.nextInt(899999);
		//加10000确保该数字为6位，并且不会超过999999
		tmp= tmp+100000;;
		//电话号 , 随机码
		SmsUtils.sendSms(phone,tmp);
		System.out.println("验证码已经生成"+tmp);
		//将验证码保存session , 以便比对
		request.getSession().setAttribute("validCode", tmp);
		
	}
	
	
	
	public static void main(String[] args) {
		String lessonName = Dict.getDictNameByCode("102");
		System.out.println(lessonName);
	}
}
