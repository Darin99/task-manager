package com.example.demo.service.impl;

import com.example.demo.model.entity.Role;
import com.example.demo.model.entity.User;
import com.example.demo.model.service.UserServiceModel;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, BCryptPasswordEncoder passwordEncoder, RoleService roleService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @Override
    public void register(UserServiceModel userServiceModel) {

        User user = this.modelMapper.map(userServiceModel, User.class);
        user.setPassword(this.passwordEncoder.encode(userServiceModel.getPassword()));
        if (this.userRepository.count() == 0) {
            user.setAuthorities(this.roleService.findAll().stream()
                    .map(r -> this.modelMapper.map(r, Role.class)).collect(Collectors.toSet()));
        } else {
            user.setAuthorities(Set.of(this.modelMapper.map(this.roleService.findByAuthority("ROLE_USER"), Role.class)));
        }
        this.userRepository.saveAndFlush(user);
    }

    @Override
    public UserServiceModel findByUsername(String username) {
        return this.modelMapper.map(this.userRepository.findByUsername(username).orElse(null), UserServiceModel.class);
    }

    @Override
    public void editUserProfile(UserServiceModel userServiceModel) {
        User user = this.userRepository.findByUsername(userServiceModel.getUsername()).orElse(null);

        user.setPassword(this.passwordEncoder.encode(userServiceModel.getPassword()));
        user.setEmail(userServiceModel.getEmail());

        this.userRepository.saveAndFlush(user);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        return this.userRepository.findByEmail(s).orElse(null);
    }
}