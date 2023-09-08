package com.yi.blogj.dto;

import org.springframework.http.HttpStatus;

public class Result {
    private int code;
    private String errMsg;
    private Object data;

    public static Result ok(String errMsg, Object data) {
        return new Result(HttpStatus.OK.value(), errMsg, data);
    }
    public static Result ok(Object data) {
        return ok(HttpStatus.OK.name(), data);
    }
    public static Result ok() {
        return Result.ok(null);
    }
    public static Result fail(int code, String errMsg) {
        return new Result(code, errMsg, null);
    }
    public static Result fail(String errMsg) {
        return Result.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), errMsg);
    }
    public static Result fail() {
        return Result.fail(HttpStatus.INTERNAL_SERVER_ERROR.name());
    }

    public Result() {
    }

    public Result(int code, String errMsg, Object data) {
        this.code = code;
        this.errMsg = errMsg;
        this.data = data;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
