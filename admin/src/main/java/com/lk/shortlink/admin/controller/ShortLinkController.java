package com.lk.shortlink.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lk.shortlink.admin.remote.ShortLinkActualRemoteService;
import com.lk.shortlink.admin.remote.dto.req.ShortLinkBatchCreateReqDTO;
import com.lk.shortlink.admin.remote.dto.req.ShortLinkCreateReqDTO;
import com.lk.shortlink.admin.remote.dto.req.ShortLinkPageReqDTO;
import com.lk.shortlink.admin.remote.dto.req.ShortLinkUpdateReqDTO;
import com.lk.shortlink.admin.remote.dto.resp.ShortLinkBaseInfoRespDTO;
import com.lk.shortlink.admin.remote.dto.resp.ShortLinkBatchCreateRespDTO;
import com.lk.shortlink.admin.remote.dto.resp.ShortLinkCreateRespDTO;
import com.lk.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;
import com.lk.shortlink.admin.toolkit.EasyExcelWebUtil;
import com.lk.shortlink.common.api.result.Result;
import com.lk.shortlink.common.api.result.Results;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@ApiOperation("短链接后管控制层")
@RestController
@RequestMapping("/api/short-link/admin/v1")
public class ShortLinkController {
    @Resource
    private ShortLinkActualRemoteService shortLinkActualRemoteService;

    @ApiOperation("创建短链接")
    @PostMapping("/create")
    public Result<ShortLinkCreateRespDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO requestParam) {
        return shortLinkActualRemoteService.createShortLink(requestParam);
    }


    @ApiOperation("批量创建短链接")
    @SneakyThrows
    @PostMapping("/create/batch")
    public void batchCreateShortLink(@RequestBody ShortLinkBatchCreateReqDTO requestParam, HttpServletResponse response) {
        Result<ShortLinkBatchCreateRespDTO> shortLinkBatchCreateRespDTOResult = shortLinkActualRemoteService.batchCreateShortLink(requestParam);
        if (shortLinkBatchCreateRespDTOResult.isSuccess()) {
            List<ShortLinkBaseInfoRespDTO> baseLinkInfos = shortLinkBatchCreateRespDTOResult.getData().getBaseLinkInfos();
            EasyExcelWebUtil.write(response, "批量创建短链接-SaaS短链接系统", ShortLinkBaseInfoRespDTO.class, baseLinkInfos);
        }
    }

    @ApiOperation("修改短链接")
    @PostMapping("/update")
    public Result<Void> updateShortLink(@RequestBody ShortLinkUpdateReqDTO requestParam) {
        shortLinkActualRemoteService.updateShortLink(requestParam);
        return Results.success();
    }

    @ApiOperation("分页查询短链接")
    @GetMapping("/page")
    public Result<Page<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO requestParam) {
        return shortLinkActualRemoteService.pageShortLink(requestParam.getGid(), requestParam.getOrderTag(), requestParam.getCurrent(), requestParam.getSize());
    }
}
