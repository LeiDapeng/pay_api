package com.pay.web.common.aspect;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.alibaba.fastjson.JSON;
import com.pay.basic.exception.*;
import com.pay.web.controller.common.ResponseCodeEnum;
import com.pay.web.controller.common.ResponseDTO;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @Title: GlobalExceptionHandle.java
 * @Description: controller通用异常处理
 * @author: 雷大鹏
 * @date: 2020-02-03 06:58:12
 */
@RestControllerAdvice
@Slf4j
public class ExceptionAspect {
	/**
	 * 
	 * @Description:  异常处理逻辑
	 * @param e
	 * @return
	 * @author: 雷大鹏
	 * @date: 2020-02-03 07:01:01
	 */
	@ExceptionHandler(Exception.class)
	public String handle(Exception e) {
		String exceptionType = "未知异常";
		String exceptionMsg = "";
		if (e instanceof BusinessException) {
			// 内部逻辑异常
			BusinessException businessException = (BusinessException) e;
			exceptionType = "内部逻辑异常";
			exceptionMsg = businessException.getExceptionTypeEnum().getExceptionType() + "->" + businessException.getErrorMsg();
		} else if (e instanceof CommunicationException) {
			// 通讯异常
			CommunicationException communicationException = (CommunicationException) e;
			exceptionType = "通讯异常";
			exceptionMsg = communicationException.getExceptionTypeEnum().getExceptionType() + "->" + communicationException.getErrorMsg();

		} else if (e instanceof RelationSystemException) {
			// 关联系统异常
			RelationSystemException relationSystemException = (RelationSystemException) e;
			exceptionType = "关联系统异常";
			exceptionMsg = relationSystemException.getExceptionTypeEnum().getExceptionType() + "->" + relationSystemException.getErrorMsg();
		} else {
			// 未知异常
			exceptionType = "未知异常";
			exceptionMsg = e.getMessage();
		}
		log.error("【" + exceptionType + "】,错误信息:【" + exceptionMsg + "】", e);
		return JSON.toJSONString(new ResponseDTO(null, ResponseCodeEnum.FAIL.getCode(), exceptionType + ":" + exceptionMsg));
	}

}
