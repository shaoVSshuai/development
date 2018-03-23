package com.hzyc.website.aop;


import java.lang.annotation.*;

/**
 * �Զ���ystemLogϵͳ��־ע��
 */

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLog {
    String module()  default "";
    String methods()  default "";
}