package com.ktriasia.contractmanager.model.result;

import lombok.Data;

/**
 * 统一响应结果封装类。
 * <p>用于封装API接口的统一响应格式，包括状态码、消息和数据。</p>
 * <ul>
 *   <li>code：响应状态码</li>
 *   <li>message：响应消息</li>
 *   <li>data：响应数据</li>
 * </ul>
 * @param <T> 数据类型
 * @author Ktriasia
 * @since 2025-9-19
 * @version 1.0.0
 */
@Data
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    public Result() {}

    /**
     * 构造函数
     * @param code Http状态码
     * @param message 描述信息
     * @param data 返回数据
     */
    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(200, "成功", data);
    }

    public static <T> Result<T> success(String message, T data) {
        return new Result<>(200, message, data);
    }

    public static <T> Result<T> created(T data) {
        return new Result<>(201, "创建成功", data);
    }

    public static <T> Result<T> error(Integer code, String message) {
        return new Result<>(code, message, null);
    }

    public static <T> Result<T> notFound(String message) {
        return new Result<>(404, message, null);
    }
}
