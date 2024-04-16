package com.example.bpr2server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.bpr2server.mapper")
public class Bpr2ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Bpr2ServerApplication.class, args);
    }

}
