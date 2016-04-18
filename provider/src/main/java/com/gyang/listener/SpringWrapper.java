package com.gyang.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 获取Spring Bean对象的工具类
 *
 */
public class SpringWrapper implements ServletContextListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringWrapper.class);

    private static WebApplicationContext springContext;
    
    public SpringWrapper() {
        super();
    }
    
    public static ApplicationContext getApplicationContext() {
        return springContext;
    }


    public static <T> T getBean(Class<T> type) throws BeansException {
        return springContext.getBean(type);
    }

    public static Object getBean(String name) throws BeansException {
        return springContext.getBean(name);
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        springContext = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
             
    }

}