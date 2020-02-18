package com.pay.web.dto.out.impl;

import com.alibaba.fastjson.JSON;
import com.pay.web.dto.out.CommonOutDto;
import com.pay.web.dto.out.OutDtoInterface;

import lombok.Data;

import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;

public class AccountSignOutDto extends CommonOutDto implements OutDtoInterface {

	@Override
	public String getOutDto(String responseData) {
		Map<String, String> paramsMap = JSON.parseObject(responseData, Map.class);
		paramsMap.put("MsgTp", "epcc.101.001.01");
		Document document = this.getHeadElement(paramsMap);
		Element msgBodyElement = document.getRootElement().addElement("MsgBody");
		Element sysRtnInfElement = msgBodyElement.addElement("SysRtnInf");
		sysRtnInfElement.addElement("SysRtnCd").setText(paramsMap.get("SysRtnCd"));
		sysRtnInfElement.addElement("SysRtnDesc").setText(paramsMap.get("SysRtnDesc"));
		sysRtnInfElement.addElement("SysRtnTm").setText(paramsMap.get("SysRtnTm"));

		// {"TrxId":"1234567890ABCDEF","SysRtnCd":"0000","SgnAcctTp":"00","SgnAcctId":"100061562170010001","BizStsDesc":"交易成功","TrxDtTm":"2020-02-02T22:22:22","SgnAcctNm":"02","SysRtnTm":"2020-02-09T02:54:52","SgnAcctLvl":"一级账户","TrxCtgy":"0202","BizStsCd":"success","SysRtnDesc":"交易成功"}
		Element bizInfElement = msgBodyElement.addElement("BizInf");
		addElementText(bizInfElement.addElement("SgnNo"), paramsMap.get("SgnNo"));
		addElementText(bizInfElement.addElement("BizStsCd"), paramsMap.get("BizStsCd"));
		addElementText(bizInfElement.addElement("BizStsDesc"), paramsMap.get("BizStsDesc"));

		Element sgnInfElement = msgBodyElement.addElement("SgnInf");

		addElementText(sgnInfElement.addElement("SgnAcctIssrId"), paramsMap.get("SgnAcctIssrId"));
		addElementText(sgnInfElement.addElement("SgnAcctTp"), paramsMap.get("SgnAcctTp"));
		addElementText(sgnInfElement.addElement("SgnAcctId"), paramsMap.get("SgnAcctId"));
		addElementText(sgnInfElement.addElement("SgnAcctNm"), paramsMap.get("SgnAcctNm"));
		addElementText(sgnInfElement.addElement("SgnAcctLvl"), paramsMap.get("SgnAcctLvl"));

		Element instgInfElement = msgBodyElement.addElement("InstgInf");

		addElementText(instgInfElement.addElement("InstgId"), paramsMap.get("InstgId"));
		addElementText(instgInfElement.addElement("InstgAcct"), paramsMap.get("InstgAcct"));

		Element oriTrxInfElement = msgBodyElement.addElement("OriTrxInf");

		addElementText(oriTrxInfElement.addElement("TrxCtgy"), paramsMap.get("TrxCtgy"));
		addElementText(oriTrxInfElement.addElement("TrxId"), paramsMap.get("TrxId"));
		addElementText(oriTrxInfElement.addElement("TrxDtTm"), paramsMap.get("TrxDtTm"));

		return document.asXML();
	}

}
