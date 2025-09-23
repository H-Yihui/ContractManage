package com.ktriasia.contractmanager.exception;

import com.ktriasia.contractmanager.model.result.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理类，捕获并处理应用程序中的异常，返回统一的错误响应
 * @author ktriasia
 * @version 1.0.0
 * @since 2025-09-23
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 处理所有未被捕获的异常，返回 500 服务器内部错误
     * @param e 抛出的非业务异常
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Result<Object>> handleGlobalException(Exception e) {
        Result<Object> result=Result.error(500, "[服务器内部错误]"+e.getMessage());
        return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 处理业务异常，返回对应的HTTP状态码和错误信息
     * @param e 抛出的业务异常
     * @return
     */
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<Result<Object>> handleServiceException(ServiceException e) {
        Result<Object> result = Result.error(e.getResponseCode().getHttpStatus().value(),
                "["+e.getResponseCode().getCode()+"]"+e.getResponseCode().getMessage() +":"+ e.getMessage());
        return ResponseEntity.status(result.getCode()).body(result);
    }
}
