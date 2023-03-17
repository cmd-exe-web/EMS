package com.example.ems.service.impl;

import com.example.ems.exceptions.ResourceNotFoundException;
import com.example.ems.model.Leave;
import com.example.ems.model.User;
import com.example.ems.payload.LeaveDto;
import com.example.ems.repository.LeaveRepository;
import com.example.ems.repository.UserRepository;
import com.example.ems.service.LeaveService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    private LeaveRepository leaveRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;

    @Override
    public LeaveDto createLeave(LeaveDto leaveDto, long userId) {
        //to add the userId reference
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        Leave leave = modelMapper.map(leaveDto, Leave.class);
        leave.setStatus("Not approved");
        leave.setUser(user);

        Leave newLeave = leaveRepository.save(leave);

        return modelMapper.map(newLeave, LeaveDto.class);
    }

    @Override
    public Leave updateLeave(LeaveDto leaveDto, long leaveId) {
        return null;
    }

    @Override
    public void deleteLeave(long leaveId) {

    }

    @Override
    public List<Leave> getAllLeaves() {
        return null;
    }

    @Override
    public Leave getLeaveById(long leaveId) {
        return null;
    }

    @Override
    public List<LeaveDto> getLeavesByUser(long userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));

        List<Leave> leaves = leaveRepository.findByUser(user);

        List<LeaveDto> leaveDtos = leaves.stream().map((leave) -> modelMapper.map(leave, LeaveDto.class)).collect(Collectors.toList());

        return leaveDtos;
    }
}
