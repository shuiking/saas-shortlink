package com.lk.shortlink.system.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Api("短链接不存在跳转控制器")
@Controller
@RequestMapping("/page")
public class ShortLinkNotfoundController {

    @ApiOperation("短链接不存在跳转页面")
    @RequestMapping("/notfound")
    public String notfound() {
        return "notfound";
    }
}
