package com.lk.shortlink.admin.controller;

import cn.hutool.core.bean.BeanUtil;
import com.lk.shortlink.admin.dto.req.UserLoginReqDTO;
import com.lk.shortlink.admin.dto.req.UserRegisterReqDTO;
import com.lk.shortlink.admin.dto.req.UserUpdateReqDTO;
import com.lk.shortlink.admin.dto.resp.UserActualRespDTO;
import com.lk.shortlink.admin.dto.resp.UserLoginRespDTO;
import com.lk.shortlink.admin.dto.resp.UserRespDTO;
import com.lk.shortlink.admin.service.UserService;
import com.lk.shortlink.common.api.result.Result;
import com.lk.shortlink.common.api.result.Results;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@Api("用户管理控制层")
@RestController
@RequestMapping("/api/short-link/admin/v1")
public class UserController {
    @Resource
    private UserService userService;

    @ApiOperation("根据用户名查询用户信息")
    @GetMapping("/user/{username}")
    public Result<UserRespDTO> getUserByUsername(@PathVariable("username") String username) {
        return Results.success(userService.getUserByUsername(username));
    }


    @ApiOperation("根据用户名查询无脱敏用户信息")
    @GetMapping("/user/actual/{username}")
    public Result<UserActualRespDTO> getActualUserByUsername(@PathVariable("username") String username) {
        return Results.success(BeanUtil.toBean(userService.getUserByUsername(username), UserActualRespDTO.class));
    }

    @ApiOperation("查询用户名是否存在")
    @GetMapping("/user/has-username")
    public Result<Boolean> hasUsername(@RequestParam("username") String username) {
        return Results.success(userService.hasUsername(username));
    }


    @ApiOperation("注册用户")
    @PostMapping("/user")
    public Result<Void> register(@RequestBody UserRegisterReqDTO requestParam) {
        userService.register(requestParam);
        return Results.success();
    }

    @ApiOperation("修改用户")
    @PutMapping("/user")
    public Result<Void> update(@RequestBody UserUpdateReqDTO requestParam) {
        userService.update(requestParam);
        return Results.success();
    }

    @ApiOperation("用户登录")
    @PostMapping("/user/login")
    public Result<UserLoginRespDTO> login(@RequestBody UserLoginReqDTO requestParam) {
        return Results.success(userService.login(requestParam));
    }

    @ApiOperation("检查用户是否登录")
    @GetMapping("/user/check-login")
    public Result<Boolean> checkLogin(@RequestParam("username") String username, @RequestParam("token") String token) {
        return Results.success(userService.checkLogin(username, token));
    }

    @ApiOperation("用户退出登录")
    @DeleteMapping("/user/logout")
    public Result<Void> logout(@RequestParam("username") String username, @RequestParam("token") String token) {
        userService.logout(username, token);
        return Results.success();
    }
}
