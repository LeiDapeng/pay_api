package com.pay.web.dto.out;

import java.net.URLDecoder;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.pay.basic.utils.DateUtils;
import com.pay.web.bo.AccountSignBo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class CommonOutDto {
	public Document getHeadElement(Map<String, String> paramsMap) {
		Document document = DocumentHelper.createDocument();// 建立document对象，用来操作xml文件
		Element rootElement = document.addElement("root");
		rootElement.setAttributeValue("xmlns", "namespace_string");
		Element headElement = rootElement.addElement("MsgHeader");// 建立公共节点
		headElement.addElement("SndDt").setText(DateUtils.getTradeDate());

		headElement.addElement("MsgTp").setText(paramsMap.get("MsgTp"));
		headElement.addElement("IssrId").setText("G4000311000018");
		headElement.addElement("Drctn").setText("22");
		headElement.addElement("SignSN").setText("4009731852");
		return document;
	}

	public void addElementText(Element element, Object obj) {
		if (obj != null && obj.toString().length() > 0) {
			element.setText(obj.toString());
		}
	}
}
