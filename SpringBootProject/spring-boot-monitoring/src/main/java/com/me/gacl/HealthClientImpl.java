package com.me.gacl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author CH-yfy
 * @date 2018/6/1
 */
@Service
public class HealthClientImpl implements IHealthClient {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    RestTemplate restTemplate;

    public HealthClientImpl() {
        restTemplate = new RestTemplate();
    }
    @Override
    public <T> T get(String uri, Class<T> responseType) throws Exception {
        ResponseEntity<String> responseEntity;
        T result = null;
        try {
            responseEntity = restTemplate.getForEntity(uri,String.class) ;
            if(responseEntity.getStatusCode().is2xxSuccessful()){
                logger.info("Successful response code: " + responseEntity.getStatusCode() +" on " + uri);
                ObjectMapper mapper = new ObjectMapper();
                result = mapper.readValue(responseEntity.getBody(),responseType);
            }
        }  catch (Exception e) {
                throw new Exception("Could not connect with the Api");
        }
        return result ;
    }
}