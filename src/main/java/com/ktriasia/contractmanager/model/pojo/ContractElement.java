package com.ktriasia.contractmanager.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ktriasia.contractmanager.model.enums.ElementType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 合同元素实体类。
 * <p>用于描述合同中的各类元素，包括条款、标题和文本段落等。</p>
 * <ul>
 *   <li>elementId：元素唯一标识</li>
 *   <li>contractId：所属合同ID</li>
 *   <li>elementType：元素类型，使用 {@link ElementType} 枚举</li>
 *   <li>content：元素内容</li>
 *   <li>sourceClauseId：来源条款ID（如果适用）</li>
 * </ul>
 * @author Ktriasia
 * @since 2025-9-16
 * @version 1.0.0
 */
@Data
public class ContractElement {
    /** 元素唯一标识 */
    @TableId(type= IdType.AUTO)
    Integer elementId;
    /** 所属合同ID */
    Integer contractId;
    /** 元素类型，枚举类型，使用字符串存储 */
    @Enumerated(EnumType.STRING)
    ElementType elementType;
    /** 元素内容 */
    String content;
    /** 来源条款ID(如果适用) */
    Integer sourceClauseId;
    /** 关联的条款对象(如果适用) */
    Clause clause;
    /** 关联的合同对象 */
    Contract contract;
}