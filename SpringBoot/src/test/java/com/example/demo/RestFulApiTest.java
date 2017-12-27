package com.example.demo;

import com.example.demo.bean.UserInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author CH-yfy
 * @date 2017/12/6
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RestFulApiTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc = null;
    RequestBuilder request = null;
    ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testAdd() throws Exception {
        UserInfo user = new UserInfo();
        user.setUid("apple123456");
        user.setRealName("七七");
        user.setProvince("四川省");
        user.setCity("绵阳市");
        mockMvc.perform(post("/user/add")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(user)))
                .andExpect(status().isOk());
    }

    @Test
    public void testGet() throws Exception {
        request = get("/user/get?uid=1234567890");
        mockMvc.perform(request)
                .andExpect(status().isOk());
    }

}
