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
     * 获取元素类型的描述信息
     * @return 元素类型的详细描述
     */
    public String getDescription() {
        switch (this) {
            case CLAUSE: return "合同条款，构成合同主体的法律条文";
            case HEADER_1: return "一级标题，用于主要章节标题";
            case HEADER_2: return "二级标题，用于子章节标题";
            case HEADER_3: return "三级标题，用于小节标题";
            case PARAGRAPH: return "文本段落，普通文本内容";
            case PARTY_INFO: return "当事人信息块，包含甲方或乙方完整信息";
            case TABLE: return "表格，用于展示结构化数据";
            case ORDERED_LIST: return "有序列表，带编号的项目列表";
            case UNORDERED_LIST: return "无序列表，带项目符号的列表";
            case FILLABLE_FIELD: return "待填写字段，如下划线或输入框";
            case CHECKBOX: return "复选框，用于选择项";
            case SIGNATURE: return "签名区域，用于签署姓名";
            case SEAL: return "印章区域，用于盖章";
            case IMAGE: return "图片，如公司Logo等图像";
            default: return "未知元素类型";
        }
    }

    /**
     * 根据原始字符串解析元素类型，兼容常见别名与大小写。
     * 例如："HEADER" -> HEADER_1；其余值按与枚举同名规则解析。
     * @param raw 原始字符串（大小写不敏感）
     * @return 对应的 ElementType
     * @throws IllegalArgumentException 当无法解析为合法枚举时抛出
     */
    public static ElementType fromString(String raw) {
        if (raw == null) return null;
        String key = raw.trim().toUpperCase();
        switch (key) {
            case "HEADER":
            case "HEADER_1":
                return ElementType.HEADER_1;
            case "HEADER_2":
                return ElementType.HEADER_2;
            case "HEADER_3":
                return ElementType.HEADER_3;
            default:
                return ElementType.valueOf(key);
        }
    }
}
