package com.example.demo.model.view;

import com.example.demo.model.entity.ClassificationName;

public class TaskViewModel {

    private String id;
    private String name;
    private String username;
    private ClassificationName classificationName;
    private String progress;

    public TaskViewModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ClassificationName getClassificationName() {
        return classificationName;
    }

    public void setClassificationName(ClassificationName classificationName) {
        this.classificationName = classificationName;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }
}