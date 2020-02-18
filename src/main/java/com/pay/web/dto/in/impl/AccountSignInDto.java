package com.pay.web.dto.in.impl;

import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.alibaba.fastjson.JSON;
import com.pay.web.bo.AccountSignBo;
import com.pay.web.dto.in.CommonInDto;
import com.pay.web.dto.in.InDtoInterface;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AccountSignInDto extends CommonInDto implements InDtoInterface {

	private Document document;

	@Override
	public String getReqData() throws Exception {
		AccountSignBo bo = new AccountSignBo();
		this.getHeadElement(bo, document);

		Element rootElement = document.getRootElement();

		Element sgnElement = rootElement.element("SgnInf");
		bo.setAcctIssrId(sgnElement.elementText("SgnAcctIssrId"));
		bo.setSgnAcctTp(sgnElement.elementText("SgnAcctTp"));
		bo.setSgnAcctId(sgnElement.elementText("SgnAcctId"));
		bo.setSgnAcctNm(sgnElement.elementText("SgnAcctNm"));
		bo.setIdTp(sgnElement.elementText("IDTp"));
		bo.setIdNo(sgnElement.elementText("IDNo"));

		Element instgInfElement = rootElement.element("InstgInf");
		bo.setInstgId(instgInfElement.elementText("InstgId"));
		bo.setInstgAcct(instgInfElement.elementText("InstgAcct"));

		Element trxInfElement = rootElement.element("TrxInf");

		bo.setTrxCtgy(trxInfElement.elementText("TrxCtgy"));
		bo.setTrxId(trxInfElement.elementText("TrxId"));
		bo.setTrxDtTm(trxInfElement.elementText("TrxDtTm"));
		bo.setAuthMsg(trxInfElement.elementText("AuthMsg"));

		StringBuffer bufMessage = new StringBuffer();
		bufMessage.append("{");
		bufMessage.append("\"MsgTp\":\"" + bo.getMsgTp() + "\",");
		bufMessage.append("\"tradeCode\":\"" + bo.getTradeCode() + "\",");
		bufMessage.append("\"traceId\":\"" + bo.getTraceId() + "\",");
		bufMessage.append("\"spanId\":\"" + bo.getSpanId() + "\",");
		bufMessage.append("\"productCode\":\"" + bo.getProductCode() + "\",");
		bufMessage.append("\"merchantCode\":\"" + bo.getInstgId() + "\",");
		bufMessage.append("\"cardNo\":\"" + bo.getSgnAcctId() + "\",");
		bufMessage.append("\"SgnAcctIssrId\":\"" + bo.getAcctIssrId() + "\",");
		bufMessage.append("\"SgnAcctTp\":\"" + bo.getSgnAcctTp() + "\",");
		bufMessage.append("\"SgnAcctId\":\"" + bo.getSgnAcctId() + "\",");
//		bufMessage.append("\"SgnAcctNm\":\"" + this.getSgnAcctNm() + "\",");
		Map map = new HashMap();
		map.put("SgnAcctNm", bo.getSgnAcctNm());
		String json = JSON.toJSONString(map);
		bufMessage.append(json.substring(1, json.length() - 1) + ",");
		bufMessage.append("\"IDTp\":\"" + bo.getIdTp() + "\",");
		bufMessage.append("\"IDNo\":\"" + bo.getIdNo() + "\",");
//		bufMessage.append("\"MobNo\":\"" + bo.getMobNo() + "\",");
		bufMessage.append("\"TrxCtgy\":\"" + bo.getTrxCtgy() + "\",");
		bufMessage.append("\"TrxId\":\"" + bo.getTrxId() + "\",");
		bufMessage.append("\"TrxDtTm\":\"" + bo.getTrxDtTm() + "\",");
		bufMessage.append("\"AuthMsg\":\"" + bo.getAuthMsg() + "\",");
		bufMessage.append("\"InstgId\":\"" + bo.getInstgId() + "\",");
		bufMessage.append("\"InstgAcct\":\"" + bo.getInstgAcct() + "\"");
		bufMessage.append("}");
		return bufMessage.toString();
	}

}
