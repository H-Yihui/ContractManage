package com.ktriasia.contractmanager.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ktriasia.contractmanager.model.enums.ClauseCategory;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.io.Serializable;

/**
 * 合同条款实体类。
 * <p>用于描述合同中的单条条款，包括条款ID、类别、标题和内容等信息。</p>
 * <ul>
 *   <li>clauseId：条款唯一标识</li>
 *   <li>clauseCategory：条款类别，使用 {@link ClauseCategory} 枚举</li>
 *   <li>title：条款标题</li>
 *   <li>content：条款具体内容</li>
 * </ul>
 * @author Ktriasia
 * @since 2025-9-16
 * @version 1.0.0
 */
@Data
@TableName("clause")
public class Clause implements Serializable {
    /** 条款唯一标识 */
    @TableId(type = IdType.AUTO)
    Integer clauseId;
    /** 条款类别，枚举类型，使用字符串存储 */
    @Enumerated(EnumType.STRING)
    ClauseCategory clauseCategory;
    /** 条款标题 */
    String title;
    /** 条款内容 */
    String content;
}