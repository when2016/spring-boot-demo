//package com.wanghongen.demo.handler;
//
//import com.aliyun.openservices.shade.com.google.common.base.Throwables;
//import com.wanghongen.demo.base.GlobalResponse;
//import com.wanghongen.demo.exception.DemoException;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.core.MethodParameter;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.server.ServerHttpRequest;
//import org.springframework.http.server.ServerHttpResponse;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
//
//
///**
// * Controller增强处理器
// */
//@Slf4j
//@ControllerAdvice(annotations = {RestController.class, Controller.class})
//public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {
//    @Override
//    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> aClass) {
//        final String returnTypeName = returnType.getParameterType().getName();
//        //哪些进行拦截处理
//        return !"com.wanghongen.demo.base.GlobalResponse".equals(returnTypeName)
//                && "org.springframework.http.ResponseEntity".equals(returnTypeName);
//    }
//
//    //返回统一数据处理
//    @Override
//    public Object beforeBodyWrite(Object body, MethodParameter returnType,
//                                  MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType,
//                                  ServerHttpRequest request, ServerHttpResponse response) {
//
//        final String returnTypeName = returnType.getParameterType().getName();
//        if ("void".equals(returnTypeName)) {
//            return GlobalResponse.sucess(null);
//        }
//
//        if (!selectedContentType.includes(MediaType.APPLICATION_JSON)) {
//            return body;//返回值不是json类型
//        }
//
//        if ("com.wanghongen.demo.base.GlobalResponse".equals(returnTypeName)) {
//            return body;
//        }
//
//        return GlobalResponse.sucess(body);
//    }
//
//    @ResponseBody
//    @ResponseStatus(HttpStatus.OK)
//    @ExceptionHandler({DemoException.class})
//    public <T> GlobalResponse<T> handlerException(DemoException e) {
//        log.error(Throwables.getStackTraceAsString(e));
//        return GlobalResponse.fail(e.getErrorMsg(), e.getErrorCode());
//    }
//
//    @ResponseBody
//    @ResponseStatus(HttpStatus.OK)
//    @ExceptionHandler({Throwable.class})
//    public <T> GlobalResponse<T> handleThrowable(Throwable e) {
//        log.error(Throwables.getStackTraceAsString(e));
//
//        return GlobalResponse.fail(Throwables.getStackTraceAsString(e), null);
//    }
//
//}
