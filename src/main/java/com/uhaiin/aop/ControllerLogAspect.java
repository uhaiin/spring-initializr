package com.uhaiin.aop;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * 切面记录请求入参和返回报文以及接口响应时间
 * 
 * @author Jiancai.zhong
 * @date 2024-10-31 04:29:05
 */
@Component
@Aspect
@Slf4j
public class ControllerLogAspect {

	@Pointcut("execution(public * com.uhaiin.controller.*.*(..))")
	public void pointCut() {
	}

	@Around("pointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		long startTime = System.currentTimeMillis();
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		assert requestAttributes != null;
		HttpServletRequest request = requestAttributes.getRequest();

		log.info("Request ==> {} {}", request.getMethod(), Optional.of(request.getRequestURI()).orElse(null));

		String data = null;
		Object obj = point.proceed();
		try {
			data = JSON.toJSONString(obj);
		} catch (Exception e) {
			log.error("format response error", e);
		} finally {
			log.info("Response <== {}", data);
			long executionTime = System.currentTimeMillis() - startTime;
			log.info("Method: {} executed in {} ms", point.getSignature(), executionTime);
		}

		return obj;
	}

}