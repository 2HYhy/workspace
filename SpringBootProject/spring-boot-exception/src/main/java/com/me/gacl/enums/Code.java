package com.me.gacl.enums;

/**
 * @author CH-yfy
 * @date 2018/6/25
 */
public enum Code {

    SUCCESS(200);

    private Integer code;

    Code(Integer code){
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
