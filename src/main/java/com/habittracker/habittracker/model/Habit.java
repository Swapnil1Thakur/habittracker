package com.habittracker.habittracker.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Habit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private LocalDate startDate;

    private boolean completed;

    // Optional: In future link with User
    // private Long userId;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
