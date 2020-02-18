package com.pay.web.bo;

import lombok.Data;

/**
 * 
* @Title: CommonBo.java  
* @Description: TODO(描述)
* @author: 雷大鹏  
* @date: 2020-02-16 09:54:09
 */
@Data
public class CommonBo {
	private String traceId;
	private String spanId;
	/** 产品编码*/
	private String productCode;
	/** 交易码*/
	private String tradeCode;
	/** 报文类型代码*/
	private String MsgTp;
	/** 流水号（路由需要）*/
	private String serialNo;
	/** 商户号*/
	private String merchantCode;
	
	private String sndDt;
	private String msgTp;
	private String issrId;
	private String drctn;
	private String signSN;
}
