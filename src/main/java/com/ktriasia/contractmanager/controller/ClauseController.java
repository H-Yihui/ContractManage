package com.ktriasia.contractmanager.controller;

import com.ktriasia.contractmanager.service.ClauseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("api")
public class ClauseController {
    private final ClauseService clauseService;
    @GetMapping("clauses")
    public ResponseEntity<Object> getAllClauses(){
        return clauseService.getAllClauses();
    }
}
