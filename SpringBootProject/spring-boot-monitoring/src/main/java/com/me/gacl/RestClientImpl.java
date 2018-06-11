package com.me.gacl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * @author CH-yfy
 * @date 2018/5/28
 */
@Service
public class RestClientImpl implements IRestClient {

    private RestTemplate restTemplate;

    public RestClientImpl() {
        restTemplate = new RestTemplate();
    }

    @Override
    public <T> T get(String uri, Class<T> responseType) throws Exception {
        ResponseEntity<String> response;
        //TODO 数组应该不定长
        String [] finalArrays = new String[80];
        try {
            response = restTemplate.getForEntity(uri, String.class);
            //该接口返回的内容
            String body = response.getBody();
            //按每一行封装成数组
            String [] strings = body.split("\n");
            int j = 0;
            for (int i=0; i<strings.length; i++) {
                String temp = strings[i];
                if (!temp.contains("#")) {
                    //将去掉#的内容重新封装成数组
                    finalArrays[j] = temp;
                    j++;
                }
            }
            //将数组转换为list返回
            List<String> list = Arrays.asList(finalArrays);
            System.out.println("list = "+ list.size());
            return (T) list;
        }catch(Exception e) {
            e.printStackTrace();
            System.out.println(">>>>>>"+e.getMessage());
            return null;
        }
    }
}
