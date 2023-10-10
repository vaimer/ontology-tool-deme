package com.example.ontology.tool.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class OntologyToolDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(OntologyToolDemoApplication.class, args);
	}
}
