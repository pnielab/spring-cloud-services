package com.tikal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Created by Pniel abramovich
 */
@SpringBootApplication(exclude = {RabbitAutoConfiguration.class})
@EnableConfigServer
public class ConfigServerBoot {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerBoot.class, args);
    }


}
