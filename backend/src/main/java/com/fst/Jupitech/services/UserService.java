
package com.fst.Jupitech.services;

import java.util.Arrays;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import com.fst.Jupitech.dto.UserDTO;
import com.fst.Jupitech.repositories.UserRepository;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Data;
import com.fst.Jupitech.dto.AuthResponseDTO;
import com.fst.Jupitech.dto.LoginDTO;

import lombok.NoArgsConstructor;
import com.fst.Jupitech.entities.User;
import com.fst.Jupitech.enums.RolesEnum;
import com.fst.Jupitech.exceptions.InvalidCredentialsException;
import com.fst.Jupitech.exceptions.InvalidRoleException;
import com.fst.Jupitech.exceptions.UserAlreadyExistsException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import com.fst.Jupitech.security.JWTGenerator;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.security.core.Authentication;



@Data   @Service @NoArgsConstructor
@Validated  
public class UserService {
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  BCryptPasswordEncoder passwordEncoder;
  

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTGenerator jwtGenerator;
    @Autowired
    private ModelMapper modelMapper;
    
   
    
    public User registerUser(@RequestBody UserDTO userDTO) {
        if(userRepository.existsByEmail(userDTO.getEmail())){
          throw new UserAlreadyExistsException("Email already exists");
        }

        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setNom(userDTO.getNom());
        user.setPrenom(userDTO.getPrenom());
        user.setAddress(userDTO.getAddress());
        user.setBusinessname(userDTO.getBusinessName());
        try {
            // Use valueOf() to map the role string to the enum
            user.setRole(userDTO.getRole()); // assuming role is case-insensitive
        } catch (IllegalArgumentException e) {
            // Handle the case where the role is invalid
            throw new InvalidRoleException("Invalid role: " + userDTO.getRole());
        }
        
        userRepository.save(user);
        return user;
       
    }
    public AuthResponseDTO loginUser(@RequestBody LoginDTO userDTO) {
        try {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                userDTO.getEmail(),
                userDTO.getPassword()
            )
        );


        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        System.out.println("Token: " + token);
        
        return new AuthResponseDTO(token);
    } catch (BadCredentialsException e) {
        throw new InvalidCredentialsException("Invalid email or password.");
    }

    }

    public User getUserById(int id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));
    }
     public User updateUser(int userId, UserDTO userDetails) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
                 
        // Check if the new email is not already used by another user
        if (!user.getEmail().equals(userDetails.getEmail()) && 
             userRepository.findByEmail(userDetails.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already in use");
        }
        
        modelMapper.map(userDetails, user);
        return userRepository.save(user);
    }
    // Changer le mot de passe
    public User updatePassword(int userId, String oldPassword, String newPassword) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        
        if (!user.getPassword().equals(oldPassword)) {
            throw new RuntimeException("Ancien mot de passe incorrect");
        }
        
        user.setPassword(newPassword);
        return userRepository.save(user);
    }
    

    
    // Vérifier si un email existe
    public boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
	





    
}
