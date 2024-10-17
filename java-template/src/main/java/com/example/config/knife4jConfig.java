package com.example.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@EnableKnife4j
@Configuration
public class knife4jConfig {

    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(new ApiInfoBuilder()
                        .title("后台管理系统")
                        .description("后台管理系统API接口文档")
                        .termsOfServiceUrl("127.0.0.1")
                        .contact(new Contact("作者本人",":8080","1111111111@qq.com"))
                        .version("1.0")
                        .build())
                .groupName("开发环境");
    }

}
