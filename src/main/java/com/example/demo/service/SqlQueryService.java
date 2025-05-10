package com.example.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;
import java.util.Map;

public interface SqlQueryService {
    /**
     * 执行自然语言查询
     * @param query 自然语言查询语句
     * @return 查询结果
     */
    List<Map<String, Object>> executeQuery(String query);

    /**
     * 分页执行自然语言查询
     * @param query 自然语言查询语句
     * @param page 页码
     * @param size 每页大小
     * @return 分页查询结果
     */
    IPage<Map<String, Object>> executePageQuery(String query, long page, long size);
}