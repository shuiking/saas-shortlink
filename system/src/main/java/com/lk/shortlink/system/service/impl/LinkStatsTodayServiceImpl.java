package com.lk.shortlink.system.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lk.shortlink.system.dao.entity.LinkStatsTodayDO;
import com.lk.shortlink.system.dao.mapper.LinkStatsTodayMapper;
import com.lk.shortlink.system.service.LinkStatsTodayService;
import org.springframework.stereotype.Service;

/**
 * 短链接今日统计接口实现层
 */

@Service
public class LinkStatsTodayServiceImpl extends ServiceImpl<LinkStatsTodayMapper, LinkStatsTodayDO> implements LinkStatsTodayService {
}
