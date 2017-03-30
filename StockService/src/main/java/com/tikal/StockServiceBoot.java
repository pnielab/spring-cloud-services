package com.tikal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by Pniel Abramovich on 3/19/2017.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class StockServiceBoot {

    public static void main(String[] args) {
        SpringApplication.run(StockServiceBoot.class, args);
    }
}
