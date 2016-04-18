package com.gyang.dubbo.filter;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import com.gyang.bean.BaseParam;
import com.gyang.util.MsgIdGenerator;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * Dubbo 调用接口 检查是否传递traceId属性，如果没有则设置一个有效的traceId属性
 * <p/>
 * Created by gyang on 16-4-6.
 */
@Activate(group = Constants.PROVIDER)
public class DubboInvokeTraceIdFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(DubboInvokeTraceIdFilter.class);
    private static final String TRACE = "traceId";

    /**
     * 流水号生成器
     */
    private static final MsgIdGenerator.MsgId MSG_ID = MsgIdGenerator.getInstance().nextMsgId();

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        Object[] arguments = invocation.getArguments();
        if (ArrayUtils.isNotEmpty(arguments)) {
            String traceId = StringUtils.EMPTY;
            for (Object arg : arguments) {
                if (arg != null && arg instanceof BaseParam) {
                    try {
                        BaseParam param = (BaseParam) arg;
                        if (StringUtils.isEmpty(param.getTraceId())) {
                            traceId = MSG_ID.nextSeq();
                            param.setTraceId(traceId);
                        } else {
                            traceId = param.getTraceId();
                        }

                        MDC.put(TRACE, traceId);
                        break;
                    } catch (Exception e) {
                        LOGGER.error("Rpc filter to set traceId exp!", e);
                    }
                }
            }
        }

        return invoker.invoke(invocation);
    }
}
