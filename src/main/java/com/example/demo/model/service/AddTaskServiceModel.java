package com.example.demo.model.service;

import com.example.demo.model.entity.ClassificationName;

public class AddTaskServiceModel extends BaseServiceModel {

    private String name;

    private String description;

    private ClassificationName classificationName;

    public AddTaskServiceModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ClassificationName getClassificationName() {
        return classificationName;
    }

    public void setClassificationName(ClassificationName classificationName) {
        this.classificationName = classificationName;
    }
}