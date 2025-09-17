package com.ktriasia.contractmanager.controller;

import com.ktriasia.contractmanager.model.enums.ClauseCategory;
import com.ktriasia.contractmanager.service.ClauseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

/**
 * 条款的控制层，提供获取所有条款、根据标题获取条款、根据分类获取条款以及获取所有条款分类的功能
 * @author ktriasia
 * @version 1.0.1
 * @since 2025-09-18
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("api/clauses")
public class ClauseController {

    private final ClauseService clauseService;

    /**
     * 获取所有条款
     * @return 所有条款的响应实体
     */
    @GetMapping
    public ResponseEntity<Object> getAllClauses(){
        return clauseService.getAllClauses();
    }

    /**
     * 根据标题获取条款
     * @param title 条款标题
     * @return 匹配条款的响应实体
     */
    @GetMapping(params = {"title"})
    public ResponseEntity<Object> getClausesByTitle(@RequestParam("title") String title){
        return clauseService.getClausesByTitle(title);
    }

    /**
     * 根据分类获取条款
     * @param category 条款分类
     * @return 匹配条款的响应实体
     */
    @GetMapping(params = {"category"})
    public ResponseEntity<Object> getClausesByCategory(@RequestParam("category") String category){
        return clauseService.getClausesByCategory(category);
    }

    /**
     * 获取所有条款分类
     * @return 所有条款分类的响应实体
     */
    @GetMapping("categories")
    public ResponseEntity<Object> getAllCategories(){
        return clauseService.getAllCategories();
    }

}
