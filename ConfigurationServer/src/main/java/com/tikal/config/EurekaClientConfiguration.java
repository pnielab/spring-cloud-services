package com.tikal.config;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by Pniel abramovich
 */
@Profile("registration-first")
@Configuration
@EnableDiscoveryClient
public class EurekaClientConfiguration {
}
