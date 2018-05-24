package com.me.gacl.pojo;

/**
 * @author CH-yfy
 * @date 2018/5/3
 */
public class UploadInfo {

    private int id;
    private long size;
    private String name;

    public UploadInfo() {
    }
    public UploadInfo(int id, long size, String name) {
        this.id = id;
        this.size = size;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UploadInfo{" +
                "id=" + id +
                ", size=" + size +
                ", name='" + name + '\'' +
                '}';
    }
}
