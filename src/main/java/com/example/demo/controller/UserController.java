package com.example.demo.controller;

import com.example.demo.model.binding.UserEditBindingModel;
import com.example.demo.model.binding.UserLoginBindingModel;
import com.example.demo.model.binding.UserRegisterBindingModel;
import com.example.demo.model.service.UserServiceModel;
import com.example.demo.model.view.UserProfileViewModel;
import com.example.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/login")
    public String login(Model model) {
        if (!model.containsAttribute("userLoginBindingModel")) {
            model.addAttribute("userLoginBindingModel", new UserLoginBindingModel());
        }
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {

        if (!model.containsAttribute("userRegisterBindingModel")) {
            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
        }

        return "register";
    }

    @PostMapping("/register")
    public String registerPost(@Valid @ModelAttribute("userRegisterBindingModel") UserRegisterBindingModel userRegisterBindingModel
            , BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors() || !userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);
            return "redirect:register";
        }

        this.userService.register(this.modelMapper.map(userRegisterBindingModel, UserServiceModel.class));
        return "redirect:login";
    }

    @GetMapping("/profile/{username}")
    public String userProfile(@PathVariable("username") String username, Model model) {

        model.addAttribute("user", this.modelMapper.map(this.userService.findByUsername(username), UserProfileViewModel.class));
        return "profile";
    }

    @GetMapping("/edit/{username}")
    public String userEditProfile(@PathVariable("username") String username, Model model) {

        if (!model.containsAttribute("userEditBindingModel")) {
            model.addAttribute("userEditBindingModel", new UserEditBindingModel());
        }

        return "edit-profile";
    }

    @PostMapping("/edit")
    public String userEditProfilePost(@Valid @ModelAttribute("userEditBindingModel") UserEditBindingModel userEditBindingModel
            , BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !userEditBindingModel.getPassword().equals(userEditBindingModel.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("userEditBindingModel", userEditBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userEditBindingModel", bindingResult);
            return "redirect:/users/edit/" + userEditBindingModel.getUsername();
        }

        this.userService.editUserProfile(this.modelMapper.map(userEditBindingModel, UserServiceModel.class));
        return "redirect:/users/profile/" + userEditBindingModel.getUsername();
    }
}