package com.lk.shortlink.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lk.shortlink.system.dto.req.ShortLinkGroupStatsAccessRecordReqDTO;
import com.lk.shortlink.system.dto.req.ShortLinkGroupStatsReqDTO;
import com.lk.shortlink.system.dto.req.ShortLinkStatsAccessRecordReqDTO;
import com.lk.shortlink.system.dto.req.ShortLinkStatsReqDTO;
import com.lk.shortlink.system.dto.resp.ShortLinkStatsAccessRecordRespDTO;
import com.lk.shortlink.system.dto.resp.ShortLinkStatsRespDTO;


/**
 * 短链接监控接口层
 */

public interface ShortLinkStatsService {

    /**
     * 获取单个短链接监控数据
     *
     * @param requestParam 获取短链接监控数据入参
     * @return 短链接监控数据
     */
    ShortLinkStatsRespDTO oneShortLinkStats(ShortLinkStatsReqDTO requestParam);

    /**
     * 获取分组短链接监控数据
     *
     * @param requestParam 获取分组短链接监控数据入参
     * @return 分组短链接监控数据
     */
    ShortLinkStatsRespDTO groupShortLinkStats(ShortLinkGroupStatsReqDTO requestParam);

    /**
     * 访问单个短链接指定时间内访问记录监控数据
     *
     * @param requestParam 获取短链接监控访问记录数据入参
     * @return 访问记录监控数据
     */
    IPage<ShortLinkStatsAccessRecordRespDTO> shortLinkStatsAccessRecord(ShortLinkStatsAccessRecordReqDTO requestParam);

    /**
     * 访问分组短链接指定时间内访问记录监控数据
     *
     * @param requestParam 获取分组短链接监控访问记录数据入参
     * @return 分组访问记录监控数据
     */
    IPage<ShortLinkStatsAccessRecordRespDTO> groupShortLinkStatsAccessRecord(ShortLinkGroupStatsAccessRecordReqDTO requestParam);
}
