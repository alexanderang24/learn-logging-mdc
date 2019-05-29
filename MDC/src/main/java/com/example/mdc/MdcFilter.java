package com.example.mdc;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Component
public class MdcFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        MDC.put("userId", "");
        MDC.put("email", "");
        MDC.put("processId", "-1");
        MDC.put("value", "null");

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        //not implemented
    }

    @Override
    public void init(FilterConfig filterConfig) {
        //not implemented
    }

}
