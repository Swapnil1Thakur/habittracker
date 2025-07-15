package com.habittracker.habittracker.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SecureController {

    @GetMapping("/secure-data")
    public ResponseEntity<?> getSecureData() {
        return ResponseEntity.ok("✅ This is a protected resource only accessible with valid token!");
    }
}
