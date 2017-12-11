package com.example.demo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author yunhua.he
 * @date 2017/12/5
 */
@RunWith(SpringRunner.class)  //1.4版本之前用的是SpringJUnit4ClassRunner.class
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)//1.4版本之前用的是@SpringApplicationConfiguration(class=Application.class)+@WebAppConfiguration
//@SpringBootTest(classes = Application.class)
public class BootControllerTest {

    @LocalServerPort
    private int port;
    private URL base;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:"+ port +"/hyh/boot/");
    }

    @Test
    public void getBoot() throws Exception {
        ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
        assertThat(response.getBody(), equalTo("Greeting, Spring Boot !"));
    }

}
