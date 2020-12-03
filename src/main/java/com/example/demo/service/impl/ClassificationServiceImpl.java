package com.example.demo.service.impl;

import com.example.demo.model.entity.Classification;
import com.example.demo.model.entity.ClassificationName;
import com.example.demo.repository.ClassificationRepository;
import com.example.demo.service.ClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ClassificationServiceImpl implements ClassificationService {

    private final ClassificationRepository classificationRepository;

    @Autowired
    public ClassificationServiceImpl(ClassificationRepository classificationRepository) {
        this.classificationRepository = classificationRepository;
    }

    @Override
    public void init() {
        if (this.classificationRepository.count() == 0) {
            Arrays.stream(ClassificationName.values()).forEach(classificationName ->
                    this.classificationRepository.save(
                            new Classification(classificationName, String.format("Description for %s", classificationName.name()))));
        }
    }

    @Override
    public Classification findByClassificationName(ClassificationName classificationName) {

        return this.classificationRepository.findByClassificationName(classificationName).orElse(null);
    }
}