package com.hzyc.website.system;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.hzyc.website.beans.Audition;
import com.hzyc.website.beans.Company;
import com.hzyc.website.beans.Job;
import com.hzyc.website.beans.Privilege;
import com.hzyc.website.services.AudSer;
import com.hzyc.website.services.InitService;
import com.hzyc.website.utils.BeanUtil;

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

	    	System.out.println("====================��ַ��Ϣ������...===========================");
	    	sys.getInitService().getAllAddress();
	    	sys.getInitService().getAllCounty();
		    System.out.println("====================��ַ��Ϣ������ϣ�===========================");
		    System.out.println("====================�˵���������...===========================");
	    	List<Job> list = sys.getInitService().selRoleMenu();
	    	//�������в˵�
	    	List<Privilege> pList = sys.getInitService().selAllPrivilege();
	    	JobPrivliege.setPri(pList);
	    	//���ǽ�ɫӵ�еĲ˵��������ȶ�Ȩ��
	    	JobPrivliege.setList(list);
		    System.out.println("====================�˵���������ϣ�===========================");
		    
		    
		    System.out.println("====================��˾��Ϣ������...(������Ƹ)===========================");
		   List<Company> companyList = sys.getInitService().selAllCompany();
		   CompanyForInit.setList(companyList);
		   System.out.println("====================��˾��Ϣ������ϣ�===========================");
		   
		    System.out.println("===================ÿ��1:00ִ�еĶ�ʱ��������========================================");
		    new AudDelTimerManager();    
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
