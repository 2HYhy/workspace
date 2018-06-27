package com.me.gacl.exception;

/**
 * @author yunhua.he
 * @date 2018/6/25
 * 统一的json异常处理
 */
public class JsonException extends RuntimeException{

    public Integer code;

    public JsonException(Integer code,String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
