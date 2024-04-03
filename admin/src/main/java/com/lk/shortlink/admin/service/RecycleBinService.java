package com.lk.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lk.shortlink.admin.remote.dto.req.ShortLinkRecycleBinPageReqDTO;
import com.lk.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;
import com.lk.shortlink.common.api.result.Result;

/**
 * URL 回收站接口层
 */
public interface RecycleBinService {

    /**
     * 分页查询回收站短链接
     *
     * @param requestParam 请求参数
     * @return 返回参数包装
     */
    Result<Page<ShortLinkPageRespDTO>> pageRecycleBinShortLink(ShortLinkRecycleBinPageReqDTO requestParam);
}
