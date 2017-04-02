package com.tikal.configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by pniel abramovich
 */
@Configuration
public class ConfigrationBeans {

    @Bean
    @LoadBalanced
    public RestTemplate loadBalancer() {
        return new RestTemplate();
    }


}
