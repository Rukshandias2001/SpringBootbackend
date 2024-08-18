package com.example.demo.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("Electronics")
@Entity
public class Electronics extends Product{

    @Column(name="warrenty")
    private String warrenty;

    @Column(name="brand")
    private String brand;



}
