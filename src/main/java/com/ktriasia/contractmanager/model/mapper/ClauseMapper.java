package com.ktriasia.contractmanager.model.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ktriasia.contractmanager.model.pojo.Clause;
import org.apache.ibatis.annotations.Mapper;

/**
 * 条款的Mapper接口
 * @author ktriasia
 * @version 1.0.0
 * @since 2025-09-18
 */
@Mapper
public interface ClauseMapper extends BaseMapper<Clause> {

}
