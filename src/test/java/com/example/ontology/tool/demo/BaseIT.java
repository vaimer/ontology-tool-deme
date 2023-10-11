package com.example.ontology.tool.demo;

import com.example.ontology.tool.demo.repository.OntologyRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.junit.jupiter.Testcontainers;

import static com.example.ontology.tool.demo.TestUtils.olsOntologyMock;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
@ActiveProfiles("test")
//@Testcontainers
@ContextConfiguration(classes = CommonTestConfig.class)
public class BaseIT {

    @Autowired
    protected OntologyRepository ontologyRepository;

    @Autowired
    protected RestTemplate restTemplate;

    @BeforeEach
    void setUpMocks() {
        when(restTemplate.getForObject(anyString(), any(), anyString())).thenReturn(olsOntologyMock);
    }

    @AfterEach
    void cleanUp() {
        this.ontologyRepository.deleteAll();
    }

    protected RestTemplate getRestTemplate() {
        return new RestTemplateBuilder().rootUri("http://localhost:8080").build();
    }
}
