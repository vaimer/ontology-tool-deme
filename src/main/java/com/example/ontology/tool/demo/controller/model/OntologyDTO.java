package com.example.ontology.tool.demo.controller.model;

import java.util.List;

public record OntologyDTO(String id,
                          String title,
                          String description,
                          List<String> definitionProperties,
                          List<String> synonymProperties)
{}
