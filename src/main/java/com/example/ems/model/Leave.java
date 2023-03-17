package com.example.ems.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "`leave`")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Leave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "`date`")
    private LocalDate date;
    private String status;
    @ManyToOne
    private User user;

}
