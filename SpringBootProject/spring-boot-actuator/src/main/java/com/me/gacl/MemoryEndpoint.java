package com.me.gacl;


import org.springframework.boot.actuate.endpoint.Endpoint;

import java.util.Date;

/**
 * @author CH-yfy
 * @date 2018/4/23
 * 自定义收集内存信息的endpoint,  http://localhost:8080/memoryPoint
 */
public class MemoryEndpoint implements Endpoint<Memory> {

    @Override
    public String getId() {
        return "memoryPoint";
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean isSensitive() {
        return false;
    }

    @Override
    public Memory invoke() {
        Memory temp = new Memory();
        Runtime runtime = Runtime.getRuntime();
        temp.setCurrentTime(new Date());
        temp.setMaxSave(runtime.maxMemory());
        temp.setHasUsed(runtime.totalMemory());
        temp.setStillFree(runtime.freeMemory());
        return temp;
    }
}
