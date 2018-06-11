package com.me.gacl.controller;

import com.me.gacl.IRestClient;
import org.influxdb.dto.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author CH-yfy
 * @date 2018/5/28
 */
@RestController
public class WrittenController {

    @Autowired
    private IRestClient restClient;

    @Autowired
    private InfluxDBTemplate<Point> influxDBTemplate;

    public WrittenController(){
        influxDBTemplate = new InfluxDBTemplate<>();
    }

    @RequestMapping("/influx/add")
    public void write() {
        HashMap<String, String> tags = new HashMap<>(2);
        tags.put("serviceName", "micrometer");
        tags.put("serviceUri", "/actuator/prometheus");

        HashMap<String, Object> fields = new HashMap<>();
        try{
            List<String> results = restClient.get("http://localhost:8899/actuator/prometheus", List.class);
            if (results != null && results.size() > 0 ) {
                for(String temp : results) {
                    if (temp != null) {
                        String [] array = temp.split(" ");
                        fields.put(array[0], array[1]);
                        System.out.println("fields = "+ fields);
                    }
                }
            } else {
                System.out.println("uri请求获取内容为空>>>>>>");
                throw new Exception("request fail");
            }

            //数据写入influxDB
            influxDBTemplate.createDatabase();
            Point point = Point.measurement("actuator_prometheus")
                    .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                    .tag(tags)
                    .fields(fields)
                    .build();
            influxDBTemplate.write(point);
        }catch(Exception e) {
            System.out.println(">>>"+e.getMessage());
            e.printStackTrace();
        }
    }
}
