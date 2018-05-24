package com.me.gacl.pojo;

/**
 * @author CH-yfy
 * @date 2018/5/2
 */
public class BugBO {

    private String title;
    private String itemId;
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
        return "BugBO{" +
                "title='" + title + '\'' +
                ", itemId='" + itemId + '\'' +
                ", projectId='" + projectId + '\'' +
                '}';
    }
}
