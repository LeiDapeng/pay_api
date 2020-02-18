package com.pay.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pay.basic.exception.*;
import com.pay.basic.exception.enums.*;
import com.pay.web.controller.common.BaseController;
import com.pay.web.controller.common.ResponseCodeEnum;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @Title: TestController.java
 * @Description: 测试类
 * @author: 雷大鹏
 * @date: 2020-02-03 08:06:07
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController extends BaseController {
//
//	@Autowired
//	DataSourceTransactionManager dataSourceTransactionManager;
//	@Autowired
//	TransactionDefinition transactionDefinition;
//	@Autowired
//	MonitorAlarmMessageLogMapper mapper;
//
//	@RequestMapping("/errorTest")
//	public String test(int type, int pageNo, int pageSize) throws Exception {
//		if (type == 1) {
//			throw new BusinessException(BusinessExceptionTypeEnum.PARAMETER, "手机号校验失败");
//		} else if (type == 2) {
//			throw new BusinessException(BusinessExceptionTypeEnum.LIMIT, "账号限额");
//		} else if (type == 3) {
//			throw new CommunicationException(CommunicationExceptionTypeEnum.POSTGER_SQL, "写入失败");
//		} else if (type == 4) {
//			throw new CommunicationException(CommunicationExceptionTypeEnum.POSTGER_SQL, "读取失败");
//		} else if (type == 5) {
//			throw new CommunicationException(CommunicationExceptionTypeEnum.REDIS, "读取失败");
//		} else if (type == 6) {
//			throw new CommunicationException(CommunicationExceptionTypeEnum.REDIS, "写入失败");
//		} else if (type == 7) {
//			throw new RelationSystemException(RelationSystemExceptionTypeEnum.LJJZ, "交易超时");
//		} else if (type == 8) {
//			throw new RelationSystemException(RelationSystemExceptionTypeEnum.LJJZ, "返回报文非法");
//		} else {
//			PageHelper.startPage(pageNo, pageSize);
//			List list = mapper.selectByExample(new MonitorAlarmMessageLogExample());
//			PageInfo<Map> pageinfo = new PageInfo<>(list);
//			return ResponseJson(ResponseCodeEnum.SUCCESS, pageinfo);
//		}
//
//	}
//
//	@Transactional(rollbackFor = Exception.class)
//	@RequestMapping("/swAutoTest")
//	public String swAutoTest(int type) throws Exception {
//
//		MonitorAlarmMessageLog log = new MonitorAlarmMessageLog();
//		for (int i = 0; i < 2; i++) {
//			log.setRecId(System.currentTimeMillis() + "");
//			log.setAlarmKey(System.currentTimeMillis() + "");
//			mapper.insert(log);
//		}
//
//		if (type == 1) {
//			throw new RelationSystemException(RelationSystemExceptionTypeEnum.LJJZ, "交易超时");
//		}
//		return ResponseJson(ResponseCodeEnum.SUCCESS, null);
//	}
//
//	@RequestMapping("/swTest")
//	public String swTest(int type) throws Exception {
//		TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
//		dataSourceTransactionManager.setDefaultTimeout(2);
//		MonitorAlarmMessageLog log = new MonitorAlarmMessageLog();
//		for (int i = 0; i < 2; i++) {
//			log.setRecId(System.currentTimeMillis() + "");
//			log.setAlarmKey(System.currentTimeMillis() + "");
//			mapper.insert(log);
//		}
//
//		if (type == 1) {
//			dataSourceTransactionManager.rollback(transactionStatus);
//		} else {
//			dataSourceTransactionManager.commit(transactionStatus);
//		}
//
//		return ResponseJson(ResponseCodeEnum.SUCCESS, null);
//	}
}
