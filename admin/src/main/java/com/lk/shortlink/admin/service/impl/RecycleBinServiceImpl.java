package com.lk.shortlink.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lk.shortlink.admin.common.biz.user.UserContext;
import com.lk.shortlink.admin.dao.entity.GroupDO;
import com.lk.shortlink.admin.dao.mapper.GroupMapper;
import com.lk.shortlink.admin.remote.ShortLinkActualRemoteService;
import com.lk.shortlink.admin.remote.dto.req.ShortLinkRecycleBinPageReqDTO;
import com.lk.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;
import com.lk.shortlink.admin.service.RecycleBinService;
import com.lk.shortlink.common.api.exception.ServiceException;
import com.lk.shortlink.common.api.result.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;


/**
 * URL 回收站接口实现层
 */

@Service
public class RecycleBinServiceImpl implements RecycleBinService {
    @Resource
    private ShortLinkActualRemoteService shortLinkActualRemoteService;
    @Resource
    private GroupMapper groupMapper;

    @Override
    public Result<Page<ShortLinkPageRespDTO>> pageRecycleBinShortLink(ShortLinkRecycleBinPageReqDTO requestParam) {
        // 当前用户的分组信息
        LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getUsername, UserContext.getUsername())
                .eq(GroupDO::getDelFlag, 0);
        List<GroupDO> groupDOList = groupMapper.selectList(queryWrapper);

        if (CollUtil.isEmpty(groupDOList)) {
            throw new ServiceException("用户无分组信息");
        }

        // 获取分页信息
        requestParam.setGidList(groupDOList.stream().map(GroupDO::getGid).collect(Collectors.toList()));
        return shortLinkActualRemoteService.pageRecycleBinShortLink(requestParam.getGidList(), requestParam.getCurrent(), requestParam.getSize());
    }
}
