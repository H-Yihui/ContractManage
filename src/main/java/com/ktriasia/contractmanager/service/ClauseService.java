package com.ktriasia.contractmanager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ktriasia.contractmanager.model.pojo.Clause;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * 条款的服务层
 * @author ktriasia
 * @version 1.0.0
 * @since 2024-06-26
 */
@Service
public interface ClauseService extends IService<Clause> {
    ResponseEntity<Object> getAllClauses();
}
