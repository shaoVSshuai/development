package com.hzyc.hzycpos.system;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.hzyc.hzycpos.service.InitService;
import com.hzyc.hzycpos.util.BeanUtil;

import beans.Privilege;

/**
 * ������ʼ��ʱϵͳ����
 *
 */
public class SystemInit implements ServletContextListener {
	

	 
	/**
     * Default constructor. 
     */
    public SystemInit() {
        // TODO Auto-generated constructor stub
    	System.out.println("ϵͳ��ʼ���������ʼ��-----");
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce) {
        // TODO Auto-generated method stub
    	
    	try {
    		System.out.println("====================��������,�����ֵ������...===========================");
    		InitService is = BeanUtil.getBean("initService",InitService.class);
	    	boolean isCacheDict = is.cacheDict();
	    	System.out.println(isCacheDict ? "�����ֵ���سɹ�..." : "�����ֵ����ʧ��...");
	    	
	    	System.out.println("====================��������,��ʾ��Ϣ������...===========================");
	    	boolean b = is.cacheMessage();
	    	System.out.println(b ? "��ʾ��Ϣ���سɹ�..." : "��ʾ��Ϣ����ʧ��...");
    	} catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();

        	System.out.println("====================�����ֵ����ʧ��...===========================");
		}
    	
    	
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
    }
	
}
