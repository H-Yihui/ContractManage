package com.ktriasia.contractmanager.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ktriasia.contractmanager.model.mapper.ClauseMapper;
import com.ktriasia.contractmanager.model.pojo.Clause;
import com.ktriasia.contractmanager.service.ClauseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * 条款的服务层
 * @author ktriasia
 * @version 1.0.0
 * @since 2024-06-26
 */
@Service
public class ClauseServiceImpl extends ServiceImpl<ClauseMapper,Clause> implements ClauseService {

    @Autowired
    ClauseMapper clauseMapper;
    @Override
    public ResponseEntity<Object> getAllClauses() {
        List<Clause> clauses = clauseMapper.selectList(new QueryWrapper<>());
        return ResponseEntity.ok(clauses);
    }
}
