package com.tikal.configuration;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.Vertx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

/**
 * Created by pniel abramovich
 */
@Profile("vertx")
@Configuration
@ComponentScan("com.tikal.*")
public class VertxConfiguration {

    @Autowired
    private Environment env;

    private static Vertx vertx = Vertx.vertx();

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper(new JsonFactory());
    }

    @Bean
    public Vertx getVertxInstance() {
        return vertx;
    }

    @Bean
    @LoadBalanced
    public RestTemplate loadBalancer() {
        return new RestTemplate();
    }

    public int getHttpPort() {
        return env.getProperty("http.port", Integer.class, 8002);
    }
}
