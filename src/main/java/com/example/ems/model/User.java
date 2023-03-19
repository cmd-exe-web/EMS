package com.example.ems.model;

import com.example.ems.model.enums.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String name;
    private String email;
    private String password;
    //the "user" below in mappedBy refers to the "user" variable in the Leave class
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Leave> leaves = new ArrayList<>();
    //the user below in mappedBy refers to the "user" reference variable in the Task class
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Task> tasks = new ArrayList<>();
    private Role role;
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private User manager;
}
