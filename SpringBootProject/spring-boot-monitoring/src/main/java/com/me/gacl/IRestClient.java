package com.me.gacl;

/**
 * @author CH-yfy
 * @date 2018/5/28
 */
public interface IRestClient {

    /**
     * 调用其他接口服务
     * @param uri
     * @param responseType
     * @param <T>
     * @return
     * @throws Exception
     */
    <T> T get(String uri, Class<T> responseType) throws Exception;
}
