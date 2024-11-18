package com.uhaiin.advice;

import com.uhaiin.annotation.NoWrapper;
import com.uhaiin.web.ApiResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 将请求结果封装为 ApiResponse<Object> 格式
 * 
 * @author Jiancai.zhong
 * @date 2024-10-31 04:24:36
 */
@Slf4j
@ControllerAdvice
public class PackResponseBodyAdvice implements ResponseBodyAdvice<Object> {

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		Class<?> clazz = returnType.getDeclaringClass();
		// 在以下三种情况下不进行包装处理
		return
		// 1.类上有@NoWrapper注解
		!clazz.isAnnotationPresent(NoWrapper.class)
				// 2.方法上有@NoWrapper注解
				&& !returnType.hasMethodAnnotation(NoWrapper.class)
				// 3.返回值已经是R类型
				&& !ApiResponse.class.isAssignableFrom(returnType.getParameterType());
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {
		if (body instanceof String) {
			String str = (String) body;
			// 最终保证返回的数据结构还是一致的
			ObjectMapper objectMapper = new ObjectMapper();
			try {
				return objectMapper.writeValueAsString(ApiResponse.success(str));
			} catch (JsonProcessingException e) {
				log.error("Object 转 String 异常");
			}
		}
		return ApiResponse.success(body);
	}
}