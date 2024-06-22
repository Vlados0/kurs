package com.example.cyber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableAspectJAutoProxy
@EnableScheduling
@EnableCaching
@EnableTransactionManagement
@SpringBootApplication
@EnableFeignClients(basePackages = "com.example.cyber.api")
public class CyberApplication {

    public static void main(String[] args) {
        SpringApplication.run(CyberApplication.class, args);
    }

}
