package com.example.demo.controller;

import com.example.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class HomeController {

    private final TaskService taskService;

    @Autowired
    public HomeController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String indexPage() {
        return "index";
    }

    @GetMapping("/home")
    public ModelAndView homePage(ModelAndView modelAndView, Principal principal) {

        String username = principal.getName();
        modelAndView.addObject("tasks", this.taskService.findAll(username));
        modelAndView.setViewName("home");
        return modelAndView;
    }
}