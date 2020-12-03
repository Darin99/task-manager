package com.example.demo.service;

import com.example.demo.model.service.RoleServiceModel;

import java.util.Set;

public interface RoleService {

    void saveRolesToDb();

    Set<RoleServiceModel> findAll();

    RoleServiceModel findByAuthority(String authority);
}