package com.example.ems.payload;

import lombok.Data;

@Data
public class TaskDto {
    private long id;
    private String title;
    private String description;
    private String status;
    private UserDto user;
}
