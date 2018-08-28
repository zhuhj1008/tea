package com.joe.common.spring.filter;

import org.apache.logging.log4j.core.config.Order;
import org.springframework.boot.web.servlet.FilterRegistrationBean;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 请求参数过滤
 * create by Joe on 2018-08-28 18:32
 **/
@WebFilter
@Order(FilterRegistrationBean.LOWEST_PRECEDENCE)
public class RequestParamFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //可以在这里设置过滤规则
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
