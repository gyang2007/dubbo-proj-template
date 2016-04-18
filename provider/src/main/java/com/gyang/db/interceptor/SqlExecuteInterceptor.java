package com.gyang.db.interceptor;

import org.apache.commons.lang3.time.StopWatch;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * 统计数据库执行时间
 * Created by declan.guo on 15-8-6.
 */
@Intercepts(
        {
                @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
                @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
        }
)
public class SqlExecuteInterceptor implements Interceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(SqlExecuteInterceptor.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        String className = "unkonw_class";
        String methodName = "unkonw_method";
        try {
            MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
            String id = mappedStatement.getId();
            int index = id.lastIndexOf('.');
            if(index != -1) {
                className = id.substring(0, index);
                methodName = id.substring(index + 1, id.length());
            } else {
                className = id;
                methodName = id;
            }

            return invocation.proceed();
        } catch (Throwable throwable) {
            LOGGER.error(String.format("db error, class = %s, method = %s", new Object[]{className, methodName}), throwable);

            throw throwable;
        } finally {
            stopWatch.stop();
            LOGGER.info("db access consume time: {}, class = {}, method = {}",
                    new Object[]{stopWatch.getTime(), className, methodName});
        }
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
