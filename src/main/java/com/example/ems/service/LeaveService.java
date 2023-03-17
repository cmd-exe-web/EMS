package com.example.ems.service;

import com.example.ems.model.Leave;
import com.example.ems.payload.LeaveDto;

import java.util.List;

public interface LeaveService {
    //create
    LeaveDto createLeave(LeaveDto leaveDto, long userId);
    //update
    Leave updateLeave(LeaveDto leaveDto, long leaveId);
    //delete
    void deleteLeave(long leaveId);
    //get all leaves
    List<Leave> getAllLeaves();

    //get leave by leave_id
    Leave getLeaveById(long leaveId);

    //get all leaves by user
    List<LeaveDto> getLeavesByUser(long userId);
}
