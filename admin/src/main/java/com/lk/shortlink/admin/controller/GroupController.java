package com.lk.shortlink.admin.controller;

import com.lk.shortlink.admin.dto.req.ShortLinkGroupSaveReqDTO;
import com.lk.shortlink.admin.dto.req.ShortLinkGroupSortReqDTO;
import com.lk.shortlink.admin.dto.req.ShortLinkGroupUpdateReqDTO;
import com.lk.shortlink.admin.dto.resp.ShortLinkGroupRespDTO;
import com.lk.shortlink.admin.service.GroupService;
import com.lk.shortlink.common.api.result.Result;
import com.lk.shortlink.common.api.result.Results;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@Api("短链接分组控制层")
@RestController
@RequestMapping("/api/short-link/admin/v1")
public class GroupController {
    @Resource
    private GroupService groupService;

    @ApiOperation("新增短链接分组")
    @PostMapping("/group")
    public Result<Void> save(@RequestBody ShortLinkGroupSaveReqDTO requestParam) {
        groupService.saveGroup(requestParam.getName());
        return Results.success();
    }

    @ApiOperation("查询短链接分组集合")
    @GetMapping("/group")
    public Result<List<ShortLinkGroupRespDTO>> listGroup() {
        return Results.success(groupService.listGroup());
    }

    @ApiOperation("修改短链接分组名称")
    @PutMapping("/group")
    public Result<Void> updateGroup(@RequestBody ShortLinkGroupUpdateReqDTO requestParam) {
        groupService.updateGroup(requestParam);
        return Results.success();
    }

    @ApiOperation("删除短链接分组")
    @DeleteMapping("/group")
    public Result<Void> updateGroup(@RequestParam String gid) {
        groupService.deleteGroup(gid);
        return Results.success();
    }

    @ApiOperation("排序短链接分组")
    @PostMapping("/sort")
    public Result<Void> sortGroup(@RequestBody List<ShortLinkGroupSortReqDTO> requestParam) {
        groupService.sortGroup(requestParam);
        return Results.success();
    }
}
