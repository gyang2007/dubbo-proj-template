package com.gyang.bean;

import java.io.Serializable;

/**
 * Created by gyang on 16-4-5.
 */
public abstract class BaseParam implements Serializable {

    /**
     * traceId
     */
    private String traceId;

    /**
     * 调用源
     */
    private String source;

    protected BaseParam(String traceId, String source) {
        this.traceId = traceId;
        this.source = source;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getTraceId() {
        return traceId;
    }

    public String getSource() {
        return source;
    }
}
