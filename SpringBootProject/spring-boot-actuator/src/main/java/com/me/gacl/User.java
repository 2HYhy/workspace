package com.me.gacl;

/**
 * @author CH-yfy
 * @date 2018/4/23
 */
public class User {

    private final static String USERNAME = "Jessica";
    private final static Integer AGE = 22;
    private final static String ADDRESS = "山东淄博";

    public static String getUSERNAME() {
        return USERNAME;
    }

    public static Integer getAGE() {
        return AGE;
    }

    public static String getADDRESS() {
        return ADDRESS;
    }
}
