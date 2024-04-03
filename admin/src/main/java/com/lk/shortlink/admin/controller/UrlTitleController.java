package com.lk.shortlink.admin.controller;

import com.lk.shortlink.admin.remote.ShortLinkActualRemoteService;
import com.lk.shortlink.common.api.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api("URL 标题控制层")
@RestController
@RequestMapping("/api/short-link/admin/v1")
public class UrlTitleController {
    @Resource
    private ShortLinkActualRemoteService shortLinkActualRemoteService;


    @ApiOperation("根据URL获取对应网站的标题")
    @GetMapping("/title")
    public Result<String> getTitleByUrl(@RequestParam("url") String url) {
        return shortLinkActualRemoteService.getTitleByUrl(url);
    }
}
