package com.example.ontology.tool.demo.repository.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("ontology")
public record OntologyEntity(@Id String id,
                            String title,
                            String description,
                            List<String> definitionProperties,
                            List<String> synonymProperties)
{}
