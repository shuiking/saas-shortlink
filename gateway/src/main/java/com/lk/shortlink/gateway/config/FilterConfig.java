package com.lk.shortlink.gateway.config;

import lombok.Data;

import java.util.List;

/**
 * 过滤器配置
 */
@Data
public class FilterConfig {
    /**
     * 白名单前置路径
     */
    private List<String> whitePathList;
}
