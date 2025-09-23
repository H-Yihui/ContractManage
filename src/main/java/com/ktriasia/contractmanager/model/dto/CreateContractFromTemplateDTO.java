package com.ktriasia.contractmanager.model.dto;

import lombok.Data;

/**
 * 从模板创建合同的请求DTO
 * <p>用于封装从模板创建合同时的请求参数，实现控制层和业务层的解耦</p>
 *
 * @author Ktriasia
 * @since 2025-09-22
 * @version 1.0.0
 */
@Data
public class CreateContractFromTemplateDTO {

    /** 模板ID */
    private Integer templateId;

    /** 合同名称 */
    private String contractName;

    /** 合同描述（可选） */
    private String description;

    /** 自定义属性（JSON格式，可选） */
    private String customAttributes;
}
