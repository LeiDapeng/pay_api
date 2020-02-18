package com.pay.web.controller.common;

public enum ResponseCodeEnum {
    SUCCESS("0000","交易成功"),
    FAIL("9999","交易异常");
    private String code;
    private String message;

    public void setCode(String code) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    private ResponseCodeEnum(String code,String message){
        this.code=code;
        this.message=message;
    }
}
