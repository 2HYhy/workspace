package com.me.gacl.enums;

/**
 * @author yunhua.he
 * @date 2018/6/25
 */
public enum Status {

    OK(200, "成功"), UNKNOW_ERROR(-1, "未知错误");

    private Integer code;
    private String message;

    Status(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
