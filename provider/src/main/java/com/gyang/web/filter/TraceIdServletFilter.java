package com.gyang.web.filter;

import com.gyang.util.MsgIdGenerator;
import org.slf4j.MDC;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by gyang on 16-4-8.
 */
public class TraceIdServletFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        MDC.put("traceId", MsgIdGenerator.getInstance().nextMsgId().nextSeq());
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
