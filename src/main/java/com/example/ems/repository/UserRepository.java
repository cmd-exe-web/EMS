package com.example.ems.repository;

import com.example.ems.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByManagerIsNull();
}
