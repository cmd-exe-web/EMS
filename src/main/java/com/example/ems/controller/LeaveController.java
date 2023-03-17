package com.example.ems.controller;

import com.example.ems.model.Leave;
import com.example.ems.payload.LeaveDto;
import com.example.ems.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    //create
    @PostMapping("/user/{userId}/leaves")
    public ResponseEntity<LeaveDto> createLeave(@RequestBody LeaveDto leaveDto, @PathVariable long userId){
        LeaveDto newLeaveDto = leaveService.createLeave(leaveDto, userId);
        return new ResponseEntity<>(newLeaveDto, HttpStatus.CREATED);
    }
}
