package com.me.gacl.core;

import com.me.gacl.IHealthClient;
import org.influxdb.dto.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author CH-yfy
 * @date 2018/5/30
 * 将通过自定义/info获取到的数据写入influxdb
 * TODO 表中数据为空
 */
@Service
public class InfoCore {

    @Value("${infos}")
    private String [] infoArrays;

    @Autowired
    InfluxDBTemplate<Point> influxDBTemplate;
    @Autowired
    IHealthClient healthClient;

    public InfoCore() throws Exception {
    }

    //每50秒刷新一次
    //@Scheduled(cron = "*/50 * * * * *")
    public void run() {
        Map<String, String> tags = new HashMap<>(2);
        tags.put("tagOne", "first");
        tags.put("tagTwo", "second");

        HashMap<String, Object> fields = new HashMap<>(10);
        try {
            HashMap<String, Object> results = healthClient.get("http://127.0.0.1:8899/actuator/info", HashMap.class);
            if (results != null && results.size() > 0) {
                Set set = results.entrySet();
                Iterator iterator = set.iterator();
                while (iterator.hasNext()) {
                    Map.Entry entry = (Map.Entry) iterator.next();
                    if (Arrays.asList(infoArrays).contains(entry.getKey())) {
                        fields.put((String) entry.getKey(), entry.getValue());
                    }
                }
            }
            System.out.println("fields= "+ fields);
            //开始写入数据
            influxDBTemplate.createDatabase();
            Point point = Point.measurement("my_info")
                    .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                    .tag(tags)
                    .fields(fields)
                    .build();
            influxDBTemplate.write(point);
            System.out.println("point=" + point.toString());
            System.out.println(">>>influxDB数据写入完毕>>>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
