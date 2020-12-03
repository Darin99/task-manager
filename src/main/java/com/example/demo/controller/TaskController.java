package com.example.demo.controller;

import com.example.demo.model.binding.AddTaskBindingModel;
import com.example.demo.model.binding.ChangeTaskProgressBindingModel;
import com.example.demo.model.service.ChangeTaskProgressServiceModel;
import com.example.demo.model.service.AddTaskServiceModel;
import com.example.demo.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final ModelMapper modelMapper;
    private final HttpSession httpSession;

    @Autowired
    public TaskController(TaskService taskService, ModelMapper modelMapper, HttpSession httpSession) {
        this.taskService = taskService;
        this.modelMapper = modelMapper;
        this.httpSession = httpSession;
    }

    @GetMapping("/add")
    public String addTask(Model model) {
        if (!model.containsAttribute("addTaskBindingModel")) {
            model.addAttribute("addTaskBindingModel", new AddTaskBindingModel());
        }
        return "add-task";
    }

    @PostMapping("/add")
    public String addTaskPost(@Valid @ModelAttribute("addTaskBindingModel") AddTaskBindingModel addTaskBindingModel
            , BindingResult bindingResult, RedirectAttributes redirectAttributes, Principal principal) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addTaskBindingModel", addTaskBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addTaskBindingModel", bindingResult);
            return "redirect:add";
        }

        String username = principal.getName();
        this.taskService.addTask(this.modelMapper.map(addTaskBindingModel, AddTaskServiceModel.class), username);
        return "redirect:/home";
    }

    @GetMapping("/change-progress/{id}")
    public String changeProgress(@PathVariable(value = "id") String id) {
        this.httpSession.setAttribute("id", id);
        return "change-progress-task";
    }

    @PostMapping("/change-progress")
    public String changeProgressPost(@ModelAttribute("changeTaskProgressBindingModel")
                                             ChangeTaskProgressBindingModel changeTaskProgressBindingModel) {

        String id = (String) this.httpSession.getAttribute("id");
        this.taskService.changeProgress(
                this.modelMapper.map(changeTaskProgressBindingModel, ChangeTaskProgressServiceModel.class), id);
        return "redirect:/home";
    }
}