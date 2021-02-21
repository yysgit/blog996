
package com.yys.szcp.config;

import org.apache.shiro.web.servlet.OncePerRequestFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CORSFilter extends OncePerRequestFilter {

/*
     * 在ResponseBodyWrapHandler中已处理跨域问题
     * 但是在shiro验证未通过跳转/unauth时, 因为redirect 重定向会丢失所有请求头，跨域问题重新出现
     * */

    @Override
    protected void doFilterInternal(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        res.setCharacterEncoding("UTF-8");
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        res.setHeader("Access-Control-Max-Age", "3600");
        res.setHeader("Access-Control-Allow-Headers", "x-requested-with,Authorization");
        res.setHeader("Access-Control-Allow-Credentials","true");

        filterChain.doFilter(servletRequest, servletResponse);

    }
}
