package com.me.gacl.controller;

import com.me.gacl.core.HealthCore;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author CH-yfy
 * @date 2018/6/1
 * 获取写入influxdb的数据
 */
@RestController
public class TestInfluxController {

    @Autowired
    private InfluxDBTemplate<Point> influxDBTemplate;
    @Autowired
    HealthCore core;

    @RequestMapping("/influx/get")
    public String getHealth() {
        final Query query = new Query("select * from my_info", influxDBTemplate.getDatabase());
        QueryResult results = influxDBTemplate.query(query);
        List<QueryResult.Result> list = results.getResults();
        String seriesName=null;
        for (QueryResult.Result result : list) {
            List<QueryResult.Series> l = result.getSeries();
            for (QueryResult.Series series : l) {
                seriesName = series.getName();
                System.out.println("Measurement Name: " + seriesName);
                Map<String, String> map = series.getTags();
                if (map != null) {
                    for (Map.Entry<String, String> entry : map.entrySet()) {
                        System.out.println("tag: " + entry.getKey() + ", value: " + entry.getValue());
                    }
                }
                List<String> columns = series.getColumns();
                for (String column : columns) {
                    System.out.println("column name: " + column);
                }
                List<List<Object>> values = series.getValues();
                for (List<Object> ll : values) {
                    for (Object obj : ll) {
                        System.out.println("Object type: " + obj.getClass() + ", value: " + String.valueOf(obj));
                    }
                }
            }
        }
        return seriesName;
    }
}
