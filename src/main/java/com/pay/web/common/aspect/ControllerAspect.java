package com.pay.web.common.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerMapping;

import com.alibaba.fastjson.JSON;
import com.pay.web.controller.TestController;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 
 * @Title: ControllerAspect.java
 * @Description: Controller切面,主要计算交易耗时
 * @author: 雷大鹏
 * @date: 2020-02-03 08:53:39
 */
@Aspect
@Component
@Slf4j
public class ControllerAspect {

	private ThreadLocal<Map> tlocal = new ThreadLocal<Map>();

	@Pointcut("execution(public * com.pay.web.controller..*.*(..))")
	public void webRequestLog() {
	}

	@Before("webRequestLog()")
	public void doBefore(JoinPoint joinPoint) {
		try {
			Thread.currentThread().setName(UUID.randomUUID().toString());
			long beginTime = System.currentTimeMillis();
			// 接收到请求，记录请求内容
			ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
			HttpServletRequest request = attributes.getRequest();

			String beanName = joinPoint.getSignature().getDeclaringTypeName();
			String methodName = joinPoint.getSignature().getName();
			String uri = request.getRequestURI();
			String remoteAddr = getIpAddr(request);
			String sessionId = request.getSession().getId();

//            String user = (String) request.getSession().getAttribute("user");
			String method = request.getMethod();
			String params = "";

			if ("POST".equals(method)) {
				Object[] paramsArray = joinPoint.getArgs();
				params = JSON.toJSONString(request.getParameterMap());
			} else {
				Map<?, ?> paramsMap = (Map<?, ?>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
				params = paramsMap.toString();
				params = request.getQueryString();
			}

			log.info("->request,请求时间:[" + beginTime + "]url=" + uri + "; beanName=" + beanName + "; remoteAddr=" + remoteAddr + "; methodName=" + methodName + "; params=" + params);

			Map optLog = new HashMap();
			optLog.put("beginTime", beginTime);
			tlocal.set(optLog);

		} catch (Exception e) {
			log.error("***请求报文分析失败doBefore()***", e);
		}
	}

	@AfterReturning(returning = "result", pointcut = "webRequestLog()")
	public void doAfterReturning(Object result) {
		Map optLog = tlocal.get();
		try {
			long beginTime = (long) optLog.get("beginTime");
			log.info("<-response,开始时间:[" + beginTime + "],交易耗时：[" + (System.currentTimeMillis() - beginTime) + "]");

		} catch (Exception e) {
			log.error("***响应报文分析失败doAfterReturning()***", e);
		}
		optLog = null;
	}

	@AfterThrowing(pointcut = "webRequestLog()", throwing = "e")
	public void handleThrowing(Exception e) {
		Map optLog = tlocal.get();
		try {
			long beginTime = (long) optLog.get("beginTime");
			log.info("<-response,开始时间:[" + beginTime + "],交易耗时：[" + (System.currentTimeMillis() - beginTime) + "]");

		} catch (Exception ee) {
			log.error("***响应报文分析失败doAfterReturning()***", ee);
		}
		optLog = null;
	}

	/**
	 * 获取登录用户远程主机ip地址
	 *
	 * @param request
	 * @return
	 */
	private String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

}