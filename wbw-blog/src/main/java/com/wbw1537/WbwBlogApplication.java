package com.wbw1537;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableScheduling
@EnableSwagger2
//@EnableWebMvc
@MapperScan("com.wbw1537.mapper")
public class WbwBlogApplication {

    public static void main(String[] args) {

        SpringApplication.run(WbwBlogApplication.class, args);
    }

}
