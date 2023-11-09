package com.wbw1537;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wbw1537.mapper")
public class WbwAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(WbwAdminApplication.class, args);
    }

}
