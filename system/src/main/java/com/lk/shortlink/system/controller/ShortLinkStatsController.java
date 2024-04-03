package com.lk.shortlink.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lk.shortlink.common.api.result.Result;
import com.lk.shortlink.common.api.result.Results;
import com.lk.shortlink.system.dto.req.ShortLinkGroupStatsAccessRecordReqDTO;
import com.lk.shortlink.system.dto.req.ShortLinkGroupStatsReqDTO;
import com.lk.shortlink.system.dto.req.ShortLinkStatsAccessRecordReqDTO;
import com.lk.shortlink.system.dto.req.ShortLinkStatsReqDTO;
import com.lk.shortlink.system.dto.resp.ShortLinkStatsAccessRecordRespDTO;
import com.lk.shortlink.system.dto.resp.ShortLinkStatsRespDTO;
import com.lk.shortlink.system.service.ShortLinkStatsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@Api("短链接监控控制层")
@RestController
@RequestMapping("/api/short-link/v1")
public class ShortLinkStatsController {
    @Resource
    private ShortLinkStatsService shortLinkStatsService;


    @ApiOperation("访问单个短链接指定时间内监控数据")
    @GetMapping("/stats")
    public Result<ShortLinkStatsRespDTO> shortLinkStats(ShortLinkStatsReqDTO requestParam) {
        return Results.success(shortLinkStatsService.oneShortLinkStats(requestParam));
    }


    @ApiOperation("访问分组短链接指定时间内监控数据")
    @GetMapping("/stats/group")
    public Result<ShortLinkStatsRespDTO> groupShortLinkStats(ShortLinkGroupStatsReqDTO requestParam) {
        return Results.success(shortLinkStatsService.groupShortLinkStats(requestParam));
    }


    @ApiOperation("访问单个短链接指定时间内访问记录监控数据")
    @GetMapping("/stats/access-record")
    public Result<IPage<ShortLinkStatsAccessRecordRespDTO>> shortLinkStatsAccessRecord(ShortLinkStatsAccessRecordReqDTO requestParam) {
        return Results.success(shortLinkStatsService.shortLinkStatsAccessRecord(requestParam));
    }


    @ApiOperation("访问分组短链接指定时间内访问记录监控数据")
    @GetMapping("/stats/access-record/group")
    public Result<IPage<ShortLinkStatsAccessRecordRespDTO>> groupShortLinkStatsAccessRecord(ShortLinkGroupStatsAccessRecordReqDTO requestParam) {
        return Results.success(shortLinkStatsService.groupShortLinkStatsAccessRecord(requestParam));
    }
}
