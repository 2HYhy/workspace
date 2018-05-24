package com.me.gacl.pojo;

import com.opencsv.bean.CsvBindByPosition;

/**
 * @author CH-yfy
 * @date 2018/5/2
 */
public class BugDO {

    @CsvBindByPosition(position=0)
    private String title;
    @CsvBindByPosition(position=1)
    private String itemId;
    @CsvBindByPosition(position=2)
    private String projectId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "BugDO{" +
                "title='" + title + '\'' +
                ", itemId='" + itemId + '\'' +
                ", projectId='" + projectId + '\'' +
                '}';
    }
}
