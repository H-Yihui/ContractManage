package com.ktriasia.contractmanager.model.enums;

/**
 * 合同元素类型枚举
 * <p>定义了合同中不同类型的元素，便于分类和管理。</p>
 * <ul>
 *     <li>{@link #CLAUSE} 条款</li>
 *     <li>{@link #HEADER} 标题</li>
 *     <li>{@link #PARAGRAPH} 文本段落</li>
 * </ul>
 * @author Ktriasia
 * @since 2025-9-16
 * @version 1.0.0
 */
public enum ElementType {
    /** 条款 */
    CLAUSE,
    /** 标题 */
    HEADER,
    /** 文本段落 */
    PARAGRAPH
}
