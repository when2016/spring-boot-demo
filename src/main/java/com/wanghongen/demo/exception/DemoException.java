package com.wanghongen.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 业务异常
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemoException extends RuntimeException {
    private String errorMsg;
    private Integer errorCode;
}
