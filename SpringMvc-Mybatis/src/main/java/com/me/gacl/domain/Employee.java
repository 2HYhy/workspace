package com.me.gacl.domain;

import java.util.List;

/**
 * Created by yunhua.he on 2017/9/29.
 */
public class Employee {
    private int eId;
    private String eName;
    private Firm firm;
    private List<Cards> card;

    public int geteId() {
        return eId;
    }

    public String geteName() {
        return eName;
    }

    public Firm getFirm() {
        return firm;
    }

    public List<Cards> getCard() {
        return card;
    }

    public void seteId(int eId) {
        this.eId = eId;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public void setFirm(Firm firm) {
        this.firm = firm;
    }

    public void setCard(List<Cards> card) {
        this.card = card;
    }
    public String toString(){
        return "employee[ eId="+eId+",eName="+eName+",firm=("+firm+"),cardList=("+card+") ]";
    }
}
/**
 *employee表：eid.ename,fid
 * firm表：fid,fname
 * card表：cid.cname,eid
 */

