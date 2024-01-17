package com.microservice.microservicegym.configuration;

//import com.mongodb.ConnectionString;
//import com.mongodb.MongoClientSettings;
//import com.mongodb.client.MongoClient;
//import com.mongodb.client.MongoClients;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
//import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
//
//import java.util.Collection;
//import java.util.Collections;

//@EnableMongoRepositories(basePackages = "com.microservice.microservicegym.repository")
//@Configuration
//public class MongoConfig extends AbstractMongoClientConfiguration {
//    @Override
//    protected String getDatabaseName() {
//        return "gym";
//    }
//
//    @Override
//    public MongoClient mongoClient() {
//        ConnectionString connectionString = new ConnectionString("mongodb+srv://saulcolins:1234@cluster0.swxjxng.mongodb.net/\n");
//        MongoClientSettings mongoClientSettings = MongoClientSettings.builder().applyConnectionString(connectionString).build();
//        return MongoClients.create(mongoClientSettings);
//    }
//
//    @Override
//    protected Collection getMappingBasePackages() {
//        return Collections.singleton("com.microservice.microservicegym");
//    }
//
//}
