package com.pay.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pay.module.TBServicePost.TBServiceRequest;
import com.pay.web.controller.common.BaseController;
import com.pay.web.dto.in.InDtoInterface;
import com.pay.web.dto.in.impl.AccountSignInDto;
import com.pay.web.dto.in.impl.TradeInDto;
import com.pay.web.dto.out.OutDtoInterface;
import com.pay.web.dto.out.impl.AccountSignOutDto;
import com.pay.web.dto.out.impl.TradeOutDto;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @Title: TradeController.java
 * @Description: 交易测试类
 * @author: 雷大鹏
 * @date: 2020-02-05 02:01:57
 */
@RestController
@RequestMapping("/main")
@Slf4j
public class TradeController extends BaseController {
	@Autowired
	TBServiceRequest tBServiceRequest;

	@RequestMapping("/_tradePay")
	public String test(HttpServletRequest request) throws Exception {
		log.info("=================接收到请求==================");
		String fromPayMessage = this.inputStream2String(request.getInputStream());
		log.info("请求信息：[" + fromPayMessage + "]");
		if ("".equals(fromPayMessage)) {
			log.error("报文为空，不处理！！！");
		}
		// 后台报文格式转换
		Document document = DocumentHelper.parseText(fromPayMessage);
		Element headerElement = document.getRootElement().element("MsgHeader");// 建立公共节点
		String msgTp = headerElement.elementText("MsgTp");
		OutDtoInterface outDto = null;
		InDtoInterface inDto = null;
		if ("epcc.101.001.01".equals(msgTp)) {
			log.info("==============签约0202、身份认证0201==================");
			outDto = new AccountSignOutDto();
			inDto = new AccountSignInDto(document);
		} else if ("epcc.201.001.01".equals(msgTp)) {
			log.info("==============事务测试==================");
			outDto = new TradeOutDto();
			inDto = new TradeInDto(document);
		}
		// 发送交易
		String resData = outDto.getOutDto(tBServiceRequest.send(inDto.getReqData()));
		log.info("返回报文：[" + resData + "]");
		return resData;
	}

}
