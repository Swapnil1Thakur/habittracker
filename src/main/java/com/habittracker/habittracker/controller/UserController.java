package com.habittracker.habittracker.controller;

import com.habittracker.habittracker.model.Habit;
import com.habittracker.habittracker.model.User;
import com.habittracker.habittracker.repository.HabitRepository;
import com.habittracker.habittracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private HabitRepository habitRepository;

    @Autowired
    private UserRepository userRepository;

    // ✅ Signup - POST /users

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }


    // ✅ List all users - GET /users
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}/habits")
    public List<Habit> getHabitsByUser(@PathVariable Long id) {
        return habitRepository.findByUserId(id);
    }

}
