package com.habittracker.habittracker.controller;

import com.habittracker.habittracker.model.Habit;
import com.habittracker.habittracker.model.User;
import com.habittracker.habittracker.repository.HabitRepository;
import com.habittracker.habittracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/habits")
public class HabitController {

    @Autowired
    private HabitRepository habitRepository;

    @Autowired
    private UserRepository userRepository;

    // ✅ Add habit with user
    @PostMapping
    public Habit addHabit(@RequestBody Habit habit) {
        if (habit.getUser() != null && habit.getUser().getId() != null) {
            Optional<User> userOpt = userRepository.findById(habit.getUser().getId());
            if (userOpt.isPresent()) {
                habit.setUser(userOpt.get());
            } else {
                throw new RuntimeException("User not found with ID: " + habit.getUser().getId());
            }
        } else {
            throw new RuntimeException("User ID is required to assign habit");
        }

        habit.setStartDate(LocalDate.now());
        habit.setCompleted(false);
        return habitRepository.save(habit);
    }

    // ✅ List all habits
    @GetMapping
    public List<Habit> getAllHabits() {
        return habitRepository.findAll();
    }

    // ✅ Mark habit done
    @PutMapping("/{id}/log")
    public Habit markHabitDone(@PathVariable Long id) {
        Optional<Habit> optionalHabit = habitRepository.findById(id);
        if (optionalHabit.isPresent()) {
            Habit habit = optionalHabit.get();
            habit.setCompleted(true);
            return habitRepository.save(habit);
        } else {
            throw new RuntimeException("Habit not found");
        }
    }

    // ✅ View progress (just return habit detail)
    @GetMapping("/{id}/progress")
    public Habit getHabitProgress(@PathVariable Long id) {
        return habitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Habit not found"));
    }

    // ✅ Get all habits by User ID
    @GetMapping("/user/{userId}")
    public List<Habit> getHabitsByUser(@PathVariable Long userId) {
        return habitRepository.findByUserId(userId);
    }
}
