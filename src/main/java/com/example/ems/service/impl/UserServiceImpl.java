package com.example.ems.service.impl;

import com.example.ems.exceptions.ResourceNotFoundException;
import com.example.ems.model.User;
import com.example.ems.payload.UserDto;
import com.example.ems.repository.UserRepository;
import com.example.ems.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.dtoToUser(userDto);
        User savedUser = userRepository.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        User updatedUser = userRepository.save(user);
        UserDto userDto1 = userToDto(updatedUser);

        return userDto1;
    }

    @Override
    public UserDto getUserById(long userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));

        return userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();

        List<UserDto> userDtos = users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());

        return userDtos;
    }

    @Override
    public void deleteUser(long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        userRepository.delete(user);
    }

    @Override
    public UserDto assignManager(long userId, long managerId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
        User manager = userRepository.findById(managerId).orElseThrow(() -> new ResourceNotFoundException("Manager", "Id", managerId));
        user.setManager(manager);
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUnassignedEmployees() {
        List<User> unassignedEmployees = userRepository.findByManagerIsNull();
        List<UserDto> employeeDtos = unassignedEmployees.stream().map((employee) -> modelMapper.map(employee, UserDto.class)).collect(Collectors.toList());

        return employeeDtos;
    }

    public User dtoToUser(UserDto userDto){
        User user = modelMapper.map(userDto, User.class);

//        user.setId(user.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());

        return user;
    }

    public UserDto userToDto(User user){
        UserDto userDto = modelMapper.map(user, UserDto.class);

//        userDto.setId(user.getId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword());

        return userDto;
    }

}
