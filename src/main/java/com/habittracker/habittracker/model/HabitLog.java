package com.habittracker.habittracker.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class HabitLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "habit_id")
    private Habit habit;

    // Getters & Setters
    public Long getId() {
        return id;

    }


    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Habit getHabit() {
        return habit;
    }

    public void setHabit(Habit habit) {
        this.habit = habit;
    }
}
