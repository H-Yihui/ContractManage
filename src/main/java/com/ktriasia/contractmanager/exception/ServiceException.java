package com.ktriasia.contractmanager.exception;

import com.ktriasia.contractmanager.model.result.ResponseCode;
import lombok.Getter;

/**
 * 顶层的异常类，业务异常都该抛出此异常
 * @author ktriasia
 * @version 1.0.0
 * @since 2025-09-23
 */
@Getter
public class ServiceException extends RuntimeException {

    /**返回状态*/
    ResponseCode responseCode;

    /**
     * 初始化异常类
     * @param httpStates 返回给前端的状态码
     * @param message 错误信息
     */
    public ServiceException(ResponseCode httpStates,String message){
        super(message);
        this.responseCode=httpStates;
    }

}
