package com.example.ems.service;

import com.example.ems.payload.TaskDto;

import java.util.List;

public interface TaskService {
    TaskDto createTask(TaskDto taskDto, long userId);
    TaskDto getTaskById(long taskId);
    List<TaskDto> getTasksByUser(long userId);
    List<TaskDto> getAllTasks();
    void deleteTask(long taskId);
    TaskDto updateTask(TaskDto taskDto, long taskId);
}
