package com.ktriasia.contractmanager.model.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ktriasia.contractmanager.model.pojo.Contract;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ContractMapper extends BaseMapper<Contract> {

}
