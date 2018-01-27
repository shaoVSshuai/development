package com.hzyc.hzycpos.system;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ScopeMetadata;
import org.springframework.context.annotation.ScopeMetadataResolver;
import org.springframework.context.annotation.ScopedProxyMode;

/**
 * spring ����strutsĬ���ǵ���ģʽ[��Ҫ���ڴ���struts��action]
 * �̲߳���ȫ
 * �޸�������Ϊ�ǵ���
 * 			[�˴�ʹ��session��Ϊÿ���û�session����ʵ��]
 * 			prototype:Ϊÿ�����󴴽�ʵ��
 * 
 * @author SHAOSHUAI
 *
 */
public class MyScopeResolver  implements ScopeMetadataResolver 	{
	public ScopeMetadata resolveScopeMetadata(BeanDefinition definition) {
        ScopeMetadata result = new ScopeMetadata();
        result.setScopedProxyMode(ScopedProxyMode.NO);
        result.setScopeName("prototype");
        return result;
    }
}
