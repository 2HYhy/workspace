package com.me.hyh.pojo;

import com.opencsv.bean.CsvBindByPosition;

/**
 * @author CH-yfy
 * @date 2018/8/13
 */
public class BugDO {
    @CsvBindByPosition(position=0)
    private String id;
    @CsvBindByPosition(position=1)
    private String content;
    @CsvBindByPosition(position=2)
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
        return "BugDO{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
