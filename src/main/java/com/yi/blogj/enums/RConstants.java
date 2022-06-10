package com.yi.blogj.enums;

public enum RConstants {
    OK(20000, "操作成功！"),
    FAIL(20001, "操作失败！");

    public final int code;
    public final String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    RConstants(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
