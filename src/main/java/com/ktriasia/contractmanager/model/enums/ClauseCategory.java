package com.ktriasia.contractmanager.model.enums;

/**
 * 条款类别枚举
 * <p>定义了合同中常见的条款类型，便于分类和管理。</p>
 * <ul>
 *   <li>{@link #PAYMENT} 付款条款</li>
 *   <li>{@link #DELIVERY} 交付条款</li>
 *   <li>{@link #LIABILITY} 责任条款</li>
 *   <li>{@link #CONFIDENTIALITY} 保密条款</li>
 *   <li>{@link #TERMINATION} 终止条款</li>
 *   <li>{@link #DISPUTE_RESOLUTION} 争议解决条款</li>
 *   <li>{@link #FORCE_MAJEURE} 不可抗力条款</li>
 *   <li>{@link #INTELLECTUAL_PROPERTY} 知识产权条款</li>
 *   <li>{@link #WARRANTIES} 保证条款</li>
 *   <li>{@link #INDEMNIFICATION} 赔偿条款</li>
 *   <li>{@link #GOVERNING_LAW} 适用法律条款</li>
 *   <li>{@link #AMENDMENTS} 修订条款</li>
 *   <li>{@link #ASSIGNMENT} 转让条款</li>
 *   <li>{@link #NOTICES} 通知条款</li>
 *   <li>{@link #ENTIRE_AGREEMENT} 完整协议条款</li>
 *   <li>{@link #SEVERABILITY} 可分割性条款</li>
 *   <li>{@link #COUNTERPARTS} 副本条款</li>
 *   <li>{@link #OTHERS} 其他条款</li>
 * </ul>
 * @author Ktriasia
 * @since 2025-09-16
 * @version 1.0.0
 */
public enum ClauseCategory {
    /** 付款条款 */
    PAYMENT,
    /** 交付条款 */
    DELIVERY,
    /** 责任条款 */
    LIABILITY,
    /** 保密条款 */
    CONFIDENTIALITY,
    /** 终止条款 */
    TERMINATION,
    /** 争议解决条款 */
    DISPUTE_RESOLUTION,
    /** 不可抗力条款 */
    FORCE_MAJEURE,
    /** 知识产权条款 */
    INTELLECTUAL_PROPERTY,
    /** 保证条款 */
    WARRANTIES,
    /** 赔偿条款 */
    INDEMNIFICATION,
    /** 适用法律条款 */
    GOVERNING_LAW,
    /** 修订条款 */
    AMENDMENTS,
    /** 转让条款 */
    ASSIGNMENT,
    /** 通知条款 */
    NOTICES,
    /** 完整协议条款 */
    ENTIRE_AGREEMENT,
    /** 可分割性条款 */
    SEVERABILITY,
    /** 副本条款 */
    COUNTERPARTS,
    /** 其他条款 */
    OTHERS
}
