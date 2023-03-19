package com.example.ems.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "`leave`")
@Getter
@Setter
@NoArgsConstructor
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
