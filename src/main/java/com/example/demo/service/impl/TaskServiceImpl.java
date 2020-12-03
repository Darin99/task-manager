package com.example.demo.service.impl;

import com.example.demo.model.entity.Classification;
import com.example.demo.model.entity.Progress;
import com.example.demo.model.entity.Task;
import com.example.demo.model.entity.User;
import com.example.demo.model.service.ChangeTaskProgressServiceModel;
import com.example.demo.model.service.AddTaskServiceModel;
import com.example.demo.model.view.TaskViewModel;
import com.example.demo.repository.TaskRepository;
import com.example.demo.service.ClassificationService;
import com.example.demo.service.TaskService;
import com.example.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;
    private final ClassificationService classificationService;
    private final UserService userService;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, ModelMapper modelMapper, ClassificationService classificationService, UserService userService) {
        this.taskRepository = taskRepository;
        this.modelMapper = modelMapper;
        this.classificationService = classificationService;
        this.userService = userService;
    }

    @Override
    public void addTask(AddTaskServiceModel addTaskServiceModel, String username) {
        Task task = this.modelMapper.map(addTaskServiceModel, Task.class);
        Classification classification = this.classificationService.findByClassificationName(addTaskServiceModel.getClassificationName());
        task.setClassification(classification);
        User user = this.modelMapper.map(this.userService.findByUsername(username), User.class);
        task.setUser(user);
        this.taskRepository.saveAndFlush(task);
    }

    @Override
    public List<TaskViewModel> findAll(String username) {

        return this.taskRepository.findAllByUserUsername(username).stream().map(task -> {
            TaskViewModel taskViewModel = this.modelMapper.map(task, TaskViewModel.class);
            taskViewModel.setUsername(task.getUser().getUsername());
            return taskViewModel;
        }).collect(Collectors.toList());
    }

    @Override
    public void changeProgress(ChangeTaskProgressServiceModel progress, String id) {
        Task task = this.taskRepository.findById(id).orElse(null);
        task.setProgress(Progress.valueOf(progress.getProgress()));
        this.taskRepository.saveAndFlush(task);
    }
}