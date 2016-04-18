package com.gyang.api;

import com.gyang.bean.DubboInvResult;

/**
 * Dubbo api接口示例
 *
 * Created by gyang on 16-4-18.
 */
public interface IDubboService {
    DubboInvResult<String> process();
}
