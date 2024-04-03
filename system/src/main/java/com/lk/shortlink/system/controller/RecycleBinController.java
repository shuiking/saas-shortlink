package com.lk.shortlink.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lk.shortlink.common.api.result.Result;
import com.lk.shortlink.common.api.result.Results;
import com.lk.shortlink.system.dto.req.RecycleBinRecoverReqDTO;
import com.lk.shortlink.system.dto.req.RecycleBinRemoveReqDTO;
import com.lk.shortlink.system.dto.req.RecycleBinSaveReqDTO;
import com.lk.shortlink.system.dto.req.ShortLinkRecycleBinPageReqDTO;
import com.lk.shortlink.system.dto.resp.ShortLinkPageRespDTO;
import com.lk.shortlink.system.service.RecycleBinService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@Api("回收站管理控制层")
@RestController
@RequestMapping("/api/short-link/v1/recycle-bin")
public class RecycleBinController {
    @Resource
    private RecycleBinService recycleBinService;


    @ApiOperation("保存回收站")
    @PostMapping("/save")
    public Result<Void> saveRecycleBin(@RequestBody RecycleBinSaveReqDTO requestParam) {
        recycleBinService.saveRecycleBin(requestParam);
        return Results.success();
    }

    @ApiOperation("分页查询回收站短链接")
    @GetMapping("/page")
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkRecycleBinPageReqDTO requestParam) {
        return Results.success(recycleBinService.pageShortLink(requestParam));
    }

    @ApiOperation("恢复短链接")
    @PostMapping("/recover")
    public Result<Void> recoverRecycleBin(@RequestBody RecycleBinRecoverReqDTO requestParam) {
        recycleBinService.recoverRecycleBin(requestParam);
        return Results.success();
    }


    @ApiOperation("移除短链接")
    @PostMapping("/remove")
    public Result<Void> removeRecycleBin(@RequestBody RecycleBinRemoveReqDTO requestParam) {
        recycleBinService.removeRecycleBin(requestParam);
        return Results.success();
    }
}
