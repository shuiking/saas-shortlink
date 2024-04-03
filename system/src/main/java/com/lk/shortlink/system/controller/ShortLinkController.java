package com.lk.shortlink.system.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lk.shortlink.common.api.result.Result;
import com.lk.shortlink.common.api.result.Results;
import com.lk.shortlink.system.dto.req.ShortLinkBatchCreateReqDTO;
import com.lk.shortlink.system.dto.req.ShortLinkCreateReqDTO;
import com.lk.shortlink.system.dto.req.ShortLinkPageReqDTO;
import com.lk.shortlink.system.dto.req.ShortLinkUpdateReqDTO;
import com.lk.shortlink.system.dto.resp.ShortLinkBatchCreateRespDTO;
import com.lk.shortlink.system.dto.resp.ShortLinkCreateRespDTO;
import com.lk.shortlink.system.dto.resp.ShortLinkGroupCountQueryRespDTO;
import com.lk.shortlink.system.dto.resp.ShortLinkPageRespDTO;
import com.lk.shortlink.system.handler.CustomBlockHandler;
import com.lk.shortlink.system.service.ShortLinkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.List;


@Api("短链接控制层")
@RestController
public class ShortLinkController {
    @Resource
    private ShortLinkService shortLinkService;

    @ApiOperation("短链接跳转原始链接")
    @GetMapping("/{short-uri}")
    public void restoreUrl(@PathVariable("short-uri") String shortUri, ServletRequest request, ServletResponse response) {
        shortLinkService.restoreUrl(shortUri, request, response);
    }

    @ApiOperation("创建短链接")
    @PostMapping("/api/short-link/v1/create")
    @SentinelResource(
            value = "create_short-link",
            blockHandler = "createShortLinkBlockHandlerMethod",
            blockHandlerClass = CustomBlockHandler.class
    )
    public Result<ShortLinkCreateRespDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO requestParam) {
        return Results.success(shortLinkService.createShortLink(requestParam));
    }


    @ApiOperation("通过分布式锁创建短链接")
    @PostMapping("/api/short-link/v1/create/by-lock")
    public Result<ShortLinkCreateRespDTO> createShortLinkByLock(@RequestBody ShortLinkCreateReqDTO requestParam) {
        return Results.success(shortLinkService.createShortLinkByLock(requestParam));
    }


    @ApiOperation("批量创建短链接")
    @PostMapping("/api/short-link/v1/create/batch")
    public Result<ShortLinkBatchCreateRespDTO> batchCreateShortLink(@RequestBody ShortLinkBatchCreateReqDTO requestParam) {
        return Results.success(shortLinkService.batchCreateShortLink(requestParam));
    }


    @ApiOperation("修改短链接")
    @PostMapping("/api/short-link/v1/update")
    public Result<Void> updateShortLink(@RequestBody ShortLinkUpdateReqDTO requestParam) {
        shortLinkService.updateShortLink(requestParam);
        return Results.success();
    }

    @ApiOperation("分页查询短链接")
    @GetMapping("/api/short-link/v1/page")
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO requestParam) {
        return Results.success(shortLinkService.pageShortLink(requestParam));
    }


    @ApiOperation("查询短链接分组内数量")
    @GetMapping("/api/short-link/v1/count")
    public Result<List<ShortLinkGroupCountQueryRespDTO>> listGroupShortLinkCount(@RequestParam("requestParam") List<String> requestParam) {
        return Results.success(shortLinkService.listGroupShortLinkCount(requestParam));
    }
}
