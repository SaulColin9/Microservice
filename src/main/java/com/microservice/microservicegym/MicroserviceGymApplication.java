package com.microservice.microservicegym;

import com.microservice.microservicegym.controller.interceptor.CustomHttpInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.web.servlet.handler.MappedInterceptor;

@SpringBootApplication
public class MicroserviceGymApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(MicroserviceGymApplication.class, args);
        MongoTemplate mongoTemplate = ctx.getBean(MongoTemplate.class);
        mongoTemplate.indexOps("trainingWorkloadResponseDTO")
                .ensureIndex(
                        new Index().on("trainer.user.username", Sort.Direction.ASC)
                );
    }

    @Bean
    public MappedInterceptor mappedInterceptor() {
        return new MappedInterceptor(null, new CustomHttpInterceptor());
    }


}
