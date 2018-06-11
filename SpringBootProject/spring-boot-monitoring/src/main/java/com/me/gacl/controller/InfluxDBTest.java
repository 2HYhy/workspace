package com.me.gacl.controller;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @author CH-yfy
 * @date 2018/6/4
 */
public class InfluxDBTest {

    private RestTemplate template = new RestTemplate();

    public static void main(String [] args) {
        new InfluxDBTest().createDb();
        new InfluxDBTest().writeData();
        new InfluxDBTest().writeMultiData();
    }

    private void createDb() {
        String url = "http://localhost:8086/query";
        MultiValueMap<String, String> postParameter = new LinkedMultiValueMap<>();
        postParameter.add("q", "create database invoice_center");
        template.postForObject(url, postParameter, Object.class);
    }

    private void writeData() {
        //RestTemplate template = new RestTemplate();
        String url = "http://localhost:8086/write?db=invoice_center";
        String pointValue = "monday,host=localhost,region=monitoring value=1006";
        template.postForObject(url, pointValue, Object.class);
    }

    private void writeMultiData() {
        String url = "http://localhost:8086/write?db=invoice_center";
        //每条记录用\n隔开，表名可不同
        String pointValue = "monday,host=10.1.20.30,region=creating value=1007 \n monday,host=10.1.30.40,region=deleting value=1008";
        template.postForObject(url, pointValue, Object.class);
    }
}
