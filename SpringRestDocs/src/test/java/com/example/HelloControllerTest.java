package com.example;

import com.example.controller.HelloController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author CH-yfy
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
//@DirtiesContext
//以上两注释等同于@WebMvcTest
@WebMvcTest(HelloController.class)
//@AutoConfigureRestDocs("target/generated-snippets")
public class HelloControllerTest {
    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");
    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    HelloController controller;
    @Autowired
    ObjectMapper objectMapper;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).
                apply(documentationConfiguration(this.restDocumentation)).build();
    }

    @Test
    public void testOne() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/hello/info")
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andDo(document("document-1", preprocessResponse(prettyPrint())))
                .andReturn();
    }

    @Test
    public void testTwo() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get( "/hello/info")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("document-2", responseFields(fieldWithPath("username").description("姓名"),
                        fieldWithPath("address").ignored(), //fieldWithPath("address").description("Address"),
                        fieldWithPath("company").description("公司"))))
                .andReturn();
        System.out.println("result= "+result);
    }

    @Test
    public void testThree() throws Exception {
        Map<String, Object> info = new HashMap<>();
        info.put("username", "NanNan");
        info.put("address", "浙江金华");
        info.put("company", "Small Valley");
        this.mockMvc.perform(MockMvcRequestBuilders.post("/hello/save")
                .contentType("application/json").content(objectMapper.writeValueAsString(info)))
                .andExpect(status().isOk())
                .andDo(document("document-3", responseFields(
                        fieldWithPath("username").type("String").description("姓名"),
                        fieldWithPath("address").type("String").description("地址"),
                        fieldWithPath("company").description("公司")
                )));
    }

    @Test
    public void testFour() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("address", "山东济南");
        this.mockMvc.perform(MockMvcRequestBuilders.get("/hello/get/{name}", "Linda").params(params))
                .andExpect(status().isOk())
                .andDo(document("document-4", responseFields(
                        fieldWithPath("username").type("String").description("姓名"),
                        fieldWithPath("address").type("String").description("地址"),
                        fieldWithPath("company").type("String").description("公司")
                )));
    }
}