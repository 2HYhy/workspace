package com.me.hyh.pojo;

import com.opencsv.bean.CsvBindByName;

/**
 * @author CH-yfy
 * @date 2018/8/13
 */
public class BugDTO {
    @CsvBindByName(column = "编号", required = true)
    private String id;
    @CsvBindByName(column = "标题", required = true)
    private String content;
    @CsvBindByName(column = "日期", required = true)
    private String createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "BugDTO{" +
                "Id=" + id +
                ", content=" + content +
                ", createTime=" + createTime +
                '}';
    }
}
