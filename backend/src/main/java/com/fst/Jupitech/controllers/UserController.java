package com.fst.Jupitech.controllers;

import com.fst.Jupitech.dto.UserDTO;
import com.fst.Jupitech.entities.User;
import com.fst.Jupitech.services.UserService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3030")
public class UserController {
    
    @Autowired
    private UserService userService;

  

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateProfile(
            @PathVariable int userId,
            @Valid @RequestBody UserDTO userDetails) {
        
            User updatedUser = userService.updateUser(userId, userDetails);
            return ResponseEntity.ok(updatedUser);
     
    }

    @PutMapping("/{userId}/password")
    public ResponseEntity<?> updatePassword(
            @PathVariable int userId,
            @RequestBody Map<String, String> passwords) {
        try {
            User updatedUser = userService.updatePassword(
                userId,
                passwords.get("oldPassword"),
                passwords.get("newPassword")
            );
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserProfile(@PathVariable int userId) {
        try {
            User user = userService.getUserById(userId);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/check-email")
    public ResponseEntity<Map<String, Boolean>> checkEmail(@RequestParam String email) {
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", userService.emailExists(email));
        return ResponseEntity.ok(response);
    }
}

