package com.uhaiin.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 在方法上使用 @NoWrapper 不强制封装为 ApiResponse<Object> 格式
 * 
 * @author Jiancai.zhong
 * @date 2024-10-31 04:21:55
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface NoWrapper {
}