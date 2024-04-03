package com.lk.shortlink.admin.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lk.shortlink.admin.remote.ShortLinkActualRemoteService;
import com.lk.shortlink.admin.remote.dto.req.ShortLinkGroupStatsAccessRecordReqDTO;
import com.lk.shortlink.admin.remote.dto.req.ShortLinkGroupStatsReqDTO;
import com.lk.shortlink.admin.remote.dto.req.ShortLinkStatsAccessRecordReqDTO;
import com.lk.shortlink.admin.remote.dto.req.ShortLinkStatsReqDTO;
import com.lk.shortlink.admin.remote.dto.resp.ShortLinkStatsAccessRecordRespDTO;
import com.lk.shortlink.admin.remote.dto.resp.ShortLinkStatsRespDTO;
import com.lk.shortlink.common.api.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api("短链接监控控制层")
@RestController
@RequestMapping("/api/short-link/admin/v1")
public class ShortLinkStatsController {
    @Resource
    private ShortLinkActualRemoteService shortLinkActualRemoteService;


    @ApiOperation("访问单个短链接指定时间内监控数据")
    @GetMapping("/stats")
    public Result<ShortLinkStatsRespDTO> shortLinkStats(ShortLinkStatsReqDTO requestParam) {
        return shortLinkActualRemoteService.oneShortLinkStats(requestParam.getFullShortUrl(), requestParam.getGid(), requestParam.getStartDate(), requestParam.getEndDate());
    }


    @ApiOperation("访问分组短链接指定时间内监控数据")
    @GetMapping("/stats/group")
    public Result<ShortLinkStatsRespDTO> groupShortLinkStats(ShortLinkGroupStatsReqDTO requestParam) {
        return shortLinkActualRemoteService.groupShortLinkStats(requestParam.getGid(), requestParam.getStartDate(), requestParam.getEndDate());
    }


    @ApiOperation("访问单个短链接指定时间内访问记录监控数据")
    @GetMapping("/stats/access-record")
    public Result<Page<ShortLinkStatsAccessRecordRespDTO>> shortLinkStatsAccessRecord(ShortLinkStatsAccessRecordReqDTO requestParam) {
        return shortLinkActualRemoteService.shortLinkStatsAccessRecord(requestParam.getFullShortUrl(), requestParam.getGid(), requestParam.getStartDate(), requestParam.getEndDate());
    }

    @ApiOperation("访问分组短链接指定时间内访问记录监控数据")
    @GetMapping("/stats/access-record/group")
    public Result<Page<ShortLinkStatsAccessRecordRespDTO>> groupShortLinkStatsAccessRecord(ShortLinkGroupStatsAccessRecordReqDTO requestParam) {
        return shortLinkActualRemoteService.groupShortLinkStatsAccessRecord(requestParam.getGid(), requestParam.getStartDate(), requestParam.getEndDate());
    }
}
