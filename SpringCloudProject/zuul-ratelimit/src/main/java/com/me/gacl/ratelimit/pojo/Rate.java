package com.me.gacl.ratelimit.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author CH-yfy
 * @date 2018/5/31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rate {

    /**
     * 单位时间内请求数限制
     */
    private Long limit;

    /**
     * 单位时间内剩余请求次数
     */
    private Long remaining;

    /**
     * 重置限制周期剩余时间(单位: 秒)
     */
    private Long reset;

    public Rate(Long limit, long max, long l) {
        this.limit = limit;
        this.remaining = max;
        this.reset = l;
    }
}
