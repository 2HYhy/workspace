package com.me.gacl.domain;

/**
 * Created by CH-yfy on 2017/9/29.
 */
public class Cards {  //一对多，employee-cards
    private int cId;
    private String cName;

    public int getcId() {
        return cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String toString(){
        return "cId="+cId+",cName="+cName;
    }
}
