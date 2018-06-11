package com.me.gacl.core;

import com.me.gacl.IHealthClient;
import org.influxdb.dto.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author CH-yfy
 * @date 2018/5/30
 * 将通过/health获取到的数据写入influxdb
 * TODO 写入格式修改(06-04)
 */
@Service
public class HealthCore {

    @Value("${infos}")
    private String [] healthArrays;

    @Autowired
    InfluxDBTemplate<Point> influxDBTemplate;
    @Autowired
    IHealthClient healthClient;

    //@Scheduled(cron = "*/20 * * * * *")
    public void run() {
        Map<String, String> tag = new HashMap<>(20);
        tag.put("endpoint", "health");

        Map<String, Object> field = new HashMap<>(20);
        try {
            HashMap<String, Object> mapRes = healthClient.get("http://127.0.0.1:8899/actuator/health", HashMap.class);
            Iterator iterator = mapRes.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry)iterator.next();
                String key = entry.getKey().toString();
                Object value = entry.getValue();
                System.out.println("key="+key+", value="+value);
                //value是否为String
                boolean bool = value.getClass().getName().equals("java.lang.String");
                if (!bool) {
                    //value=String时，进一步解析
                    Map<String, Object> mapSec = (Map) value;
                    Iterator it = mapSec.entrySet().iterator();
                    while(it.hasNext()) {
                        Map.Entry map = (Map.Entry) it.next();
                        Object kk = map.getKey();
                        Object vv = map.getValue();
                        String finalKey = key+"."+kk;
                        if(Arrays.asList(healthArrays).contains(finalKey)) {
                            field.put(finalKey, vv);
                        }
                    }
                } else {
                    //value不是String时
                    if(Arrays.asList(healthArrays).contains(key)) {
                        field.put(key, value);
                    }
                }
            }
            //创建数据库及表
            influxDBTemplate.createDatabase();
            Point point = Point.measurement("my_health")
                    .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                    .tag(tag)
                    .fields(field)
                    .build();
            influxDBTemplate.write(point);
            System.out.println(">>>influxDB数据写入完毕>>>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
