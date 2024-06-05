package com.nmb.sportwear_store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.nmb.sportwear_store")
public class SportwearStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(SportwearStoreApplication.class, args);
    }

}
