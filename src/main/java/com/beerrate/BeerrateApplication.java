package com.beerrate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BeerrateApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeerrateApplication.class, args);
    }

}
