package com.yys.szcp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class SzcpApplication {

    public static void main(String[] args) {
        SpringApplication.run(SzcpApplication.class, args);
    }

}
