package com.ktriasia.contractmanager.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ktriasia.contractmanager.model.mapper.ContractMapper;
import com.ktriasia.contractmanager.model.pojo.Contract;
import com.ktriasia.contractmanager.service.ContractService;
import org.springframework.stereotype.Service;

/**
 * 合同的服务层
 * @author ktriasia
 * @version 1.0.0
 * @since 2024-06-26
 */
@Service
public class ContractServiceImpl extends ServiceImpl<ContractMapper, Contract> implements ContractService {

}
