package com.hzyc.hzycpos.system;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.hzyc.hzycpos.service.InitService;
import com.hzyc.hzycpos.util.BeanUtil;

import beans.Privilege;

/**
 * 容器初始化时系统加载
 *
 */
public class SystemInit implements ServletContextListener {
	

	 
	/**
     * Default constructor. 
     */
    public SystemInit() {
        // TODO Auto-generated constructor stub
    	System.out.println("系统初始化加载类初始化-----");
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce) {
        // TODO Auto-generated method stub
    	
    	try {
    		System.out.println("====================容器启动,数据字典加载中...===========================");
    		InitService is = BeanUtil.getBean("initService",InitService.class);
	    	boolean isCacheDict = is.cacheDict();
	    	System.out.println(isCacheDict ? "数据字典加载成功..." : "数据字典加载失败...");
	    	
	    	System.out.println("====================容器启动,提示信息加载中...===========================");
	    	boolean b = is.cacheMessage();
	    	System.out.println(b ? "提示信息加载成功..." : "提示信息加载失败...");
    	} catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();

        	System.out.println("====================数据字典加载失败...===========================");
		}
    	
    	
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
    }
	
}
