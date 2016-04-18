package com.gyang.bean;

import java.io.Serializable;

/**
 * 通用Dubbo接口调用结果
 *
 * Created by declan.guo on 15-7-29.
 */
public class DubboInvResult<T extends Serializable> implements Serializable {

    private static final long serialVersionUID = -2564002925887144610L;

    /**
     * 返回结果code标识 200: OK
     */
    private int code;
    /**
     * 返回结果错误提示信息
     */
    private String errMsg = "";

    /**
     * Dubbo接口调用结果
     */
    private T value;

    public DubboInvResult() {

    }

    public DubboInvResult(int code, String errMsg) {
        this.code = code;
        this.errMsg = errMsg;
    }

    public DubboInvResult(int code, String errMsg, T value) {
        this.code = code;
        this.errMsg = errMsg;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    /**
     * 返回状态枚举
     */
    public static enum Code {
        CODE_200(200, "OK"),
        CODE_403(403, "403 ERROR");

        private int code;
        private String value;

        private Code(int code, String value) {
            this.code = code;
            this.value = value;
        }

        public int getCode() {
            return code;
        }

        public String getValue() {
            return value;
        }
    }
}
