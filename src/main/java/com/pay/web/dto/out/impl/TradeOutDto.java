package com.pay.web.dto.out.impl;

import com.alibaba.fastjson.JSON;
import com.pay.web.dto.out.CommonOutDto;
import com.pay.web.dto.out.OutDtoInterface;

import lombok.Data;

import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;

public class TradeOutDto extends CommonOutDto implements OutDtoInterface {

	@Override
	public String getOutDto(String responseData) {
		Map<String, String> paramsMap = JSON.parseObject(responseData, Map.class);
		paramsMap.put("MsgTp", "epcc.101.001.01");
		Document document = this.getHeadElement(paramsMap);
		Element msgBodyElement = document.getRootElement().addElement("MsgBody");
//		PS00098
//		paramsMap.get("fromAccount")
		Element sysRtnInfElement = msgBodyElement.addElement("FROM");
		addElementText(sysRtnInfElement.addElement("acount"), paramsMap.get("fromAccount"));
		addElementText(sysRtnInfElement.addElement("old"), paramsMap.get("fromAccountOldBalance"));
		addElementText(sysRtnInfElement.addElement("new"), paramsMap.get("fromAccountBalance"));
		
		
		Element bizInfElement = msgBodyElement.addElement("TO");
		addElementText(bizInfElement.addElement("acount"), paramsMap.get("toAccount"));
		addElementText(bizInfElement.addElement("old"), paramsMap.get("toAccountOldBalance"));
		addElementText(bizInfElement.addElement("new"), paramsMap.get("toAccountBalance"));
		Element sgnInfElement = msgBodyElement.addElement("trade");
		addElementText(sgnInfElement.addElement("money"), paramsMap.get("money"));
		return document.asXML();
	}

}
