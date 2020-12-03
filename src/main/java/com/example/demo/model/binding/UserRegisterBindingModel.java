package com.example.demo.model.binding;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class UserRegisterBindingModel {

    @Length(min = 3, max = 20, message = "Username length must be between 3 and 20 characters")
    private String username;

    @Email(message = "enter valid email!")
    @NotNull(message = "email cannot be null!")
    private String email;

    @Length(min = 3, max = 20, message = "Password length must be between 3 and 20 characters")
    private String password;

    private String confirmPassword;

    public UserRegisterBindingModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Email(message = "Enter valid email")
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}