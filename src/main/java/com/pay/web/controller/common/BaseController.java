package com.pay.web.controller.common;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import com.alibaba.fastjson.JSON;

/**
 * 
 * @Title: BaseController.java
 * @Description: Controller父类用于封装基本操作
 * @author: 雷大鹏
 * @date: 2020-02-03 08:55:01
 */
public class BaseController {
	/**
	 * 
	 * @Description: 返回json格式内容
	 * @param responseCodeEnum
	 * @param data
	 * @return
	 * @author: 雷大鹏
	 * @date: 2020-02-03 08:55:36
	 */
	public String ResponseJson(ResponseCodeEnum responseCodeEnum, Object data) {
		return JSON.toJSONString(new ResponseDTO(data, responseCodeEnum.getCode(), responseCodeEnum.getMessage()));
	}

	public String inputStream2String(InputStream inputStream) {
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new InputStreamReader(inputStream, Charset.defaultCharset()));
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (Exception e) {
				}
			}
		}

		return sb.toString();
	}
}
