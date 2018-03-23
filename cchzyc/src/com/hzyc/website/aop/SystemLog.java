package com.hzyc.website.aop;


import java.lang.annotation.*;

/**
 * 自定义ystemLog系统日志注解
 */

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLog {
    String module()  default "";
    String methods()  default "";
}