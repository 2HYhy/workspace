package com.me.gacl.domain;

/**
 * @author CH-yfy
 * @date 2017/12/22
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
