package com.ktriasia.contractmanager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ktriasia.contractmanager.model.dto.Result;
import com.ktriasia.contractmanager.model.pojo.Contract;
import com.ktriasia.contractmanager.model.pojo.ContractElement;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 合同的服务层，提供创建合同、删除合同、获取合同的所有元素以及获取合同的所有条款元素的业务逻辑
 * @author ktriasia
 * @version 1.0.0
 * @since 2025-09-18
 */
@Service
public interface ContractService extends IService<Contract> {
    /**
     * 创建合同
     * @param contract 合同对象
     * @return 包含创建的合同信息的响应实体
     */
    ResponseEntity<Result<Object>> createContract(Contract contract);

    /**
     * 删除合同
     * @param contractId 合同ID
     * @return 删除结果的响应实体
     */
    ResponseEntity<Result<Object>> deleteContract(Integer contractId);

    /**
     * 获取合同的所有元素
     * @param contractId 合同ID
     * @return 包含合同所有元素的响应实体
     */
    ResponseEntity<Result<Object>> getContractElements(Integer contractId);

    /**
     * 获取合同的所有条款元素
     * @param contractId 合同ID
     * @return 包含合同所有条款元素的响应实体
     */
    ResponseEntity<Result<Object>> getContractClauseElements(Integer contractId);
}
