package com.ktriasia.contractmanager.service;

import com.ktriasia.contractmanager.model.pojo.ContractElement;
import com.ktriasia.contractmanager.model.result.Result;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * 合同元素的服务层，提供获取合同元素、获取合同的所有元素、创建合同元素、更新合同元素以及删除合同元素的业务逻辑
 * @author ktriasia
 * @version 1.0.0
 * @since 2025-09-18
 */
public interface ContractElementService {
    /**
     * 获取合同元素
     * @param elementId 元素ID
     * @return 包含合同元素的响应实体
     */
    ResponseEntity<Result<Object>> getContractElement(Integer elementId);

    /**
     * 获取合同的所有元素
     * @param contractId 合同ID
     * @return 包含合同所有元素的响应实体
     */
    ResponseEntity<Result<Object>> getContractElements(Integer contractId);

    /**
     * 创建合同元素
     * @param contractElement 合同元素对象
     * @return 包含创建的合同元素信息的响应实体
     */
    ResponseEntity<Result<Object>> createContractElement(ContractElement contractElement);

    /**
     * 更新合同元素
     * @param elementId 元素ID
     * @param contractElement 合同元素对象
     * @return 包含更新的合同元素信息的响应实体
     */
    ResponseEntity<Result<Object>> updateContractElement(Integer elementId, ContractElement contractElement);

    /**
     * 删除合同元素
     * @param elementId 元素ID
     * @return 删除结果的响应实体
     */
    ResponseEntity<Result<Object>> deleteContractElement(Integer elementId);
}