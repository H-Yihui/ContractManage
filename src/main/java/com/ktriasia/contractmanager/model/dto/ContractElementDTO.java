package com.ktriasia.contractmanager.model.dto;

import lombok.Data;

/**
 * 合同元素DTO类。
 * <p>用于封装合同元素信息的数据传输对象，包含元素ID、合同ID、元素类型、内容、属性和来源条款ID等信息。</p>
 * <ul>
 *   <li>elementId：元素唯一标识</li>
 *   <li>contractId：所属合同ID</li>
 *   <li>elementType：元素类型</li>
 *   <li>content：元素内容</li>
 *   <li>attributes：元素属性（JSON格式）</li>
 *   <li>sourceClauseId：来源条款ID（如果适用）</li>
 * </ul>
 * @author Ktriasia
 * @since 2025-9-19
 * @version 1.1.0
 */
@Data
public class ContractElementDTO {
    private Integer elementId;
    private Integer contractId;
    private String elementType;
    private String content;
    private String attributes;
    private Integer sourceClauseId;
    private Integer orderIndex;

    public static ContractElementDTO fromEntity(com.ktriasia.contractmanager.model.pojo.ContractElement element) {
        ContractElementDTO dto = new ContractElementDTO();
        dto.elementId = element.getElementId();
        dto.contractId = element.getContractId();
        if (element.getElementType() != null) {
            dto.elementType = element.getElementType().name();
        }
        dto.content = element.getContent();
        dto.attributes = element.getAttributes();
        dto.sourceClauseId = element.getSourceClauseId();
        dto.orderIndex = element.getOrderIndex();
        return dto;
    }
}