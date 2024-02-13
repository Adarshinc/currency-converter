package com.currencyConverter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

@Slf4j
@SpringBootApplication
public class CurrencyConverterApp {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(CurrencyConverterApp.class, args);
        Environment environment = context.getEnvironment();
//        log.info("Environement variable used is uri: {}", environment.getProperty("spring.data.mongodb.uri"));
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
