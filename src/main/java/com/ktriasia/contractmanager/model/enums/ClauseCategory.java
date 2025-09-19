package com.ktriasia.contractmanager.model.enums;

/**
 * 条款类别枚举（最终架构版）
 * <p>深度整合《构建一套全面、可扩展的智能化合同条款库：一份架构与实施蓝图报告》的五部分框架，
 * 为动态合同生成、自动化风险评估和基于角色的权限控制提供坚实的架构基础。</p>
 *
 * @author Ktriasia
 * @since 2025-09-19
 * @version 2.0.0
 */
public enum ClauseCategory {

    // ===================================================================
    // 第一部分：基础框架条款 (Foundational Framework)
    // ===================================================================
    /** 定义条款 */
    DEFINITIONS,
    /** 工作范围与标的条款 */
    SCOPE_OF_WORK,
    /** 验收标准 */
    ACCEPTANCE_CRITERIA,

    // ===================================================================
    // 第二部分：商业核心条款 (The Commercial Core)
    // ===================================================================
    /** 定价模式 */
    PRICING,
    /** 支付安排 */
    PAYMENT_TERMS,
    /** 发票条款 */
    INVOICING,
    /** 逾期支付条款 */
    LATE_PAYMENT,
    /** 税费条款 */
    TAXES,
    /** 交付安排 */
    DELIVERY_LOGISTICS,
    /** 风险转移条款 */
    RISK_OF_LOSS,

    // ===================================================================
    // 第三部分：风险、责任与保险条款 (Risk, Liability, and Insurance)
    // ===================================================================
    /** 违约责任条款 */
    LIABILITY,
    /** 责任限制条款 */
    LIMITATION_OF_LIABILITY,
    /** 赔偿与弥偿条款 */
    INDEMNIFICATION,
    /** 保险条款 */
    INSURANCE,
    /** 不可抗力条款 */
    FORCE_MAJEURE,

    // ===================================================================
    // 第四部分：契约与保护性条款 (Covenants and Protections)
    // ===================================================================
    /** 权利与授权陈述 */
    REP_AUTHORITY,
    /** 履约保证 */
    WARRANTY_PERFORMANCE,
    /** 质量保证 */
    WARRANTY_QUALITY,
    /** 知识产权不侵权保证 */
    WARRANTY_IP_NON_INFRINGEMENT,
    /** 法律合规保证 */
    WARRANTY_COMPLIANCE_LAWS,
    /** 保密条款 */
    CONFIDENTIALITY,
    /** 知识产权条款 */
    INTELLECTUAL_PROPERTY,
    /** 数据保护条款 */
    DATA_PROTECTION,
    /** 数据处理协议 (DPA) */
    DATA_PROCESSING_AGREEMENT,
    /** 反腐败与反贿赂条款 */
    ANTI_CORRUPTION,
    /** 禁止劝诱条款 */
    NON_SOLICITATION,
    /** 公开宣传与标识使用条款 */
    PUBLICITY_AND_LOGO_USE,
    /** 审计权条款 */
    AUDIT_RIGHTS,

    // ===================================================================
    // 第五部分：程序机制条款 (Procedural Mechanics)
    // ===================================================================
    /** 期限与终止条款 */
    TERM_AND_TERMINATION,
    /** 适用法律条款 */
    GOVERNING_LAW,
    /** 争议解决条款 */
    DISPUTE_RESOLUTION,
    /** 通知条款 */
    NOTICES,
    /** 权利义务转让条款 */
    ASSIGNMENT,
    /** 双方关系条款 */
    RELATIONSHIP_OF_PARTIES,
    /** 效力存续条款 */
    SURVIVAL,
    /** 不弃权条款 */
    NO_WAIVER,
    /** 完整协议条款 */
    ENTIRE_AGREEMENT,
    /** 修订条款 */
    AMENDMENTS,
    /** 可分割性条款 */
    SEVERABILITY,
    /** 副本条款 */
    COUNTERPARTS,
    /** 其他条款 */
    OTHERS;
}
