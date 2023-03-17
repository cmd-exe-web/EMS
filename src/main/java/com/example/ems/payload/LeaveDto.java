package com.example.ems.payload;

import com.example.ems.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class LeaveDto {
    private LocalDate date;
    private String status;
    private UserDto user;

}
