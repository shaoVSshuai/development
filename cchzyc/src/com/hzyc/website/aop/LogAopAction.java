package com.hzyc.website.aop;


import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.hzyc.website.aop.SystemLog;
import com.hzyc.website.beans.Log;
import com.hzyc.website.services.InitService;
import com.hzyc.website.utils.BeanUtil;

/**
 * ������
 */
@Aspect
public class LogAopAction {
	
	//ע���ʼ��service
	private InitService initService;
	
	public void setInitService(InitService initservice){
		this.initService = initservice;
		
	}
	public InitService getInitService(){
		return this.initService;
		
	}
	
    //��ʼʱ��
    private long BEGIN_TIME ;

    //����ʱ��
    private long END_TIME;

    //������־
    private Log log = new Log();
    
    //����������controller��
	@Pointcut("execution(* com.hzyc.website.controllers..*.*(..))")
    private void controllerAspect(){}

    /**
     * ����ִ��ǰ����:��¼��ʼִ��ʱ��
     */
    @Before("controllerAspect()")
    public void doBefore(){
        BEGIN_TIME = new Date().getTime();
        System.out.println("������ʼִ��..");
    }

    /**
     * ����ִ�к��������¼����ִ��ʱ��
     */
    @After("controllerAspect()")
    public void after(){
        END_TIME = new Date().getTime();
        System.out.println("��������ִ��..");
    }

    /**
     * ����return�������������¼��־
     */
    @AfterReturning("controllerAspect()")
    public void doAfter(){
       SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
       log.setActiontime(END_TIME-BEGIN_TIME);
       log.setGmtcreate(sdf.format(new Date(BEGIN_TIME)));
       System.out.println(log);
       LogAopAction laa = (LogAopAction)BeanUtil.getBean("logAop");
       if(log.getState()==1){
	    	laa.getInitService().insertLog(log);
        }else if(log.getState()==-1){
           //ִ��ʧ��ҲҪ��¼
	    	laa.getInitService().insertLog(log);
        }
    }

    /**
     * �׳��쳣����
     */
    @AfterThrowing("controllerAspect()")
    public void doAfterThrow(){
        System.out.println("����ִ��ʧ��-----------------------------------");
    }

    /**
     * ����ִ��
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("controllerAspect()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable{
	  //��־ʵ�����
	    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	    //��ȡ��ǰ��½�û���Ϣ
	    if(request.getSession().getAttribute("uid") != null){
	    	log.setLoginaccount(request.getSession().getAttribute("uid").toString());
	    }else{
	    	 log.setLoginaccount("null");;
	    }
	
	    // ���ص�ʵ���࣬���ǵ�ǰ����ִ�е�controller
	    Object target = pjp.getTarget();
	    // ���صķ������ơ���ǰ����ִ�еķ���
	    String methodName = pjp.getSignature().getName();
	    // ���صķ�������
	    Object[] args = pjp.getArgs();
	    // ���صķŲ�������
	    Signature sig = pjp.getSignature();
	    MethodSignature msig = null;
	    if (!(sig instanceof MethodSignature)) {
	        throw new IllegalArgumentException("��ע��ֻ�����ڷ���");
	    }
	    msig = (MethodSignature) sig;
	    Class[] parameterTypes = msig.getMethod().getParameterTypes();
	
	    Object object = null;
	
	    Method method = null;
	    try {
	        method = target.getClass().getMethod(methodName, parameterTypes);
	    } catch (NoSuchMethodException e1) {
	        // TODO Auto-generated catch block
	        e1.printStackTrace();
	    } catch (SecurityException e1) {
	        // TODO Auto-generated catch block
	        e1.printStackTrace();
	    }
	
	    if (null != method) {
	        // �ж��Ƿ�����Զ����ע�⣬˵��һ�������SystemLog�������Լ��Զ����ע��
	        if (method.isAnnotationPresent(SystemLog.class)) {
	            SystemLog systemlog = method.getAnnotation(SystemLog.class);
	            log.setModule(systemlog.module());
	            log.setMethod(systemlog.methods());
	            log.setLoginip(getIp(request));
	            log.setActionurl(request.getRequestURI());
	
	            try {
	                object = pjp.proceed();
	                log.setDescription("ִ�гɹ�");
	                log.setState((short) 1);
	            } catch (Throwable e) {
	            	//��¼�쳣
	            	log.setException(e.toString());
	                // TODO Auto-generated catch block
	                log.setDescription("ִ��ʧ��");
	                log.setState((short)-1);
	            }
	        } else {//û�а���ע��
	            object = pjp.proceed();
	            //log.setDescription("�˲���������ע��");
	            log.setState((short)0);
	        }
	    } else { //����Ҫ����ֱ��ִ��
	        object = pjp.proceed();
	        //log.setDescription("����Ҫ����ֱ��ִ��");
	        log.setState((short)0);
	    }
	    return object;
	}

	/**
	 * ��ȡip��ַ
	 * @param request
	 * @return
	 */
	private String getIp(HttpServletRequest request){
	    if (request.getHeader("x-forwarded-for") == null) {
	        try {
				return InetAddress.getLocalHost().getHostAddress();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    return request.getHeader("x-forwarded-for");
	}
	    
	
	public static void main(String[] args) {
		try {
			String a = null;
			a.toCharArray();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
    
}