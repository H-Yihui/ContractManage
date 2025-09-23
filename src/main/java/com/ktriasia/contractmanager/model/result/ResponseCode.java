package com.ktriasia.contractmanager.model.result;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 返回给客户端的响应码枚举类（增强版）
 * <p>集成了业务错误码和对应的HTTP状态，成为唯一的响应状态信息源。</p>
 * @author Ktriasia
 * @version 1.1.0
 * @since 2025-09-23
 */
@Getter
public enum ResponseCode {
    // --- 成功状态 ---
    SUCCESS(200, "成功", HttpStatus.OK),
    CREATED(201, "创建成功", HttpStatus.CREATED),

    // --- 通用错误 ---
    BAD_REQUEST(400, "无效的参数", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED(401, "未授权", HttpStatus.UNAUTHORIZED),
    FORBIDDEN(403, "禁止访问", HttpStatus.FORBIDDEN),
    NOT_FOUND(404, "资源不存在", HttpStatus.NOT_FOUND),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误", HttpStatus.INTERNAL_SERVER_ERROR),

    // --- 业务错误 ---
    // 合同相关 (1000-1999)
    CONTRACT_NOT_FOUND(1001, "合同不存在", HttpStatus.NOT_FOUND),
    CONTRACT_CREATE_ERROR(1002, "合同创建失败", HttpStatus.INTERNAL_SERVER_ERROR),
    CONTRACT_DELETE_ERROR(1003, "合同删除失败", HttpStatus.INTERNAL_SERVER_ERROR),
    CONTRACT_UPDATE_ERROR(1004, "合同更新失败", HttpStatus.INTERNAL_SERVER_ERROR),
    CONTRACT_TEMPLATE_NOT_FOUND(1005, "合同模板不存在", HttpStatus.NOT_FOUND),
    CONTRACT_TEMPLATE_CONFIG_EMPTY(1006, "模板配置为空", HttpStatus.BAD_REQUEST),
    CONTRACT_OPERATION_ERROR(1099, "合同操作失败", HttpStatus.INTERNAL_SERVER_ERROR),

    // 合同元素相关 (2000-2999)
    ELEMENT_NOT_FOUND(2001, "合同元素不存在", HttpStatus.NOT_FOUND),
    ELEMENT_CREATE_ERROR(2002, "合同元素创建失败", HttpStatus.INTERNAL_SERVER_ERROR),
    ELEMENT_UPDATE_ERROR(2003, "合同元素更新失败", HttpStatus.INTERNAL_SERVER_ERROR),
    ELEMENT_DELETE_ERROR(2004, "合同元素删除失败", HttpStatus.INTERNAL_SERVER_ERROR),
    ELEMENT_OPERATION_ERROR(2099, "合同元素操作失败", HttpStatus.INTERNAL_SERVER_ERROR),

    // 合同模板相关 (3000-3999)
    TEMPLATE_NOT_FOUND(3001, "合同模板不存在", HttpStatus.NOT_FOUND),
    TEMPLATE_CONFIG_EMPTY(3002, "模板配置为空", HttpStatus.BAD_REQUEST),
    TEMPLATE_CREATE_ERROR(3003, "模板创建失败", HttpStatus.INTERNAL_SERVER_ERROR),
    TEMPLATE_UPDATE_ERROR(3004, "模板更新失败", HttpStatus.INTERNAL_SERVER_ERROR),
    TEMPLATE_DELETE_ERROR(3005, "模板删除失败", HttpStatus.INTERNAL_SERVER_ERROR),
    TEMPLATE_CONFIG_SAVE_ERROR(3006, "模板配置保存失败", HttpStatus.INTERNAL_SERVER_ERROR),
    TEMPLATE_OPERATION_ERROR(3099, "模板操作失败", HttpStatus.INTERNAL_SERVER_ERROR),

    // 条款相关 (4000-4999)
    CLAUSE_NOT_FOUND(4001, "条款不存在", HttpStatus.NOT_FOUND),
    CLAUSE_CREATE_ERROR(4002, "条款创建失败", HttpStatus.INTERNAL_SERVER_ERROR),
    CLAUSE_UPDATE_ERROR(4003, "条款更新失败", HttpStatus.INTERNAL_SERVER_ERROR),
    CLAUSE_DELETE_ERROR(4004, "条款删除失败", HttpStatus.INTERNAL_SERVER_ERROR),
    CLAUSE_CATEGORY_INVALID(4005, "条款类别无效", HttpStatus.BAD_REQUEST),
    CLAUSE_OPERATION_ERROR(4099, "条款操作失败", HttpStatus.INTERNAL_SERVER_ERROR);

    private final int code; // 业务码
    private final String message; // 描述信息
    private final HttpStatus httpStatus; // 对应的HTTP状态

    /**
     * 构造函数
     * @param code 业务(异常)码
     * @param message (异常信息)
     * @param httpStatus 对应的HTTP状态码
     */
    ResponseCode(int code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}