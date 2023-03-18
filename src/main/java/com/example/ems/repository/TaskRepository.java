package com.example.ems.repository;

import com.example.ems.model.Task;
import com.example.ems.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(User userId);
}
