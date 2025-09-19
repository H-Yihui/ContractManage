package com.ktriasia.contractmanager.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ktriasia.contractmanager.model.enums.ElementType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

/**
 * 合同元素实体类 (增强版)
 * <p>用于描述合同中的各类元素，支持从简单文本到复杂结构的多种类型。</p>
 * <ul>
 * <li>elementId：元素唯一标识</li>
 * <li>contractId：所属合同ID</li>
 * <li>elementType：元素类型</li>
 * <li>content：对于简单文本类元素 (如标题、段落)，存储其文本内容。对于复杂元素，可存储其标签或标题。</li>
 * <li>attributes：存储复杂元素的元数据或数据，格式为JSON字符串。例如，存储表格的结构、待填字段的默认值、图片的URL等。</li>
 * <li>sourceClauseId：来源条款ID（如果适用）</li>
 * </ul>
 * @author Ktriasia
 * @since 2025-9-19
 * @version 2.0.0
 */
@Data
public class ContractElement {
    /** 元素唯一标识 */
    @TableId(type= IdType.AUTO)
    private Integer elementId;

    /** 所属合同ID */
    private Integer contractId;

    /** 元素类型，枚举类型，使用字符串存储 */
    @Enumerated(EnumType.STRING)
    private ElementType elementType;

    /** * 元素的主要文本内容。
     * - 对于 HEADER, PARAGRAPH, CLAUSE: 存储标题或段落文字。
     * - 对于 FILLABLE_FIELD: 存储字段的标签 (如 "联系人：")。
     * - 对于 IMAGE, SEAL: 可存储图片的描述或alt文本。
     */
    private String content;

    /** * 元素的附加属性 (存储为JSON字符串)。
     * 用于描述非文本的、结构化的或动态的数据。
     * - TABLE: '{"rows": 3, "cols": 4, "data": [["..."], ["..."]]}'
     * - FILLABLE_FIELD: '{"type": "text", "placeholder": "请输入...", "value": "用户填写的值"}'
     * - CHECKBOX: '{"label": "同意", "checked": false}'
     * - IMAGE/SEAL: '{"url": "https://bucket.example.com/images/seal.png", "width": 150, "height": 150}'
     */
    private String attributes;

    /** 来源条款ID(如果适用) */
    private Integer sourceClauseId;

    /** 关联的条款对象(不持久化到数据库) */
    @TableField(exist = false)
    private Clause clause;

    /** 关联的合同对象(不持久化到数据库) */
    @TableField(exist = false)
    private Contract contract;
}