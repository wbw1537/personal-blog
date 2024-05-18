package com.wbw1537.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
  @Bean
  public Docket customDocket() {
    return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.wbw1537.controller"))
            .build();
  }

  private ApiInfo apiInfo() {
    Contact contact = new Contact("wbw1537", "http://wbw1537.cyou", "wbw1537@outlook.com");
    return new ApiInfoBuilder()
            .title("Personal Blog APIs")
            .description("This is a blog project for personal use.")
            .contact(contact)   // 联系方式
            .version("1.1.0")  // 版本
            .build();
  }
}
