package com.fst.ArtSphere.dto;


import lombok.Data;

@Data
public class UserDTO {
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String address;
    private String businessName;
    private String role;
}
