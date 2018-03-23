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
	

	//注入初始化service
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
    	System.out.println("====================容器启动,数据字典加载中...===========================");
    	try {
    		ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
	    	SystemInit sys = (SystemInit)appContext.getBean("systemInit");
	    	//获取初始化service执行缓存操作
	    	boolean isCacheDict = sys.getInitService().cacheDict();
	    	System.out.println(isCacheDict ? "数据字典加载成功..." : "数据字典加载失败...");

	    	System.out.println("====================地址信息加载中...===========================");
	    	sys.getInitService().getAllAddress();
	    	sys.getInitService().getAllCounty();
		    System.out.println("====================地址信息加载完毕！===========================");
		    System.out.println("====================菜单树加载中...===========================");
	    	List<Job> list = sys.getInitService().selRoleMenu();
	    	//缓存所有菜单
	    	List<Privilege> pList = sys.getInitService().selAllPrivilege();
	    	JobPrivliege.setPri(pList);
	    	//这是角色拥有的菜单，用来比对权限
	    	JobPrivliege.setList(list);
		    System.out.println("====================菜单树加载完毕！===========================");
		    
		    
		    System.out.println("====================公司信息加载中...(用于招聘)===========================");
		   List<Company> companyList = sys.getInitService().selAllCompany();
		   CompanyForInit.setList(companyList);
		   System.out.println("====================公司信息加载完毕！===========================");
		   
		    System.out.println("===================每天1:00执行的定时任务启动========================================");
		    new AudDelTimerManager();    
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
