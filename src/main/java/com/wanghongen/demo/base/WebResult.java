package com.wanghongen.demo.base;

public class WebResult extends BaseResult {

    public WebResult(Integer code, String message) {
        super(code, message);
    }

    public WebResult(Integer code, String message, Object object) {
        super(code, message, object);
    }

}
