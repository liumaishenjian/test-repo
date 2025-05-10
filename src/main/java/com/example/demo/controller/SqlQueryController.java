package com.example.demo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.common.Result;
import com.example.demo.service.SqlQueryService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/sql")
@RequiredArgsConstructor
public class SqlQueryController {

    private final SqlQueryService sqlQueryService;

    
    @PostMapping("/query")
    public Result<List<Map<String, Object>>> query(
            
            @RequestBody String query) {
        return Result.success(sqlQueryService.executeQuery(query));
    }

    
    @PostMapping("/query/page")
    public Result<IPage<Map<String, Object>>> queryPage(
            
            @RequestBody String query,
            
            @RequestParam(defaultValue = "1") long page,
            
            @RequestParam(defaultValue = "10") long size) {
        return Result.success(sqlQueryService.executePageQuery(query, page, size));
    }
}