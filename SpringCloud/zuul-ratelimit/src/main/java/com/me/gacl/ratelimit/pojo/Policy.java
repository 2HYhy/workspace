package com.me.gacl.ratelimit.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static java.util.concurrent.TimeUnit.MINUTES;

/**
 * @author CH-yfy
 * @date 2018/5/31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Policy {

    /**
     * 限制刷新周期(单位: 秒), 默认60秒
     */
    private Long refreshInterval = MINUTES.toSeconds(1L);

    /**
     * 请求次数限制
     */
    private Long limit;

    /**
     * 限制类型列表,允许同时配置多种限制类型,不过一般情况下只会配置一种
     */
    private List<Type> type = new ArrayList<>();

    /**
     * 限制类型, 可按照原始IP地址, 用户或者URL来限制
     */
    public enum Type {
        ORIGIN, URL, USER
    }

    public Long getRefreshInterval() {
        return refreshInterval;
    }

    public Long getLimit() {
        return limit;
    }

    public List<Type> getType() {
        return type;
    }
}
