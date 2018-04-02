package com.hzyc.hzycsms.util;




import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

public class SmsUtils {

	
	    //��Ʒ����:��ͨ�Ŷ���API��Ʒ,�����������滻
	    static final String product = "Dysmsapi";
	    //��Ʒ����,�����������滻
	    static final String domain = "dysmsapi.aliyuncs.com";
	   
	    
	    // TODO !!!! �˴���Ҫ�滻�ɿ������Լ���AK(�ڰ����Ʒ��ʿ���̨Ѱ��)-----------------------------
	    static final String accessKeyId = "LTAIMSVNxslRKyFr";
	    static final String accessKeySecret = "FI1vpWs1b75oa7QMINeq3HPNR3CtEX";
	    // TODO !!!! �˴���Ҫ�滻�ɿ������Լ���AK(�ڰ����Ʒ��ʿ���̨Ѱ��)-----------------------------
	    
	    
	    /**
	     * ������֤��
	     * 
	     * @param phoneNumber �绰��
	     * @param random ��֤��
	     * @return
	     * @throws ClientException
	     */
	    public static SendSmsResponse sendSms(String phoneNumber,int random) throws ClientException {
	    	System.out.println("f1==============================");
	        //����������[��ʱʱ��]
	        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
	        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
	        //��ʼ��acsClient,�ݲ�֧��region��
	        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
	        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
	        IAcsClient acsClient = new DefaultAcsClient(profile);
	        //��װ�������-��������������̨-�ĵ���������
	        SendSmsRequest request = new SendSmsRequest();
	        
	        //����:�������ֻ���
	        request.setPhoneNumbers(phoneNumber);
	        System.out.println("-------------------------------"+phoneNumber);
	        //����:����ǩ��-���ڶ��ſ���̨���ҵ�
	        request.setSignName("�����׳�");
	        //����:����ģ��-���ڶ��ſ���̨���ҵ�
	        request.setTemplateCode("SMS_116560103");
	        //��ѡ:ģ���еı����滻JSON��,��ģ������Ϊ"�װ���${name},������֤��Ϊ${code}"ʱ,�˴���ֵΪ
	        request.setTemplateParam("{\"code\":\""+random+"\"}");

	        //ѡ��-���ж�����չ��(�����������û�����Դ��ֶ�)
	        //request.setSmsUpExtendCode("90997");

	        //��ѡ:outIdΪ�ṩ��ҵ����չ�ֶ�,�����ڶ��Ż�ִ��Ϣ�н���ֵ���ظ�������
	        request.setOutId("yourOutId");

	        //hint �˴����ܻ��׳��쳣��ע��catch
	        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

	        return sendSmsResponse;
	    }
	    
	  
	    /**
	     *  ����������[]
	     *  
	     * @param phoneNumber �绰��
	     * @param applyCode ������
	     * @param applyLesson ����γ�
	     * @return
	     * @throws ClientException
	     */
	    public static SendSmsResponse sendApply(String name ,String phoneNumber,String applyCode,String applyLesson) throws ClientException {
	    	System.out.println("f2==============================");
	        //����������[��ʱʱ��]
	        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
	        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
	        //��ʼ��acsClient,�ݲ�֧��region��
	        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
	        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
	        IAcsClient acsClient = new DefaultAcsClient(profile);
	        //��װ�������-��������������̨-�ĵ���������
	        SendSmsRequest request = new SendSmsRequest();
	        
	        //����:�������ֻ���
	        request.setPhoneNumbers(phoneNumber);
	        System.out.println(phoneNumber);
	        //����:����ǩ��-���ڶ��ſ���̨���ҵ�
	        request.setSignName("�����׳�");
	        //����:����ģ��-���ڶ��ſ���̨���ҵ�
	        request.setTemplateCode("SMS_115925747");
	        //��ѡ:ģ���еı����滻JSON��,��ģ������Ϊ"�װ���${name},������֤��Ϊ${code}"ʱ,�˴���ֵΪ
	        request.setTemplateParam("{\"name\":\""+name+"\", \"cname\":\""+applyLesson+"\", \"code\":\""+applyCode+"\"}");

	        //ѡ��-���ж�����չ��(�����������û�����Դ��ֶ�)
	        //request.setSmsUpExtendCode("90997");

	        //��ѡ:outIdΪ�ṩ��ҵ����չ�ֶ�,�����ڶ��Ż�ִ��Ϣ�н���ֵ���ظ�������
	        request.setOutId("yourOutId");

	        //hint �˴����ܻ��׳��쳣��ע��catch
	        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
	        System.out.println(phoneNumber+"=================");
	        return sendSmsResponse;
	    }
	   
}

