package com.ktriasia.contractmanager.service.converter;

import com.ktriasia.contractmanager.model.enums.ElementType;
import com.ktriasia.contractmanager.model.pojo.Clause;
import com.ktriasia.contractmanager.model.pojo.ContractElement;
import com.ktriasia.contractmanager.model.pojo.TemplateElementConfig;
import com.ktriasia.contractmanager.model.mapper.ClauseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 模板到合同转换器
 * <p>负责将模板配置转换为合同元素，实现模板和合同之间的解耦</p>
 *
 * @author Ktriasia
 * @since 2025-09-22
 * @version 1.0.0
 */
@Component
@RequiredArgsConstructor
public class TemplateToContractConverter {

    private final ClauseMapper clauseMapper;

    /**
     * 将模板配置列表转换为合同元素列表
     *
     * @param templateConfigs 模板配置列表
     * @param contractId 目标合同ID
     * @return 转换后的合同元素列表
     */
    public List<ContractElement> convertToContractElements(List<TemplateElementConfig> templateConfigs, Integer contractId) {
        // 按order_index排序模板配置
        templateConfigs.sort((c1, c2) -> c1.getOrderIndex().compareTo(c2.getOrderIndex()));

        // 转换为合同元素
        return templateConfigs.stream()
                .map(config -> convertSingleElement(config, contractId))
                .collect(Collectors.toList());
    }

    /**
     * 转换单个模板配置为合同元素
     *
     * @param config 模板配置
     * @param contractId 合同ID
     * @return 转换后的合同元素
     */
    private ContractElement convertSingleElement(TemplateElementConfig config, Integer contractId) {
        ContractElement element = new ContractElement();
        element.setContractId(contractId);

        // 使用枚举的静态方法进行类型转换
        ElementType type = ElementType.fromString(config.getElementType());
        element.setElementType(type);

        element.setSourceClauseId(config.getSourceClauseId());
        element.setAttributes(config.getDefaultAttributes());
        element.setOrderIndex(config.getOrderIndex());

        // 根据内容来源设置内容
        setElementContent(element, config);

        return element;
    }

    /**
     * 根据配置设置元素内容
     *
     * @param element 合同元素
     * @param config 模板配置
     */
    private void setElementContent(ContractElement element, TemplateElementConfig config) {
        if ("STATIC".equals(config.getContentSource())) {
            element.setContent(config.getStaticContent());
        } else if ("CLAUSE_LIBRARY".equals(config.getContentSource()) && config.getSourceClauseId() != null) {
            // 从条款库获取内容
            Clause clause = clauseMapper.selectById(config.getSourceClauseId());
            if (clause != null) {
                element.setContent(clause.getContent());
            }
        }
    }
}
