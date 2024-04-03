package com.lk.shortlink.admin.common.biz.user;

import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 用户信息传输过滤器
 */

@RequiredArgsConstructor
public class UserTransmitFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String username = httpServletRequest.getHeader("username");
        if (StrUtil.isNotBlank(username)) {
            String userId = httpServletRequest.getHeader("userId");
            String realName = httpServletRequest.getHeader("realName");
            UserInfoDTO userInfoDTO = new UserInfoDTO(userId, username, realName);
            UserContext.setUser(userInfoDTO);
        }
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            UserContext.removeUser();
        }
    }
}
