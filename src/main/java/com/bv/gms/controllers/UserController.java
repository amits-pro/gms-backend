package com.bv.gms.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bv.gms.dto.GrievanceDto;
import com.bv.gms.dto.GrievanceResponseDto;
import com.bv.gms.dto.LoginDto;
import com.bv.gms.dto.UpdateUserProfileDto;
import com.bv.gms.dto.UserProfileDto;
import com.bv.gms.dto.UserRegistrationDto;
import com.bv.gms.entities.Grievance;
import com.bv.gms.entities.User;
import com.bv.gms.service.UserService;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")  // Allow only requests from localhost:3000 (React/Angular front-end)
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody UserRegistrationDto user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }	

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginDto loginDto) throws Exception {
        try {
        	String token = userService.authenticateAndGenerateToken(loginDto);

            if (token == null) {
            	System.out.println("null token");
                return ResponseEntity.status(401).body("Invalid credentials");
            }
            return ResponseEntity.ok(token);
        }
        catch(Exception ex) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    	
    }

    @PutMapping("/users")
    public ResponseEntity<UserProfileDto> updateProfile(@RequestBody UpdateUserProfileDto dto) throws Exception {
        return ResponseEntity.ok(userService.updateProfile(dto));
    }
    
    @GetMapping("/user-profile")
    public ResponseEntity<UserProfileDto> getUserProfile(@RequestParam Long userId) throws Exception {
        return ResponseEntity.ok(userService.getProfile(userId));
    }
    
    @GetMapping("/grievances")
    public ResponseEntity<List<GrievanceResponseDto>> getgrievances(@RequestParam Long userId) throws Exception {
        return ResponseEntity.ok(userService.getAll(userId));
    	
    }
}
