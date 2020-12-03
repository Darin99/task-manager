package com.example.demo.service.impl;

import com.example.demo.model.entity.Role;
import com.example.demo.model.service.RoleServiceModel;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveRolesToDb() {
        if (roleRepository.count() == 0) {
            this.roleRepository.saveAndFlush(new Role("ROLE_ROOT"));
            this.roleRepository.saveAndFlush(new Role("ROLE_ADMIN"));
            this.roleRepository.saveAndFlush(new Role("ROLE_USER"));
        }
    }

    @Override
    public Set<RoleServiceModel> findAll() {
        return this.roleRepository.findAll().stream()
                .map(r -> this.modelMapper.map(r, RoleServiceModel.class)).collect(Collectors.toSet());
    }

    @Override
    public RoleServiceModel findByAuthority(String authority) {

        Role role = this.roleRepository.findByAuthority(authority).orElse(null);
        return this.modelMapper.map(role, RoleServiceModel.class);
    }
}