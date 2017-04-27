package com.tikal.configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Pniel abramovich
 */
@Configuration
@Profile("vertx-springLoadBalancer")
public class SpringHttpClientConfiguration {

    @Bean
    @LoadBalanced
    public RestTemplate loadBalancer(){
        return new RestTemplate();
    }





}
