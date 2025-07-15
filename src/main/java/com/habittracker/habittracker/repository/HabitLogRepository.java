package com.habittracker.habittracker.repository;

import com.habittracker.habittracker.model.HabitLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HabitLogRepository extends JpaRepository<HabitLog, Long> {
    List<HabitLog> findByHabitId(Long habitId);

}
