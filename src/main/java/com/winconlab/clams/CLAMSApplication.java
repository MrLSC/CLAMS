package com.winconlab.clams;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.winconlab.clams.mapper")
public class CLAMSApplication {

    public static void main(String[] args) {
        SpringApplication.run(CLAMSApplication.class, args);
    }
}
