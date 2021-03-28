package com.yys.szcp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class SzcpApplication {

    public static void main(String[] args) {
        SpringApplication.run(SzcpApplication.class, args);
//        //允许特殊符号,本例是 | { } 做入参，也可追加其他符号
//        System.setProperty("tomcat.util.http.parser.HttpParser.requestTargetAllow","|{}[]");
    }

}
