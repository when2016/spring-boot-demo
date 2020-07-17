package com.wanghongen.demo.controller;

import com.wanghongen.demo.base.BaseResult;
import com.wanghongen.demo.base.WebResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//https://www.cnblogs.com/lgjlife/p/10988439.html
@Slf4j
@RestController
public class HttpErrorController implements ErrorController {

    private final static String ERROR_PATH = "/error";

    @ResponseBody
    @RequestMapping(path = ERROR_PATH)
    public BaseResult error(HttpServletRequest request, HttpServletResponse response) {
        log.info("访问/error" + "  错误代码：" + response.getStatus());
        BaseResult result = new WebResult(WebResult.RESULT_FAIL, "HttpErrorController error:" + response.getStatus());
        return result;
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
