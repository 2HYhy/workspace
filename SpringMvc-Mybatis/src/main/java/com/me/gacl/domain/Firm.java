package com.me.gacl.domain;

/**
 * Created by yunhua.he on 2017/9/29.
 */
public class Firm {  //一对一，emplooyee-firm
    private int fId;
    private String fName;

    public int getfId() {
        return fId;
    }

    public String getfName() {
        return fName;
    }

    public void setfId(int fId) {
        this.fId = fId;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String toString(){
        return "fId="+fId+",fName="+fName;
    }
}
