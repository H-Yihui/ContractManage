package com.ktriasia.contractmanager.service;

import com.ktriasia.contractmanager.model.result.Result;
import com.ktriasia.contractmanager.model.pojo.Clause;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * 条款的服务层，提供获取所有条款、根据标题获取条款、根据分类获取条款以及获取所有条款分类的业务逻辑
 * @author ktriasia
 * @version 1.0.0
 * @since 2025-09-18
 */
@Service
public interface ClauseService extends IService<Clause> {
    /**
     * 获取所有条款
     * @return 所有条款的响应实体
     */
    ResponseEntity<Result<Object>> getAllClauses();

    ResponseEntity<Result<Object>> getClausesByTitle(String title);

    /**
     * 根据分类获取条款
     * @param category 条款分类
     * @return 匹配条款的响应实体
     */
    ResponseEntity<Result<Object>> getClausesByCategory(String category);

    /**
     * 获取所有条款分类
     * @return 所有条款分类的响应实体
     */
    ResponseEntity<Result<Object>> getAllCategories();
}
