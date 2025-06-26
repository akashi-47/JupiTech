package com.fst.Jupitech.dto;


import com.fst.Jupitech.enums.RolesEnum;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class UserDTO {
    private String nom;
    private String prenom;
    @Email
    private String email;
    private String password;
    private String address;
    private String businessName;
    @Enumerated(EnumType.STRING)
    private RolesEnum role;
}
