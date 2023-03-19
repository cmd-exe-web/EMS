package com.example.ems.security;

import com.example.ems.exceptions.ResourceNotFoundException;
import com.example.ems.model.User;
import com.example.ems.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //load user from user DB
        User user = userRepository.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("User", "email", username));

        return user;
    }
}
