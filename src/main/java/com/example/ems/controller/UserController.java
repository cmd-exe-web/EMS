package com.example.ems.controller;

import com.example.ems.payload.ApiResponse;
import com.example.ems.payload.UserDto;
import com.example.ems.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    //Create user
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto createdUserDto = userService.createUser(userDto);
        return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
    }
    //Update user
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable long userId){
        UserDto updatedUser = userService.updateUser(userDto, userId);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
    //Delete user
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>(new ApiResponse("User deleted successfully", true), HttpStatus.OK);
    }

    //Get user
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable long userId){
        return ResponseEntity.ok(userService.getUserById(userId));
    }

}
