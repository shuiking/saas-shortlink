package com.lk.shortlink.system.controller;

import com.lk.shortlink.common.api.result.Result;
import com.lk.shortlink.common.api.result.Results;
import com.lk.shortlink.system.service.UrlTitleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@Api("标题控制层")
@RestController
@RequestMapping("/api/short-link/v1")
public class UrlTitleController {
    @Resource
    private UrlTitleService urlTitleService;

    @ApiOperation("根据 URL 动态获取对应网站的标题")
    @GetMapping("/title")
    public Result<String> getTitleByUrl(@RequestParam("url") String url) {
        return Results.success(urlTitleService.getTitleByUrl(url));
    }
}
