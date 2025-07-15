package com.habittracker.habittracker.controller;

import com.habittracker.habittracker.model.Habit;
import com.habittracker.habittracker.model.HabitLog;
import com.habittracker.habittracker.repository.HabitLogRepository;
import com.habittracker.habittracker.repository.HabitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/logs")
public class HabitLogController {

    @Autowired
    private HabitRepository habitRepository;

    @Autowired
    private HabitLogRepository habitLogRepository;

    // ✅ Log a habit done
    @PostMapping("/{habitId}")
    public HabitLog logHabit(@PathVariable Long habitId) {
        Habit habit = habitRepository.findById(habitId)
                .orElseThrow(() -> new RuntimeException("Habit not found"));

        HabitLog log = new HabitLog();
        log.setHabit(habit);
        log.setDate(LocalDate.now());

        return habitLogRepository.save(log);
    }

    // ✅ View logs by habit
    @GetMapping("/{habitId}")
    public List<HabitLog> getLogs(@PathVariable Long habitId) {
        return habitLogRepository.findByHabitId(habitId);
    }
}
