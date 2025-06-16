package com.fst.Jupitech.controllers;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.http.HttpStatus;


import com.fst.Jupitech.dto.UserDTO;
import com.fst.Jupitech.exceptions.InvalidCredentialsException;
import com.fst.Jupitech.services.UserService;
import com.fst.Jupitech.dto.AuthResponseDTO;
import com.fst.Jupitech.dto.LoginDTO;
    

@RestController

@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000") 
public class AuthentificationController {

    private final UserService userService;

    @Autowired
    public AuthentificationController(UserService userService) {
        this.userService = userService;
    }

   
    @PostMapping("/login")
    public ResponseEntity<?> loginClient(@RequestBody LoginDTO userDTO) {
    
        AuthResponseDTO authResponseDTO = userService.loginUser(userDTO);
        return ResponseEntity.ok(authResponseDTO);
 
    }
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO) {
   
            userService.registerUser(userDTO);
            return ResponseEntity.ok("User registered successfully"); 
    }
      @GetMapping("/welcome")
    public ResponseEntity<Map<String, String>> welcome() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "API is running successfully");
        response.put("welcomeMessage", "Welcome to Jupitech - Your Digital Art Gallery");
        response.put("backgroundImageUrl", "https://postimg.cc/HrzCPM8k");
        response.put("welcomeMessage", "Welcome to Jupitech - Your Digital Art Gallery");
        response.put("backgroundImageUrl", "https://postimg.cc/HrzCPM8k");
        return ResponseEntity.ok(response);
    }
   
}
