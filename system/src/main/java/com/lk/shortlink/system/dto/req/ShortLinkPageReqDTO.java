package com.lk.shortlink.system.dto.req;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lk.shortlink.system.dao.entity.ShortLinkDO;
import lombok.Data;

/**
 * 短链接分页请求参数
 */

@Data
public class ShortLinkPageReqDTO extends Page<ShortLinkDO> {

    /**
     * 分组标识
     */
    private String gid;

    /**
     * 排序标识
     */
    private String orderTag;
}
