package com.ktriasia.contractmanager.model.enums;

/**
 * 合同元素类型枚举（增强版）
 * <p>定义了合同中常见的各类元素，旨在覆盖从简单文本到复杂表单的多种应用场景。</p>
 * <ul>
 * <li><b>核心文本元素:</b></li>
 * <li>{@link #CLAUSE} - 条款：构成合同主体的法律条文。</li>
 * <li>{@link #HEADER_1} - 一级标题</li>
 * <li>{@link #HEADER_2} - 二级标题</li>
 * <li>{@link #HEADER_3} - 三级标题</li>
 * <li>{@link #PARAGRAPH} - 文本段落</li>
 * * <li><b>结构化元素:</b></li>
 * <li>{@link #PARTY_INFO} - 当事人信息块：用于包裹甲方或乙方整体信息的容器。</li>
 * <li>{@link #TABLE} - 表格</li>
 * <li>{@link #ORDERED_LIST} - 有序列表</li>
 * <li>{@link #UNORDERED_LIST} - 无序列表</li>
 *
 * <li><b>表单与交互元素:</b></li>
 * <li>{@link #FILLABLE_FIELD} - 待填写字段：例如下划线、输入框。</li>
 * <li>{@link #CHECKBOX} - 复选框</li>
 *
 * <li><b>签名与媒体元素:</b></li>
 * <li>{@link #SIGNATURE} - 签名区域</li>
 * <li>{@link #SEAL} - 印章区域</li>
 * <li>{@link #IMAGE} - 图片：用于Logo等非印章图像。</li>
 * </ul>
 * @author Ktriasia
 * @since 2025-09-19
 * @version 1.2.0
 */
public enum ElementType {
    // --- 核心文本元素 ---
    /** 条款 */
    CLAUSE,
    /** 一级标题 */
    HEADER_1,
    /** 二级标题 */
    HEADER_2,
    /** 三级标题 */
    HEADER_3,
    /** 文本段落 */
    PARAGRAPH,

    // --- 结构化元素 ---
    /** 当事人信息块 (例如 甲方/委托人 整体) */
    PARTY_INFO,
    /** 表格 */
    TABLE,
    /** 有序列表 (1, 2, 3...) */
    ORDERED_LIST,
    /** 无序列表 (•, •, •...) */
    UNORDERED_LIST,

    // --- 表单与交互元素 ---
    /** 待填写字段 (如下划线) */
    FILLABLE_FIELD,
    /** 复选框 */
    CHECKBOX,

    // --- 签名与媒体元素 ---
    /** 签名 */
    SIGNATURE,
    /** 印章 */
    SEAL,
    /** 图片 (如公司Logo) */
    IMAGE;

    /**
     * 判断是否为文本元素
     * @return 如果是文本元素返回true，否则返回false
     */
    public boolean isTextElement() {
        return this == CLAUSE || this == HEADER_1 || this == HEADER_2 ||
               this == HEADER_3 || this == PARAGRAPH;
    }

    /**
     * 判断是否为结构化元素
     * @return 如果是结构化元素返回true，否则返回false
     */
    public boolean isStructuralElement() {
        return this == PARTY_INFO || this == TABLE ||
               this == ORDERED_LIST || this == UNORDERED_LIST;
    }

    /**
     * 判断是否为表单元素
     * @return 如果是表单元素返回true，否则返回false
     */
    public boolean isFormElement() {
        return this == FILLABLE_FIELD || this == CHECKBOX;
    }

    /**
     * 判断是否为签名或媒体元素
     * @return 如果是签名或媒体元素返回true，否则返回false
     */
    public boolean isSignatureElement() {
        return this == SIGNATURE || this == SEAL || this == IMAGE;
    }

    /**
     * 将模板配置中的 elementType 字符串映射为 ElementType 枚举。
     * 兼容常见别名和大小写（目前按大写处理）。
     *
     * @param raw 原始字符串
     * @return 对应的ElementType枚举，如果无法映射则返回null
     */
    public static ElementType fromString(String raw) {
        if (raw == null || raw.trim().isEmpty()) {
            return null;
        }

        String key = raw.trim().toUpperCase();

        // 处理别名映射
        switch (key) {
            case "HEADER":
            case "HEADER_1":
                return HEADER_1;
            case "HEADER_2":
                return HEADER_2;
            case "HEADER_3":
                return HEADER_3;
            default:
                try {
                    // 其余值直接映射到同名枚举（如 CLAUSE, PARAGRAPH, TABLE...）
                    return ElementType.valueOf(key);
                } catch (IllegalArgumentException e) {
                    // 如果找不到对应的枚举值，返回null
                    return null;
                }
        }
    }
}
