package com.tikal.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

/**
 * Created by pniel abramovich
 */
@Profile("servlet-container")
@Configuration
public class ConfigrationBeans {

    @Bean
    @LoadBalanced
    public RestTemplate loadBalancer() {
        return new RestTemplate();
    }


}
