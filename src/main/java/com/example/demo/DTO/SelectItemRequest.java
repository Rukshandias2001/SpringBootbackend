package com.example.demo.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.A;
import org.springframework.context.annotation.Bean;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SelectItemRequest {

    private long id;

    private String productName;

    private String imageUrl;


    private String type;


    private double price;


    private int quantity;


    private String description;


    private int categoryId;

    private String email;

}
