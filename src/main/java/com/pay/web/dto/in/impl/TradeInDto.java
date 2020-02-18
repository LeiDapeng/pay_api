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
public class TradeInDto extends CommonInDto implements InDtoInterface {

	private Document document;

	@Override
	public String getReqData() throws Exception {
		AccountSignBo bo = new AccountSignBo();
		this.getHeadElement(bo, document);

		Element rootElement = document.getRootElement();

		Element sgnElement = rootElement.element("Test");

		String fromAccount = sgnElement.elementText("fromAccount");
		String toAccount = sgnElement.elementText("toAccount");
		String money = sgnElement.elementText("money");
		String error = sgnElement.elementText("error");

		StringBuffer bufMessage = new StringBuffer();
		bufMessage.append("{");
		bufMessage.append("\"fromAccount\":\"" + fromAccount + "\",");
		bufMessage.append("\"toAccount\":\"" + toAccount + "\",");
		bufMessage.append("\"money\":\"" + money + "\",");
		bufMessage.append("\"error\":\"" + error + "\",");
		bufMessage.append("\"MsgTp\":\"" + bo.getMsgTp() + "\",");
		bufMessage.append("}");
		return bufMessage.toString();
	}

}
