package com.example.demo.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class QueryController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/query")
    public List<Map<String, Object>> executeQuery(@RequestBody Map<String, String> request) {
        String sql = request.get("sql");
        
        if (StringUtils.isBlank(sql)) {
            throw new IllegalArgumentException("请提供SQL查询语句");
        }
        
        if (!sql.trim().toLowerCase().startsWith("select")) {
            throw new IllegalArgumentException("只允许执行SELECT查询语句");
        }
        
        return jdbcTemplate.queryForList(sql);
    }
}