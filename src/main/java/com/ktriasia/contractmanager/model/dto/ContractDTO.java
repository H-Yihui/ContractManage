package com.ktriasia.contractmanager.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 合同DTO类。
 * <p>用于封装合同信息的数据传输对象，包含合同ID、名称、创建时间和更新时间等信息。</p>
 * <ul>
 *   <li>contractId：合同唯一标识</li>
 *   <li>contractName：合同名称</li>
 *   <li>createdAt：合同创建时间</li>
 *   <li>updatedAt：合同最后更新时间</li>
 * </ul>
 * @author Ktriasia
 * @since 2025-9-19
 * @version 1.0.0
 */
@Data
public class ContractDTO {
    private Integer contractId;
    private String contractName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static ContractDTO fromEntity(com.ktriasia.contractmanager.model.pojo.Contract contract) {
        ContractDTO dto = new ContractDTO();
        dto.contractId = contract.getContractId();
        dto.contractName = contract.getContractName();
        dto.createdAt = contract.getCreatedAt();
        dto.updatedAt = contract.getUpdatedAt();
        return dto;
    }
}