package com.microservice.microservicegym;

import com.microservice.microservicegym.controller.interceptor.CustomHttpInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.servlet.handler.MappedInterceptor;

@SpringBootApplication
public class MicroserviceGymApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceGymApplication.class, args);
    }

    @Bean
    public MappedInterceptor mappedInterceptor() {
        return new MappedInterceptor(null, new CustomHttpInterceptor());
    }


}
