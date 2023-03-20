package com.example.ems.controller;

import com.example.ems.payload.ApiResponse;
import com.example.ems.payload.LeaveDto;
import com.example.ems.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    //create
    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    @PostMapping("/user/{userId}/leaves")
    public ResponseEntity<LeaveDto> createLeave(@RequestBody LeaveDto leaveDto, @PathVariable long userId){
        LeaveDto newLeaveDto = leaveService.createLeave(leaveDto, userId);
        return new ResponseEntity<>(newLeaveDto, HttpStatus.CREATED);
    }

    //get all leaves user id
    @GetMapping("/user/{userId}/leaves")
    public ResponseEntity<List<LeaveDto>> getLeavesByUser(@PathVariable long userId){
        List<LeaveDto> leaves = leaveService.getLeavesByUser(userId);
        return new ResponseEntity<>(leaves, HttpStatus.OK);
    }

    //get all leaves
    @GetMapping("/leaves")
    public ResponseEntity<List<LeaveDto>> getAllLeaves(){
        List<LeaveDto> leaveDtos = leaveService.getAllLeaves();
        return new ResponseEntity<>(leaveDtos, HttpStatus.OK);
    }

    //get leave by leaveId
    @GetMapping("/leaves/{leaveId}")
    public ResponseEntity<LeaveDto> getLeaveById(@PathVariable long leaveId){
        LeaveDto leaveDto = leaveService.getLeaveById(leaveId);
        return new ResponseEntity<>(leaveDto, HttpStatus.OK);
    }

    //delete leave by id
    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    @DeleteMapping("/leaves/{leaveId}")
    public ApiResponse deleteLeave(@PathVariable long leaveId){
        leaveService.deleteLeave(leaveId);
        return new ApiResponse("Leave is succussfuly deleted", true);
    }

    //update leave
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @PutMapping("/leaves/{leaveId}")
    public  ResponseEntity<LeaveDto> updateLeave(@RequestBody LeaveDto leaveDto, @PathVariable long leaveId){
        LeaveDto updatedLeave = leaveService.updateLeave(leaveDto, leaveId);
        return new ResponseEntity<>(updatedLeave, HttpStatus.OK);
    }
}
