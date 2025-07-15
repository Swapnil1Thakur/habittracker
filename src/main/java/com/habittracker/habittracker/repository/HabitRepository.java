package com.habittracker.habittracker.repository;

import com.habittracker.habittracker.model.Habit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HabitRepository extends JpaRepository<Habit, Long> {
    List<Habit> findByUserId(Long userId);


}
