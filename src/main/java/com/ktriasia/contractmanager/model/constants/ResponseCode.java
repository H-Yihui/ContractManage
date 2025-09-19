package com.ktriasia.contractmanager.model.constants;

/**
 * 返回给客户端的响应码枚举类
 * @author Ktriasia
 * @version 1.0.0
 * @since 2025-9-19
 */
public enum ResponseCode {
    SUCCESS(200, "Success"),
    BAD_REQUEST(400, "Bad Request");

    private final int code;
    private final String message;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
