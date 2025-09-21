package com.ktriasia.contractmanager.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ktriasia.contractmanager.model.dto.ContractDTO;
import com.ktriasia.contractmanager.model.dto.ContractElementDTO;
import com.ktriasia.contractmanager.model.dto.Result;
import com.ktriasia.contractmanager.model.enums.ElementType;
import com.ktriasia.contractmanager.model.mapper.*;
import com.ktriasia.contractmanager.model.pojo.*;
import com.ktriasia.contractmanager.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final TemplateElementConfigMapper templateElementConfigMapper;
    private final ClauseMapper clauseMapper;

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

        // 删除与该合同关联的所有合同元素
        QueryWrapper<ContractElement> elementQueryWrapper = new QueryWrapper<>();
        elementQueryWrapper.eq("contract_id", contractId);
        contractElementMapper.delete(elementQueryWrapper);

        // 删除合同
        contractMapper.deleteById(contractId);

        // 返回成功响应
        return ResponseEntity.ok(Result.success("合同及关联元素删除成功", null));
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

    /**
     * 从模板创建合同
     * @param templateId 模板ID
     * @param contractDetails 合同详细信息
     * @return 包含新创建的合同及其所有元素的响应实体
     */
    @Override
    @Transactional
    public ResponseEntity<Result<Object>> createContractFromTemplate(Integer templateId, Contract contractDetails) {
        // 检查模板是否存在
        QueryWrapper<TemplateElementConfig> templateQueryWrapper = new QueryWrapper<>();
        templateQueryWrapper.eq("template_id", templateId);
        List<TemplateElementConfig> templateConfigs = templateElementConfigMapper.selectList(templateQueryWrapper);

        if (templateConfigs.isEmpty()) {
            return ResponseEntity.ok(Result.notFound("模板不存在或模板配置为空"));
        }

        // 创建新合同
        LocalDateTime now = LocalDateTime.now();
        contractDetails.setCreatedAt(now);
        contractDetails.setUpdatedAt(now);
        contractMapper.insert(contractDetails);
        Integer newContractId = contractDetails.getContractId();

        // 按order_index排序模板配置
        templateConfigs.sort((c1, c2) -> c1.getOrderIndex().compareTo(c2.getOrderIndex()));

        // 创建合同元素列表
        List<ContractElement> contractElements = templateConfigs.stream().map(config -> {
            ContractElement element = new ContractElement();
            element.setContractId(newContractId);
            // 使用枚举内的静态工厂方法做字符串到枚举的解耦映射
            ElementType type = ElementType.fromString(config.getElementType());
            element.setElementType(type);
            element.setSourceClauseId(config.getSourceClauseId());
            element.setAttributes(config.getDefaultAttributes());

            // 根据内容来源设置内容
            if ("STATIC".equals(config.getContentSource())) {
                element.setContent(config.getStaticContent());
            } else if ("CLAUSE_LIBRARY".equals(config.getContentSource()) && config.getSourceClauseId() != null) {
                // 从条款库获取内容
                Clause clause = clauseMapper.selectById(config.getSourceClauseId());
                if (clause != null) {
                    element.setContent(clause.getContent());
                }
            }

            return element;
        }).collect(Collectors.toList());

        // 批量保存合同元素（顺序插入以保序）
        if (!contractElements.isEmpty()) {
            for (ContractElement element : contractElements) {
                contractElementMapper.insert(element);
            }
        }

        // 获取完整的合同信息（包含所有元素）
        ResponseEntity<Result<Object>> result = getContractElements(newContractId);

        // 返回新创建的合同及其所有元素
        return ResponseEntity.ok(Result.success("从模板创建合同成功", result.getBody().getData()));
    }
}
