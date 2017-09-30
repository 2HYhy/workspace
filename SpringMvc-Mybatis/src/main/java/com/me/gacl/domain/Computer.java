package com.me.gacl.domain;

/**
 * Created by yunhua.he on 2017/8/23.
 */
public class Computer {

    private int comId;
    private String comType;
    private int comSize;
    private String comColor;

    public String getComType() {
        return comType;
    }

    public void setComType(String comType) {
        this.comType = comType;
    }

    public int getComSize() {
        return comSize;
    }

    public void setComSize(int comSize) {
        this.comSize = comSize;
    }

    public String getComColor() {
        return comColor;
    }

    public void setComColor(String comColor) {
        this.comColor = comColor;
    }

    public int getComId() {
        return comId;
    }

    public void setComId(int comId) {
        this.comId = comId;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "comId='" + comId + '\'' +
                "comType='" + comType + '\'' +
                ", comSize=" + comSize +
                ", comColor='" + comColor + '\'' +
                '}';
    }
}
