
package com.fst.ArtSphere.services;

import java.util.Arrays;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.fst.ArtSphere.dto.UserDTO;
import com.fst.ArtSphere.repositories.UserRepository;
import com.fst.ArtSphere.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Data;
import com.fst.ArtSphere.dto.AuthResponseDTO;

import lombok.NoArgsConstructor;
import com.fst.ArtSphere.entities.User;
import com.fst.ArtSphere.entities.Role;
import com.fst.ArtSphere.repositories.RoleRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import com.fst.ArtSphere.security.JWTGenerator;
import org.springframework.security.core.Authentication;



@Data   @Service @NoArgsConstructor
public class UserService {
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  BCryptPasswordEncoder passwordEncoder;
    @Autowired
   private RoleRepository roleRepository;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTGenerator jwtGenerator;
    
   
    
    public User registerUser(@RequestBody UserDTO userDTO) {
        if(userRepository.existsByEmail(userDTO.getEmail())){
           return null;
           
        }

        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setNom(userDTO.getNom());
        user.setPrenom(userDTO.getPrenom());
        user.setAddress(userDTO.getAddress());
        user.setBiographie(userDTO.getBiographie());
        Role role = roleRepository.findByName(userDTO.getRole())
            .orElseThrow(() -> new RuntimeException("Role not found"));
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
        return user;
       
    }
    public AuthResponseDTO loginUser(@RequestBody UserDTO userDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                userDTO.getEmail(),
                userDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new AuthResponseDTO(token);

    }
    public User getUserById(int id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));
    }
     // Mise à jour du profil a adapter pour chaque type d'utilisateur
     public User updateUser(int userId, User userDetails) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        
        // Vérifier si le nouvel email n'est pas déjà utilisé par un autre utilisateur
        if (!user.getEmail().equals(userDetails.getEmail()) && 
            userRepository.findByEmail(userDetails.getEmail()).isPresent()) {
            throw new RuntimeException("Email déjà utilisé");
        }
        
        user.setNom(userDetails.getNom());
        user.setPrenom(userDetails.getPrenom());
        user.setEmail(userDetails.getEmail());
        user.setAddress(userDetails.getAddress());
        user.setBiographie(userDetails.getBiographie());

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
