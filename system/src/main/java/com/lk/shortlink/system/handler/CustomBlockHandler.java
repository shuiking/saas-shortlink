package com.lk.shortlink.system.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.lk.shortlink.common.api.result.Result;
import com.lk.shortlink.system.dto.req.ShortLinkCreateReqDTO;
import com.lk.shortlink.system.dto.resp.ShortLinkCreateRespDTO;

/**
 * 自定义流控策略
 */

public class CustomBlockHandler {
    public static Result<ShortLinkCreateRespDTO> createShortLinkBlockHandlerMethod(ShortLinkCreateReqDTO requestParam, BlockException exception) {
        return new Result<ShortLinkCreateRespDTO>().setCode("B100000").setMessage("当前访问网站人数过多，请稍后再试...");
    }
}
