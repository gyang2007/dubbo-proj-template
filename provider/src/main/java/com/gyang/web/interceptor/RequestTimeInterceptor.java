package com.gyang.web.interceptor;

import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by gyang on 16-1-7.
 */
public class RequestTimeInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestTimeInterceptor.class);

    private static final ThreadLocal<StopWatch> watch = new ThreadLocal() {
        @Override
        protected Object initialValue() {
            return new StopWatch();
        }
    };

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        watch.get().start();
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        watch.get().stop();
        long time = watch.get().getTime();
        watch.remove();
        LOGGER.info("Request statistic, url = {}, consume time = {}", request.getRequestURI(), time);
    }
}
