package com.example.ontology.tool.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;


@Configuration
@EnableMongoRepositories
@Import(OntologyToolDemoApplication.class)
public class CommonTestConfig {
    @Bean
    public RestTemplate restTemplate() {
        return Mockito.mock(RestTemplate.class);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
//    @Container
//    public static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest")
//            .withExposedPorts(27017)
//            .withEnv("MONGO_INITDB_ROOT_USERNAME", "admin")
//            .withEnv("MONGO_INITDB_ROOT_PASSWORD", "admin");
//
//    static {
//        mongoDBContainer.start();
//    }
}
