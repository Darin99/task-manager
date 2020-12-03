package com.example.demo.service;

import com.example.demo.model.service.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    void register(UserServiceModel userServiceModel);

    UserServiceModel findByUsername(String username);

    void editUserProfile(UserServiceModel userServiceModel);
}