package com.me.gacl.core;

import com.me.gacl.IHealthClient;
import org.influxdb.dto.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.scheduling.annotation.Scheduled;
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
 * TODO 表中数据为空
 */
@Service
public class HealthCore {

    @Value("${healths}")
    private String [] healthArrays;

    @Autowired
    InfluxDBTemplate<Point> influxDBTemplate;
    @Autowired
    IHealthClient healthClient;

    /**
     * 每隔1分钟写入数据库
     */
    @Scheduled(cron = "*/60 * * * * *")
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
                    //value!=String时，进一步解析
                    Map<String, Object> mapSec = (Map) value;
                    Iterator it = mapSec.entrySet().iterator();
                    while(it.hasNext()) {
                        Map.Entry map = (Map.Entry) it.next();
                        Object kk = map.getKey();
                        Object vv = map.getValue();
                        System.out.println("key="+kk+", value="+vv);
                        boolean flag = vv.getClass().getName().equals("java.lang.String");
                        if (!flag) {
                            //value!=String时
                            Map<String, Object> mapThird = (Map) vv;
                            Iterator itt = mapThird.entrySet().iterator();
                            while (itt.hasNext()) {
                                Map.Entry mapp = (Map.Entry) itt.next();
                                Object kkk = mapp.getKey();
                                Object vvv = mapp.getValue();
                                System.out.println("key="+kkk+", value="+vvv);
                                boolean mark = vvv.getClass().getName().equals("java.lang.String");
                                if (!mark) {
                                    //value!=String时
                                    Map<String, Object> mapFour = (Map) vvv;
                                    Iterator ittt = mapFour.entrySet().iterator();
                                    while(ittt.hasNext()) {
                                        Map.Entry mappp = (Map.Entry) ittt.next();
                                        Object kkkk = mappp.getKey();
                                        Object vvvv = mappp.getValue();
                                        System.out.println("key="+kkkk+", value="+vvvv);
                                        //至此已将所有key解析完毕
                                        String finalKey = key + "." + kk + "." + kkk + "." + kkkk;
                                        if(Arrays.asList(healthArrays).contains(finalKey)) {
                                            field.put(finalKey, vvvv);
                                        }
                                    }
                                } else {
                                    //value=String时
                                    String finalKey = key + "." + kk + "." + kkk;
                                    if(Arrays.asList(healthArrays).contains(finalKey)) {
                                        field.put(finalKey, vvv);
                                    }
                                }
                            }
                        } else {
                            //value=String时
                            String finalKey = key + "." + kk;
                            if(Arrays.asList(healthArrays).contains(finalKey)) {
                                field.put(finalKey, vv);
                            }
                        }
                    }
                } else {
                    //value=String时
                    if(Arrays.asList(healthArrays).contains(key)) {
                        field.put(key, value);
                    }
                }
            }
            System.out.println("field = " + field);

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
