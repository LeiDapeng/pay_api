package com.pay.web.dto.in;

import org.dom4j.Document;
import org.dom4j.Element;

import com.pay.web.bo.AccountSignBo;

import lombok.Data;

@Data
public class CommonInDto {

	public void getHeadElement(AccountSignBo bo, Document document) {
		Element headerElement = document.getRootElement().element("MsgHeader");// 建立公共节点
		bo.setSndDt(headerElement.elementText("SndDt"));
		bo.setMsgTp(headerElement.elementText("MsgTp"));
		bo.setIssrId(headerElement.elementText("IssrId"));
		bo.setDrctn(headerElement.elementText("Drctn"));
		bo.setSignSN(headerElement.elementText("SignSN"));
	}

}
