package com.me.gacl.exception;

/**
 * @author yunhua.he
 * @date 2018/6/25
 * 统一的页面异常处理
 */
public class PageException extends RuntimeException{

    public Integer code;

    public PageException(Integer code,String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
