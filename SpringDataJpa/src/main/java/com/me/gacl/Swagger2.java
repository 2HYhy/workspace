package com.me.gacl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author CH-yfy
 * @date 2017/12/26
 */

@Configuration
@EnableSwagger2
public class Swagger2 {

    @Bean
    public Docket createApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.me.gacl"))
                .paths(PathSelectors.any()).build();
    }


    private ApiInfo apiInfo() {
        //方式一
        return new ApiInfoBuilder()
                .title("Spring Boot利用Swagger自动生成API文档")
                .description("省略手写API文档的步骤，让开发过程更简化")
                .contact("hyh.he")
                .version("1.0")
                .build();
    }

//    private ApiInfo apiInfo() {
//        //方式二
//        Contact contact = new Contact("HYH", "http://hyh.com", "3936123@qq.com");
//        return new ApiInfoBuilder()
//                .title("Spring Boot利用Swagger自动生成API文档")
//                .description("省略手写API文档的步骤，让开发过程更简化")
//                .license("Apache License Version 2.0")
//                .contact(contact)
//                .version("2.0")
//                .build();
//    }

}

////浏览器访问地址: http://localhost:8080/swagger-ui.html