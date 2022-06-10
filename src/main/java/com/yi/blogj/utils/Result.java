package com.yi.blogj.utils;

import com.yi.blogj.enums.RConstants;

public class Result {
    private Integer code;
    private String msg;
    private Object data;

    private Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private Result() {}

    public static Result ok(Object data, String msg) {
        return new Result(RConstants.OK.getCode(), msg, data);
    }
    public static Result ok(Object data) {
        return new Result(RConstants.OK.getCode(), RConstants.OK.getMsg(), data);
    }
    public static Result fail() {
        return new Result(RConstants.FAIL.getCode(), RConstants.FAIL.getMsg(), null);
    }
    public static Result fail(int code, String msg) {
        return new Result(code, msg, null);
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
