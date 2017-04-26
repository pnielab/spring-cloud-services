package com.tikal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Pniel Abramovich
 */
@SpringBootApplication
@EnableEurekaClient
public class TraideServiceBoot {

    public static void main(String[] args) {
        SpringApplication.run(TraideServiceBoot.class, args);
    }
}
