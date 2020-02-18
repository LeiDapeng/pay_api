package com.pay.web.bo;

import lombok.Data;

/**
 * 
 * @Title: AccountSingBo.java
 * @Description: 账户签约业务逻辑对象
 * @author: 雷大鹏
 * @date: 2020-02-05 01:56:37
 */
@Data
public class AccountSignBo extends CommonBo {
	private String acctIssrId;
	private String sgnAcctTp;
	private String sgnAcctId;
	private String sgnAcctNm;

	private String idTp;
	private String idNo;

	private String instgId;
	private String instgAcct;

	private String trxCtgy;
	private String trxId;
	private String trxDtTm;
	private String authMsg;
}
