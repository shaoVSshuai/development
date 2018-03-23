package com.hzyc.website.system;

import java.util.TimerTask;

import com.hzyc.website.services.AudSer;
import com.hzyc.website.utils.BeanUtil;

public class AudDelTimerTask extends TimerTask{
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("开始删除前一个月的学员记录!");   
		//删除
		AudSer as = BeanUtil.getBean("audSer",AudSer.class);
		as.delAMonthAgo();
	}
	
	public static void main(String[] args) {
		BeanUtil.getBean("audtionSer");
	}
	

}
