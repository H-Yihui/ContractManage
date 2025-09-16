package com.ktriasia.contractmanager.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 合同主实体类。
 * <p>用于描述合同的基本信息，包括合同ID、名称、创建时间和更新时间等。</p>
 * <ul>
 *   <li>contractId：合同唯一标识</li>
 *   <li>contractName：合同名称</li>
 *   <li>createdAt：合同创建时间</li>
 *   <li>updatedAt：合同最后更新时间</li>
 * </ul>
 * @author Ktriasia
 * @since 2025-9-16
 * @version 1.0.0
 */
@Data
@TableName("contract")
public class Contract implements Serializable {
    /** 合同唯一标识 */
    @TableId(type = IdType.AUTO)
    Integer contractId;
    /** 合同名称 */
    String contractName;
    /** 合同创建时间 */
    LocalDateTime createdAt;
    /** 合同最后更新时间 */
    LocalDateTime updatedAt;
    /** 合同元素列表 */
    List<ContractElement> contractElements;
}
