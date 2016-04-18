package com.gyang.web.interceptor;

import com.google.common.base.Joiner;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * Created by gyang on 16-1-7.
 */
public class LoggerInterceptor extends HandlerInterceptorAdapter {

    private static final String generateTraceId() {
        return Joiner.on("").join(StringUtils.left(UUID.randomUUID().toString(), 8), StringUtils.right(String.valueOf(System.currentTimeMillis()), 4));
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        MDC.put("traceId", generateTraceId());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        MDC.remove("traceId");
    }
}
