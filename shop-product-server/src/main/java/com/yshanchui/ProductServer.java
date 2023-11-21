package com.yshanchui;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.yshanchui.mapper")
@SpringBootApplication
public class ProductServer {

    public static void main(String[] args) {
        SpringApplication.run(ProductServer.class,args);
    }
}
