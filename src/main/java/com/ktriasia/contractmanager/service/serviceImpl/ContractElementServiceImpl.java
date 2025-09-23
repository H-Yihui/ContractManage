package com.ktriasia.contractmanager.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ktriasia.contractmanager.exception.ServiceException;
import com.ktriasia.contractmanager.model.dto.ContractElementDTO;
import com.ktriasia.contractmanager.model.result.Result;
import com.ktriasia.contractmanager.model.result.ResponseCode;
import com.ktriasia.contractmanager.model.mapper.ContractElementMapper;
import com.ktriasia.contractmanager.model.pojo.ContractElement;
import com.ktriasia.contractmanager.service.ContractElementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 合同元素的服务层实现类
 * @author ktriasia
 * @version 2.1.0
 * @since 2025-09-23
 */
@Service
@RequiredArgsConstructor
public class ContractElementServiceImpl extends ServiceImpl<ContractElementMapper, ContractElement> implements ContractElementService {

    private final ContractElementMapper contractElementMapper;

    /**
     * 获取合同元素
     * @param elementId 元素ID
     * @return 包含合同元素的响应实体
     */
    @Override
    public ResponseEntity<Result<Object>> getContractElement(Integer elementId) {
        // 查询合同元素
        ContractElement element = contractElementMapper.selectById(elementId);

        // 检查元素是否存在
        if (element == null) {
            throw new ServiceException(ResponseCode.ELEMENT_NOT_FOUND, "合同元素ID为 " + elementId + " 的元素不存在");
        }

        // 转换为DTO并返回元素信息
        ContractElementDTO elementDTO = ContractElementDTO.fromEntity(element);
        return ResponseEntity.ok(Result.success(elementDTO));
    }

    /**
     * 获取合同的所有元素
     * @param contractId 合同ID
     * @return 包含合同所有元素的响应实体
     */
    @Override
    public ResponseEntity<Result<Object>> getContractElements(Integer contractId) {
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
     * 创建合同元素
     * @param contractElement 合同元素对象
     * @return 包含创建的合同元素信息的响应实体
     */
    @Override
    public ResponseEntity<Result<Object>> createContractElement(ContractElement contractElement) {
        // 保存合同元素到数据库
        contractElementMapper.insert(contractElement);

        // 转换为DTO并返回创建的元素信息
        ContractElementDTO elementDTO = ContractElementDTO.fromEntity(contractElement);
        return ResponseEntity.ok(Result.created(elementDTO));
    }

    /**
     * 更新合同元素
     * @param elementId 元素ID
     * @param contractElement 合同元素对象
     * @return 包含更新的合同元素信息的响应实体
     */
    @Override
    public ResponseEntity<Result<Object>> updateContractElement(Integer elementId, ContractElement contractElement) {
        // 检查元素是否存在
        ContractElement existingElement = contractElementMapper.selectById(elementId);
        if (existingElement == null) {
            throw new ServiceException(ResponseCode.ELEMENT_NOT_FOUND, "合同元素ID为 " + elementId + " 的元素不存在");
        }

        // 更新元素信息
        contractElement.setElementId(elementId);
        contractElementMapper.updateById(contractElement);

        // 转换为DTO并返回更新的元素信息
        ContractElementDTO elementDTO = ContractElementDTO.fromEntity(contractElement);
        return ResponseEntity.ok(Result.success("合同元素更新成功", elementDTO));
    }

    /**
     * 删除合同元素
     * @param elementId 元素ID
     * @return 删除结果的响应实体
     */
    @Override
    public ResponseEntity<Result<Object>> deleteContractElement(Integer elementId) {
        // 检查元素是否存在
        ContractElement existingElement = contractElementMapper.selectById(elementId);
        if (existingElement == null) {
            throw new ServiceException(ResponseCode.ELEMENT_NOT_FOUND, "合同元素ID为 " + elementId + " 的元素不存在");
        }

        // 删除元素
        contractElementMapper.deleteById(elementId);

        // 返回成功响应
        return ResponseEntity.ok(Result.success("合同元素删除成功", null));
    }
}