package com.me.gacl;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author CH-yfy
 * @date 2018/4/23
 */
public class Memory {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date currentTime;

    private Long maxSave;

    private Long hasUsed;

    private Long stillFree;

    public Date getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Date currentTime) {
        this.currentTime = currentTime;
    }

    public Long getStillFree() {
        return stillFree;
    }

    public void setStillFree(Long stillFree) {
        this.stillFree = stillFree;
    }

    public Long getMaxSave() {
        return maxSave;
    }

    public void setMaxSave(Long maxSave) {
        this.maxSave = maxSave;
    }

    public Long getHasUsed() {
        return hasUsed;
    }

    public void setHasUsed(Long hasUsed) {
        this.hasUsed = hasUsed;
    }
}
