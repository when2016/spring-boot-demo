package com.wanghongen.demo.handler;

import com.wanghongen.demo.base.BaseResult;
import com.wanghongen.demo.base.WebResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(NullPointerException.class)
    public BaseResult globalException(HttpServletResponse response, NullPointerException ex) {


        log.info("GlobalExceptionHandler...");
        log.info("错误代码：" + response.getStatus());
        BaseResult result = new WebResult(WebResult.RESULT_FAIL, "request error:" + response.getStatus(), "GlobalExceptionHandler:" + ex.getMessage());
        return result;
    }

}