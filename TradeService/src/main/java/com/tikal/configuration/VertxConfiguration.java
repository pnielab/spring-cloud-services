package com.tikal.configuration;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

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
    public HttpClient httpClient() {
        return vertx.createHttpClient();
    }

    public int getHttpPort() {
        return env.getProperty("http.port", Integer.class, 8002);
    }
}
