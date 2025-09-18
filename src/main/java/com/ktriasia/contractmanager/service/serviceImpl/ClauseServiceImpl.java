package com.ktriasia.contractmanager.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ktriasia.contractmanager.model.dto.ClauseDTO;
import com.ktriasia.contractmanager.model.dto.Result;
import com.ktriasia.contractmanager.model.enums.ClauseCategory;
import com.ktriasia.contractmanager.model.mapper.ClauseMapper;
import com.ktriasia.contractmanager.model.pojo.Clause;
import com.ktriasia.contractmanager.service.ClauseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 条款的服务层
 * @author ktriasia
 * @version 1.0.0
 * @since 2025-09-18
 */
@Service
@RequiredArgsConstructor
public class ClauseServiceImpl extends ServiceImpl<ClauseMapper,Clause> implements ClauseService {

    private final ClauseMapper clauseMapper;

    /**
     * 获取所有条款
     * @author ktriasia
     * @since 2025-09-18
     * @return 所有条款的响应实体
     */
    @Override
    public ResponseEntity<Result<Object>> getAllClauses() {
        List<Clause> clauses = clauseMapper.selectList(new QueryWrapper<>());
        List<ClauseDTO> clauseDTOs = clauses.stream()
                .map(ClauseDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(Result.success(clauseDTOs));
    }

    /**
     * 根据标题获取条款
     * @author ktriasia
     * @since 2025-09-18
     * @param title 条款标题
     * @return 匹配条款的响应实体
     */
    @Override
    public ResponseEntity<Result<Object>> getClausesByTitle(String title) {
        QueryWrapper<Clause> clauseQueryWrapper = new QueryWrapper<>();
        clauseQueryWrapper.like("title", title);
        List<Clause> clauses = clauseMapper.selectList(clauseQueryWrapper);
        List<ClauseDTO> clauseDTOs = clauses.stream()
                .map(ClauseDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(Result.success(clauseDTOs));
    }

    /**
     * 根据分类获取条款
     * @author ktriasia
     * @since 2025-09-18
     * @param category 条款分类
     * @return 匹配条款的响应实体
     */
    @Override
    public ResponseEntity<Result<Object>> getClausesByCategory(String category) {
        QueryWrapper<Clause> clauseQueryWrapper = new QueryWrapper<>();
        clauseQueryWrapper.eq("clause_category", category);
        List<Clause> clauses = clauseMapper.selectList(clauseQueryWrapper);
        List<ClauseDTO> clauseDTOs = clauses.stream()
                .map(ClauseDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(Result.success(clauseDTOs));
    }

    /**
     * 获取所有条款分类
     * @author ktriasia
     * @since 2025-09-18
     * @return 所有条款分类的响应实体
     */
    @Override
    public ResponseEntity<Result<Object>> getAllCategories() {
        List<ClauseCategory> categories = Arrays.asList(ClauseCategory.values());
        List<String> categoryNames = categories.stream()
                .map(Enum::name)
                .collect(Collectors.toList());
        return ResponseEntity.ok(Result.success(categoryNames));
    }
}
