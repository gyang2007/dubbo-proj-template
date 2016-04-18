package com.gyang.provider;

import com.gyang.api.IDubboService;
import com.gyang.bean.DubboInvResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by gyang on 16-4-18.
 */
@Service(value = "dubboService")
public class DubboServiceImpl implements IDubboService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DubboServiceImpl.class);

    @Override
    public DubboInvResult<String> process() {
        LOGGER.info("Start process...");
        try {
            TimeUnit.MILLISECONDS.sleep(new Random().nextInt(200));
        } catch (InterruptedException e) {
            // ...
        }

        return new DubboInvResult<>(200, null, new String("处理结果"));
    }
}
