package com.ktriasia.contractmanager.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ktriasia.contractmanager.model.dto.ContractDTO;
import com.ktriasia.contractmanager.model.dto.ContractElementDTO;
import com.ktriasia.contractmanager.model.dto.Result;
import com.ktriasia.contractmanager.model.enums.ElementType;
import com.ktriasia.contractmanager.model.mapper.ContractMapper;
import com.ktriasia.contractmanager.model.mapper.ContractElementMapper;
import com.ktriasia.contractmanager.model.pojo.Contract;
import com.ktriasia.contractmanager.model.pojo.ContractElement;
import com.ktriasia.contractmanager.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 合同的服务层
 * @author ktriasia
 * @version 1.0.0
 * @since 2025-09-18
 */
@Service
@RequiredArgsConstructor
public class ContractServiceImpl extends ServiceImpl<ContractMapper, Contract> implements ContractService {

    private final ContractMapper contractMapper;
    private final ContractElementMapper contractElementMapper;

    /**
     * 创建合同
     * @param contract 合同对象
     * @return 包含创建的合同信息的响应实体
     */
    @Override
    public ResponseEntity<Result<Object>> createContract(Contract contract) {
        // 设置创建时间和更新时间
        LocalDateTime now = LocalDateTime.now();
        contract.setCreatedAt(now);
        contract.setUpdatedAt(now);

        // 保存合同到数据库
        contractMapper.insert(contract);

        // 转换为DTO并返回创建的合同信息
        ContractDTO contractDTO = ContractDTO.fromEntity(contract);
        return ResponseEntity.ok(Result.created(contractDTO));
    }

    /**
     * 删除合同
     * @param contractId 合同ID
     * @return 删除结果的响应实体
     */
    @Override
    public ResponseEntity<Result<Object>> deleteContract(Integer contractId) {
        // 检查合同是否存在
        Contract existingContract = contractMapper.selectById(contractId);
        if (existingContract == null) {
            return ResponseEntity.ok(Result.notFound("合同不存在"));
        }

        // 删除合同
        contractMapper.deleteById(contractId);

        // 返回成功响应
        return ResponseEntity.ok(Result.success("合同删除成功", null));
    }

    /**
     * 获取合同的所有元素
     * @param contractId 合同ID
     * @return 包含合同所有元素的响应实体
     */
    @Override
    public ResponseEntity<Result<Object>> getContractElements(Integer contractId) {
        // 检查合同是否存在
        Contract existingContract = contractMapper.selectById(contractId);
        if (existingContract == null) {
            return ResponseEntity.ok(Result.notFound("合同不存在"));
        }

        // 查询合同的所有元素
        QueryWrapper<ContractElement> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("contract_id", contractId);
        List<ContractElement> elements = contractElementMapper.selectList(queryWrapper);

        // 转换为DTO
        List<ContractElementDTO> elementDTOs = elements.stream()
                .map(ContractElementDTO::fromEntity)
                .collect(Collectors.toList());

        // 返回元素列表
        return ResponseEntity.ok(Result.success(elementDTOs));
    }

    /**
     * 获取合同的所有条款元素
     * @param contractId 合同ID
     * @return 包含合同所有条款元素的响应实体
     */
    @Override
    public ResponseEntity<Result<Object>> getContractClauseElements(Integer contractId) {
        // 检查合同是否存在
        Contract existingContract = contractMapper.selectById(contractId);
        if (existingContract == null) {
            return ResponseEntity.ok(Result.notFound("合同不存在"));
        }

        // 查询合同的所有条款元素
        QueryWrapper<ContractElement> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("contract_id", contractId);
        queryWrapper.eq("element_type", ElementType.CLAUSE);
        List<ContractElement> clauseElements = contractElementMapper.selectList(queryWrapper);

        // 转换为DTO
        List<ContractElementDTO> clauseElementDTOs = clauseElements.stream()
                .map(ContractElementDTO::fromEntity)
                .collect(Collectors.toList());

        // 返回条款元素列表
        return ResponseEntity.ok(Result.success(clauseElementDTOs));
    }
}
