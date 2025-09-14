package com.pooja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class
MovieApplicationLauncher {
    public static void main(String[] args) {
        SpringApplication.run(MovieApplicationLauncher.class, args);
    }

}
