package com.example.ems.service.impl;

import com.example.ems.exceptions.ResourceNotFoundException;
import com.example.ems.model.Task;
import com.example.ems.model.User;
import com.example.ems.payload.TaskDto;
import com.example.ems.repository.TaskRepository;
import com.example.ems.repository.UserRepository;
import com.example.ems.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public TaskDto createTask(TaskDto taskDto, long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        Task task = modelMapper.map(taskDto, Task.class);

        task.setStatus("Not completed");
        task.setUser(user);

        Task savedTask = taskRepository.save(task);

        return modelMapper.map(savedTask, TaskDto.class);
    }

    @Override
    public TaskDto getTaskById(long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new ResourceNotFoundException("Task", "taskId", taskId));
        return modelMapper.map(task, TaskDto.class);
    }

    @Override
    public List<TaskDto> getTasksByUser(long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));

        List<Task> tasks = taskRepository.findByUser(user);

        List<TaskDto> taskDtos = tasks.stream().map((task) -> modelMapper.map(task, TaskDto.class)).collect(Collectors.toList());

        return taskDtos;
    }

    @Override
    public List<TaskDto> getAllTasks() {
        List<Task> allTasks = taskRepository.findAll();

        List<TaskDto> taskDtos = allTasks.stream().map((task) -> modelMapper.map(task, TaskDto.class)).collect(Collectors.toList());

        return taskDtos;
    }

    @Override
    public void deleteTask(long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new ResourceNotFoundException("Task", "taskId", taskId));
        taskRepository.delete(task);
    }

    //Only to update the status of the task
    @Override
    public TaskDto updateTask(TaskDto taskDto, long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new ResourceNotFoundException("Task", "taskId", taskId));

        task.setStatus(taskDto.getStatus());

        Task updatedTask = taskRepository.save(task);

        return modelMapper.map(updatedTask, TaskDto.class);
    }
}
