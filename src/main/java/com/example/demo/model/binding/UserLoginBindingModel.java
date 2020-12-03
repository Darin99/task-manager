package com.example.demo.model.binding;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class UserLoginBindingModel {

    @Email(message = "enter valid email!")
    @NotNull(message = "email cannot be null!")
    private String email;

    @Length(min = 3, max = 20, message = "Password length must be between 3 and 20 characters")
    private String password;

    public UserLoginBindingModel() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}