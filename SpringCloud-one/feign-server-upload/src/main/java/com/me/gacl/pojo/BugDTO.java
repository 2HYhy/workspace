package com.me.gacl.pojo;

import com.opencsv.bean.CsvBindByName;

/**
 * @author CH-yfy
 * @date 2018/5/2
 */
public class BugDTO {

    @CsvBindByName(column = "缺陷标题", required = true)
    private String title;
    @CsvBindByName(column = "编号", required = true)
    private Integer itemId;
    @CsvBindByName(column = "项目号", required = true)
    private Integer projectId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "BugDTO{" +
                "title='" + title + '\'' +
                ", itemId=" + itemId +
                ", projectId=" + projectId +
                '}';
    }
}
