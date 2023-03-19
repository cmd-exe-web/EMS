package com.example.ems.controller;

import com.example.ems.payload.UserDto;
import com.example.ems.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    UserService userService;
    //assign manager
    @PutMapping("/assign-manager")
    public ResponseEntity<UserDto> assignManager(@RequestParam long userId,@RequestParam long managerId){
        UserDto userDto = userService.assignManager(userId, managerId);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
    //list of all unassigned employees
    @GetMapping("/unassigned-employees")
    public ResponseEntity<List<UserDto>> getUnassignedEmployees(){
        List<UserDto> allUnassignedEmployees = userService.getAllUnassignedEmployees();
        return new ResponseEntity<>(allUnassignedEmployees, HttpStatus.OK);
    }

}
