package com.example.demo.service;

import com.example.demo.model.service.ChangeTaskProgressServiceModel;
import com.example.demo.model.service.AddTaskServiceModel;
import com.example.demo.model.view.TaskViewModel;

import java.util.List;

public interface TaskService {
    void addTask(AddTaskServiceModel addTaskServiceModel, String username);

    List<TaskViewModel> findAll(String username);

    void changeProgress(ChangeTaskProgressServiceModel progress, String id);
}