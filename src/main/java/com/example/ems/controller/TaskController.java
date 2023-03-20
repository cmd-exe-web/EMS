package com.example.ems.controller;

import com.example.ems.payload.ApiResponse;
import com.example.ems.payload.TaskDto;
import com.example.ems.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskController {

    @Autowired
    private TaskService taskService;

    //create task
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @PostMapping("/user/{userId}/tasks")
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto, @PathVariable long userId){
        TaskDto createdTaskDto = taskService.createTask(taskDto, userId);
        return new ResponseEntity<>(createdTaskDto, HttpStatus.CREATED);
    }

    //delete task by id
    @DeleteMapping("/tasks/{taskId}")
    public ResponseEntity<ApiResponse> deleteTask(@PathVariable long taskId){
        taskService.deleteTask(taskId);
        return new ResponseEntity<>(new ApiResponse("Task deleted successfully!", true), HttpStatus.OK);
    }

    //get all tasks
    @GetMapping("/tasks")
    public ResponseEntity<List<TaskDto>> getAllTasks(){
        List<TaskDto> allTasks = taskService.getAllTasks();
        return new ResponseEntity<>(allTasks, HttpStatus.OK);
    }

    //get all tasks by user id
    @GetMapping("/user/{userId}/tasks")
    public ResponseEntity<List<TaskDto>> getTasksByUser(@PathVariable long userId){
        List<TaskDto> tasksByUser = taskService.getTasksByUser(userId);
        return new ResponseEntity<>(tasksByUser, HttpStatus.OK);
    }
    //get task by id
    @GetMapping("/tasks/{taskId}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable long taskId){
        TaskDto taskDto = taskService.getTaskById(taskId);
        return new ResponseEntity<>(taskDto, HttpStatus.OK);
    }

    //update task
    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    @PutMapping("/tasks/{taskId}")
    public ResponseEntity<TaskDto> updateTask(@RequestBody TaskDto taskDto, @PathVariable long taskId){
        TaskDto updatedTask = taskService.updateTask(taskDto, taskId);
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }
}
