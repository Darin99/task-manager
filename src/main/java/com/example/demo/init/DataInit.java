package com.example.demo.init;

import com.example.demo.service.ClassificationService;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInit implements CommandLineRunner {

    private final ClassificationService classificationService;
    private final RoleService roleService;

    @Autowired
    public DataInit(ClassificationService classificationService, RoleService roleService) {
        this.classificationService = classificationService;
        this.roleService = roleService;
    }

    @Override
    public void run(String... args) {
        this.classificationService.init();
        this.roleService.saveRolesToDb();
    }
}