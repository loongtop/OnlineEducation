package com.gkhy.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.gkhy"})
public class EduApplication {

    public static void main(String[] args) throws Exception{
        SpringApplication.run(EduApplication.class, args);
        System.out.println("EduApplication starting..........");
    }
}

