package com.example.mockmvc;

import com.example.entity.OrderInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author CH-yfy
 * @date 2018/4/13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderControllerTest {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;
    private static int id = 1;

    @Before
    public void setUp() throws Exception{
        mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void testAdd() throws Exception {
        OrderInfo order = new OrderInfo();
        order.setName("buy top");
        order.setAmount(2);
        order.setTime(new Date());
        mockMvc.perform(post("/order/add")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(order)))
                .andExpect(status().isOk())
                //使用jsonPath解析返回值，判断具体内容
                .andExpect(jsonPath("time", notNullValue()))
                .andExpect(jsonPath("id", notNullValue()))
                .andExpect(jsonPath("name", is("buy top")))
                .andExpect(jsonPath("amount", is(2)));
    }

    @Test
    public void testGet() throws Exception {
        MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
        param.add("name", "buy top");
        mockMvc.perform(get("/order/get/{id}", id).params(param))
                .andExpect(status().isOk())
                .andExpect(jsonPath("amount", is(0)));
    }

    @Test
    public void testUpdate() throws Exception {
        OrderInfo order = new OrderInfo();
        order.setName("buy jeans");
        order.setAmount(6);
        order.setTime(new Date());
        mockMvc.perform(post("/order/update/{id}", id)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(order)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(id)))
                .andExpect(jsonPath("name", is("buy jeans")))
                .andExpect(jsonPath("amount", is(6)));
    }
}
