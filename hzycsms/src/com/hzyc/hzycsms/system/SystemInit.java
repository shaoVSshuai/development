package com.hzyc.hzycsms.system;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.hzyc.hzycsms.service.InitService;


/**
 * Application Lifecycle Listener implementation class SystemInit
 *
 */
public class SystemInit implements ServletContextListener {
	

	//ע���ʼ��service
	private InitService initService;
	
    public InitService getInitService() {
		return initService;
	}

	public void setInitService(InitService initService) {
		this.initService = initService;
	}

	/**
     * Default constructor. 
     */
    public SystemInit() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce) {
        // TODO Auto-generated method stub
    	System.out.println("====================��������,�����ֵ������...===========================");
    	try {
    		ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
	    	SystemInit sys = (SystemInit)appContext.getBean("systemInit");
	    	//��ȡ��ʼ��serviceִ�л������
	    	boolean isCacheDict = sys.getInitService().cacheDict();
	    	System.out.println(isCacheDict ? "�����ֵ���سɹ�..." : "�����ֵ����ʧ��...");
	    	
	    	System.out.println("====================�γ�ͼ�껺����...===========================");
		    sys.getInitService().courseIcon(sce);
		    System.out.println("====================�γ�ͼ�껺����ϣ�===========================");
	    	
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
