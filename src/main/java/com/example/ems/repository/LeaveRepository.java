package com.example.ems.repository;

import com.example.ems.model.Leave;
import com.example.ems.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveRepository extends JpaRepository<Leave, Long> {
    List<Leave> findByUser(User user);
}
