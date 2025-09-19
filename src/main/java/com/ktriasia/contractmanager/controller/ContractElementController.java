package com.ktriasia.contractmanager.controller;

import com.ktriasia.contractmanager.model.dto.Result;
import com.ktriasia.contractmanager.model.pojo.ContractElement;
import com.ktriasia.contractmanager.service.ContractElementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 合同元素的控制层，提供创建合同元素、更新合同元素以及删除合同元素的功能
 * @author ktriasia
 * @version 1.0.0
 * @since 2025-09-18
 */
@Controller
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("api/contract-elements")
public class ContractElementController {

    private final ContractElementService contractElementService;

    /**
     * 创建合同元素
     * @param contractElement 合同元素对象
     * @return 包含创建的合同元素信息的响应实体
     */
    @PostMapping
    public ResponseEntity<Result<Object>> createContractElement(@RequestBody ContractElement contractElement) {
        return contractElementService.createContractElement(contractElement);
    }

    /**
     * 更新合同元素
     * @param elementId 元素ID
     * @param contractElement 合同元素对象
     * @return 包含更新的合同元素信息的响应实体
     */
    @PutMapping("/{elementId}")
    public ResponseEntity<Result<Object>> updateContractElement(@PathVariable Integer elementId, @RequestBody ContractElement contractElement) {
        return contractElementService.updateContractElement(elementId, contractElement);
    }

    /**
     * 删除合同元素
     * @param elementId 元素ID
     * @return 删除结果的响应实体
     */
    @DeleteMapping("/{elementId}")
    public ResponseEntity<Result<Object>> deleteContractElement(@PathVariable Integer elementId) {
        return contractElementService.deleteContractElement(elementId);
    }

}