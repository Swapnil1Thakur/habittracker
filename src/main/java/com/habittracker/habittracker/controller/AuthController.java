package com.habittracker.habittracker.controller;

import com.habittracker.habittracker.dto.LoginRequest;
import com.habittracker.habittracker.dto.LoginResponse;
import com.habittracker.habittracker.model.User;
import com.habittracker.habittracker.repository.UserRepository;
import com.habittracker.habittracker.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    // ✅ Register new user
    @PostMapping("/signup")
    public String signup(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User registered successfully!";
    }

    // ✅ Login and return token
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );

            String token = jwtUtil.generateToken(loginRequest.getEmail());
            return new LoginResponse(token);
        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid credentials",e);
        }
    }
}
