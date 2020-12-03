package com.example.demo.service;

import com.example.demo.model.entity.Classification;
import com.example.demo.model.entity.ClassificationName;

public interface ClassificationService {

    void init();

    Classification findByClassificationName(ClassificationName classificationName);
}
