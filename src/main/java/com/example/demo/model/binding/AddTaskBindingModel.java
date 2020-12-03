package com.example.demo.model.binding;

import com.example.demo.model.entity.ClassificationName;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class AddTaskBindingModel {

    @Length(min = 3, max = 20, message = "Name length must be between 1 and 20 characters")
    private String name;

    @Length(min = 3, max = 100, message = "Description length must be between 1 and 100 characters")
    private String description;

    @NotNull(message = "Field cannot me empty")
    private ClassificationName classification;

    public AddTaskBindingModel() {
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

    public ClassificationName getClassification() {
        return classification;
    }

    public void setClassification(ClassificationName classification) {
        this.classification = classification;
    }
}