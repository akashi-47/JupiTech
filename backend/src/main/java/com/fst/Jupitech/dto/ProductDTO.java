package com.fst.Jupitech.dto;

import com.fst.Jupitech.enums.CategorieEnum;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data 
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private int id;
    private String title;
    private String description;
    private double prix;
    private String status;
    private int stock;
    private String imageURL;
    @Min(value = 0, message = "Quantity cannot be negative")
    private int quantite;
   @Enumerated(EnumType.STRING)
    private CategorieEnum categorie;   
    private int sellerId; 


    
}
