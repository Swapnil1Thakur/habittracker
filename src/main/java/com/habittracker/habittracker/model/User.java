package com.habittracker.habittracker.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users") // 👈 Renamed to avoid using reserved keyword "user"
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String role;
}
