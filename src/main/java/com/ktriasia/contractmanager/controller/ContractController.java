package com.ktriasia.contractmanager.controller;

import com.ktriasia.contractmanager.model.dto.Result;
import com.ktriasia.contractmanager.model.pojo.Contract;
import com.ktriasia.contractmanager.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 合同的控制层，提供创建合同、删除合同、获取合同的所有元素以及获取合同的所有条款元素的功能
 * @author ktriasia
 * @version 1.0.1
 * @since 2025-09-18
 */
@Controller
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("api/contracts")
public class ContractController {

    private final ContractService contractService;

    /**
     * 创建合同
     * @param contract 合同对象
     * @return 包含创建的合同信息的响应实体
     */
    @PostMapping
    public ResponseEntity<Result<Object>> createContract(@RequestBody Contract contract) {
        return contractService.createContract(contract);
    }

    /**
     * 删除合同
     * @param contractId 合同ID
     * @return 删除结果的响应实体
     */
    @DeleteMapping("/{contractId}")
    public ResponseEntity<Result<Object>> deleteContract(@PathVariable Integer contractId) {
        return contractService.deleteContract(contractId);
    }

    /**
     * 获取合同的所有元素
     * @param contractId 合同ID
     * @return 包含合同所有元素的响应实体
     */
    @GetMapping("/{contractId}/elements")
    public ResponseEntity<Result<Object>> getContractElements(@PathVariable Integer contractId) {
        return contractService.getContractElements(contractId);
    }

    /**
     * 获取合同的所有条款元素
     * @param contractId 合同ID
     * @return 包含合同所有条款元素的响应实体
     */
    @GetMapping("/{contractId}/clause-elements")
    public ResponseEntity<Result<Object>> getContractClauseElements(@PathVariable Integer contractId) {
        return contractService.getContractClauseElements(contractId);
    }

    /**
     * 从模板创建合同
     * @param templateId 模板ID
     * @param contractDetails 合同详细信息
     * @return 包含新创建的合同及其所有元素的响应实体
     */
    @PostMapping("/from-template/{templateId}")
    public ResponseEntity<Result<Object>> createContractFromTemplate(
            @PathVariable Integer templateId,
            @RequestBody Contract contractDetails
    ) {
        return contractService.createContractFromTemplate(templateId, contractDetails);
    }

}