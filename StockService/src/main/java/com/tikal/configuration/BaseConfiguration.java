package com.tikal.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Pniel abramovich
 */
@Configuration
public class BaseConfiguration {


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

