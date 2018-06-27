package com.me.gacl.domain;

import com.me.gacl.enums.Code;
import com.me.gacl.enums.Status;
import com.me.gacl.exception.JsonException;
import lombok.Builder;
import lombok.Data;

/**
 * @author yunhua.he
 * @date 2018/6/25
 * 统一返回的json对象
 */
@Data
@Builder
public class R<T> {

    private Integer code;
    private String message;
    private T data;

    public R(Integer code, String message, Object data) {

    }

    public R(Status ok) {

    }

    public static R success(Integer code, String message, Object data) {
        return new R(code, message, data);
    }

    public static R success() {
        return new R(Status.OK);
    }

    public static R success(String message) {
        return success(message, null);
    }

    public static R success(String message, Object data) {
        return success(Code.SUCCESS.getCode(), message, data);
    }

    public static R error(Integer code, String message, Object data) {
        return new R(code, message, data);
    }

    public static R error(Integer code, String message) {
        return error(code, message, null);
    }

    public static R error(JsonException exception) {
        return error(exception.getCode(), exception.getMessage());
    }
}
