package com.example.demo.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("Clothings")
public class Clothings extends Product{

    @Column(name="size")
    private String size;

    @Column(name="colour")
    private String colour;


//    @Override
//    public String toString() {
//       return "Product name +\t"+getName()+"\t"+"Product type+\t"+getType()+"\t"+"Product price+\t"+getPrice()
//               +"\t Product quantity \t"+getQuantity()+"\t";
//    }
}
