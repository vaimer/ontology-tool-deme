package com.example.ontology.tool.demo.service.model;


import java.util.List;

public record Ontology(String id, String title, String description, List<String> definitionProperties, List<String> synonymProperties)
{}
