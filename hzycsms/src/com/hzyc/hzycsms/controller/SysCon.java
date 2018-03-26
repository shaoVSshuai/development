package com.hzyc.hzycsms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.hzyc.hzycsms.service.InitService;
import com.hzyc.hzycsms.system.SystemInit;
import com.hzyc.hzycsms.util.BeanUtil;

@Controller
@RequestMapping("sysCon")
public class SysCon {

	
	@RequestMapping("/reloadDict.hzyc")
	public void reloadDict(){
		//更新数据字典
		InitService init =  (InitService)BeanUtil.getBean("InitService");
    	//获取初始化service执行缓存操作
    	try {
			boolean isCacheDict = init.cacheDict();
			System.out.println("气不气,我又更新一次");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
