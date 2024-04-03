package com.lk.shortlink.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lk.shortlink.admin.dto.req.RecycleBinSaveReqDTO;
import com.lk.shortlink.admin.remote.ShortLinkActualRemoteService;
import com.lk.shortlink.admin.remote.dto.req.RecycleBinRecoverReqDTO;
import com.lk.shortlink.admin.remote.dto.req.RecycleBinRemoveReqDTO;
import com.lk.shortlink.admin.remote.dto.req.ShortLinkRecycleBinPageReqDTO;
import com.lk.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;
import com.lk.shortlink.admin.service.RecycleBinService;
import com.lk.shortlink.common.api.result.Result;
import com.lk.shortlink.common.api.result.Results;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@Api("回收站管理控制层")
@RestController
@RequestMapping("/api/short-link/admin/v1/recycle-bin")
public class RecycleBinController {
    @Resource
    private RecycleBinService recycleBinService;
    @Resource
    private ShortLinkActualRemoteService shortLinkActualRemoteService;

    @ApiOperation("保存回收站")
    @PostMapping("/save")
    public Result<Void> saveRecycleBin(@RequestBody RecycleBinSaveReqDTO requestParam) {
        shortLinkActualRemoteService.saveRecycleBin(requestParam);
        return Results.success();
    }

    @ApiOperation("分页查询回收站短链接")
    @GetMapping("/page")
    public Result<Page<ShortLinkPageRespDTO>> pageShortLink(ShortLinkRecycleBinPageReqDTO requestParam) {
        return recycleBinService.pageRecycleBinShortLink(requestParam);
    }


    @ApiOperation("恢复短链接")
    @PostMapping("/recover")
    public Result<Void> recoverRecycleBin(@RequestBody RecycleBinRecoverReqDTO requestParam) {
        shortLinkActualRemoteService.recoverRecycleBin(requestParam);
        return Results.success();
    }

    @ApiOperation("移除短链接")
    @PostMapping("/remove")
    public Result<Void> removeRecycleBin(@RequestBody RecycleBinRemoveReqDTO requestParam) {
        shortLinkActualRemoteService.removeRecycleBin(requestParam);
        return Results.success();
    }

}
