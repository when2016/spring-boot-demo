package com.wanghongen.demo.base;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * http/https定义通用的数据返回对象
 * @param <T>
 */
@Data
@ToString
@NoArgsConstructor
public class GlobalResponse<T> {
    protected boolean success = false;
    private T data;
    private Integer errorCode;
    private String errorMsg;

    public GlobalResponse(T data, boolean success) {
        this.data = data;
        this.success = success;
    }

    public static <T> GlobalResponse<T> sucess(T data) {
        return new GlobalResponse<>(data, true);
    }

    public static <T> GlobalResponse<T> fail(String errorMsg, Integer errorCode) {
        GlobalResponse<T> resp = new GlobalResponse<T>();
        resp.setErrorMsg(errorMsg);
        resp.setErrorCode(errorCode);
        resp.setSuccess(false);
        return resp;
    }

}
