package com.wbw1537;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.wbw1537.mapper")
@EnableSwagger2
public class WbwAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(WbwAdminApplication.class, args);
    }

}
