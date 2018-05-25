package com.me.gacl;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author CH-yfy
 * @date 2018/4/25
 */
public class demoTest {

    public static void main(String [] args) {
        //Timestamp -> String
        System.out.println("timestamp now = "+ System.currentTimeMillis());
        Timestamp ts = new Timestamp(4630212);
        String tsStr = "";
        DateFormat sdf = new SimpleDateFormat("HH:mm");  //yyyy/MM/dd HH:mm:ss
        try {
            //方法一
            tsStr = sdf.format(ts);
            System.out.println("1>"+tsStr);
            //方法二
            tsStr = ts.toString();
            System.out.println("2>"+tsStr);
        } catch (Exception e) {
            e.printStackTrace();
        }

          //Timestamp -> Date
        Timestamp ts1 = new Timestamp(1524641794942L);
        Date date = new Date();
        try {
            date = ts1;
            System.out.println("3>"+date);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
