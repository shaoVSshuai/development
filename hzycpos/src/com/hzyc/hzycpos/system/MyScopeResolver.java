package com.hzyc.hzycpos.system;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ScopeMetadata;
import org.springframework.context.annotation.ScopeMetadataResolver;
import org.springframework.context.annotation.ScopedProxyMode;

/**
 * spring 管理struts默认是单例模式[主要用于创建struts的action]
 * 线程不安全
 * 修改作用域为非单例
 * 			[此处使用session，为每个用户session保持实例]
 * 			prototype:为每次请求创建实例
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
