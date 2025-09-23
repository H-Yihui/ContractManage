package com.ktriasia.contractmanager.service.converter;

import com.ktriasia.contractmanager.model.enums.ElementType;
import com.ktriasia.contractmanager.model.mapper.ClauseMapper;
import com.ktriasia.contractmanager.model.pojo.Clause;
import com.ktriasia.contractmanager.model.pojo.ContractElement;
import com.ktriasia.contractmanager.model.pojo.TemplateElementConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;

/**
 * TemplateToContractConverter 单元测试
 * 
 * @author Ktriasia
 * @since 2025-09-22
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("模板到合同转换器测试")
class TemplateToContractConverterTest {

    @Mock
    private ClauseMapper clauseMapper;

    @InjectMocks
    private TemplateToContractConverter converter;

    private TemplateElementConfig staticConfig;
    private TemplateElementConfig clauseConfig;
    private Clause mockClause;
    private Integer contractId;

    @BeforeEach
    void setUp() {
        contractId = 1;
        
        // 设置静态内容配置
        staticConfig = new TemplateElementConfig();
        staticConfig.setConfigId(1);
        staticConfig.setTemplateId(1);
        staticConfig.setOrderIndex(1);
        staticConfig.setElementType("HEADER_1");
        staticConfig.setContentSource("STATIC");
        staticConfig.setStaticContent("第一章 总则");
        staticConfig.setDefaultAttributes("{\"fontSize\": 16}");

        // 设置条款库配置
        clauseConfig = new TemplateElementConfig();
        clauseConfig.setConfigId(2);
        clauseConfig.setTemplateId(1);
        clauseConfig.setOrderIndex(2);
        clauseConfig.setElementType("CLAUSE");
        clauseConfig.setContentSource("CLAUSE_LIBRARY");
        clauseConfig.setSourceClauseId(100);
        clauseConfig.setDefaultAttributes("{\"required\": true}");

        // 模拟条款对象
        mockClause = new Clause();
        mockClause.setClauseId(100);
        mockClause.setTitle("保密条款");
        mockClause.setContent("双方应对合同内容保密...");
    }

    @Test
    @DisplayName("应该正确转换静态内容配置为合同元素")
    void shouldConvertStaticContentConfigToContractElement() {
        // Given
        List<TemplateElementConfig> configs = Arrays.asList(staticConfig);

        // When
        List<ContractElement> result = converter.convertToContractElements(configs, contractId);

        // Then
        assertThat(result).hasSize(1);
        ContractElement element = result.get(0);
        
        assertThat(element.getContractId()).isEqualTo(contractId);
        assertThat(element.getElementType()).isEqualTo(ElementType.HEADER_1);
        assertThat(element.getContent()).isEqualTo("第一章 总则");
        assertThat(element.getAttributes()).isEqualTo("{\"fontSize\": 16}");
        assertThat(element.getOrderIndex()).isEqualTo(1);
        assertThat(element.getSourceClauseId()).isNull();
        
        // 验证没有调用条款查询
        verify(clauseMapper, never()).selectById(anyInt());
    }

    @Test
    @DisplayName("应该正确转换条款库配置为合同元素")
    void shouldConvertClauseLibraryConfigToContractElement() {
        // Given
        List<TemplateElementConfig> configs = Arrays.asList(clauseConfig);
        when(clauseMapper.selectById(100)).thenReturn(mockClause);

        // When
        List<ContractElement> result = converter.convertToContractElements(configs, contractId);

        // Then
        assertThat(result).hasSize(1);
        ContractElement element = result.get(0);
        
        assertThat(element.getContractId()).isEqualTo(contractId);
        assertThat(element.getElementType()).isEqualTo(ElementType.CLAUSE);
        assertThat(element.getContent()).isEqualTo("双方应对合同内容保密...");
        assertThat(element.getAttributes()).isEqualTo("{\"required\": true}");
        assertThat(element.getOrderIndex()).isEqualTo(2);
        assertThat(element.getSourceClauseId()).isEqualTo(100);
        
        // 验证调用了条款查询
        verify(clauseMapper).selectById(100);
    }

    @Test
    @DisplayName("当条款不存在时应该设置空内容")
    void shouldSetEmptyContentWhenClauseNotFound() {
        // Given
        List<TemplateElementConfig> configs = Arrays.asList(clauseConfig);
        when(clauseMapper.selectById(100)).thenReturn(null);

        // When
        List<ContractElement> result = converter.convertToContractElements(configs, contractId);

        // Then
        assertThat(result).hasSize(1);
        ContractElement element = result.get(0);
        
        assertThat(element.getContent()).isNull();
        assertThat(element.getSourceClauseId()).isEqualTo(100);
        
        verify(clauseMapper).selectById(100);
    }

    @Test
    @DisplayName("应该按orderIndex正确排序配置")
    void shouldSortConfigsByOrderIndex() {
        // Given
        TemplateElementConfig config1 = createConfigWithOrder(3, "PARAGRAPH");
        TemplateElementConfig config2 = createConfigWithOrder(1, "HEADER_1");
        TemplateElementConfig config3 = createConfigWithOrder(2, "HEADER_2");
        
        List<TemplateElementConfig> configs = Arrays.asList(config1, config2, config3);

        // When
        List<ContractElement> result = converter.convertToContractElements(configs, contractId);

        // Then
        assertThat(result).hasSize(3);
        assertThat(result.get(0).getElementType()).isEqualTo(ElementType.HEADER_1);
        assertThat(result.get(0).getOrderIndex()).isEqualTo(1);
        assertThat(result.get(1).getElementType()).isEqualTo(ElementType.HEADER_2);
        assertThat(result.get(1).getOrderIndex()).isEqualTo(2);
        assertThat(result.get(2).getElementType()).isEqualTo(ElementType.PARAGRAPH);
        assertThat(result.get(2).getOrderIndex()).isEqualTo(3);
    }

    @Test
    @DisplayName("应该处理混合内容源配置")
    void shouldHandleMixedContentSourceConfigs() {
        // Given
        List<TemplateElementConfig> configs = Arrays.asList(staticConfig, clauseConfig);
        when(clauseMapper.selectById(100)).thenReturn(mockClause);

        // When
        List<ContractElement> result = converter.convertToContractElements(configs, contractId);

        // Then
        assertThat(result).hasSize(2);
        
        // 第一个元素（静态内容）
        ContractElement staticElement = result.get(0);
        assertThat(staticElement.getContent()).isEqualTo("第一章 总则");
        assertThat(staticElement.getElementType()).isEqualTo(ElementType.HEADER_1);
        
        // 第二个元素（条款库内容）
        ContractElement clauseElement = result.get(1);
        assertThat(clauseElement.getContent()).isEqualTo("双方应对合同内容保密...");
        assertThat(clauseElement.getElementType()).isEqualTo(ElementType.CLAUSE);
        
        verify(clauseMapper).selectById(100);
    }

    @Test
    @DisplayName("应该处理未知的元素类型")
    void shouldHandleUnknownElementType() {
        // Given
        TemplateElementConfig unknownConfig = createConfigWithOrder(1, "UNKNOWN_TYPE");
        List<TemplateElementConfig> configs = Arrays.asList(unknownConfig);

        // When
        List<ContractElement> result = converter.convertToContractElements(configs, contractId);

        // Then
        assertThat(result).hasSize(1);
        ContractElement element = result.get(0);
        assertThat(element.getElementType()).isNull(); // fromString应该返回null
    }

    @Test
    @DisplayName("应该处理空的sourceClauseId")
    void shouldHandleNullSourceClauseId() {
        // Given
        clauseConfig.setSourceClauseId(null);
        List<TemplateElementConfig> configs = Arrays.asList(clauseConfig);

        // When
        List<ContractElement> result = converter.convertToContractElements(configs, contractId);

        // Then
        assertThat(result).hasSize(1);
        ContractElement element = result.get(0);
        assertThat(element.getContent()).isNull();
        assertThat(element.getSourceClauseId()).isNull();
        
        // 不应该调用条款查询
        verify(clauseMapper, never()).selectById(anyInt());
    }

    @Test
    @DisplayName("应该处理空配置列表")
    void shouldHandleEmptyConfigList() {
        // Given
        List<TemplateElementConfig> configs = Arrays.asList();

        // When
        List<ContractElement> result = converter.convertToContractElements(configs, contractId);

        // Then
        assertThat(result).isEmpty();
        verify(clauseMapper, never()).selectById(anyInt());
    }

    private TemplateElementConfig createConfigWithOrder(Integer orderIndex, String elementType) {
        TemplateElementConfig config = new TemplateElementConfig();
        config.setConfigId(orderIndex);
        config.setTemplateId(1);
        config.setOrderIndex(orderIndex);
        config.setElementType(elementType);
        config.setContentSource("STATIC");
        config.setStaticContent("测试内容 " + orderIndex);
        return config;
    }
}
