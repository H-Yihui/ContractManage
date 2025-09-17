package com.ktriasia.contractmanager.model.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ktriasia.contractmanager.model.pojo.Contract;
import org.apache.ibatis.annotations.Mapper;

/**
 * 合同的Mapper接口
 * @author ktriasia
 * @version 1.0.0
 * @since 2025-09-18
 */
@Mapper
public interface ContractMapper extends BaseMapper<Contract> {

}
