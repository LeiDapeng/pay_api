package com.pay.web.controller.common;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 
 * @Title: ResponseDTO.java
 * @Description: json返回类封装
 * @author: 雷大鹏
 * @date: 2020-02-03 08:55:55
 */
@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class ResponseDTO {
	private Object data;
	private String code;
	private String msg;
}
