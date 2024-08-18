package com.example.demo.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="credit_card")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditCards {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="card_id")
    private int id;

    @Column(name="name_of_card")
    private String name;

    @Column(name="card_number")
    private String cardNumber;

    @Column(name="expiryDate")
    private String expiryDate;

    @Column(name="cvv")
    private String cvv;


    @OneToMany(mappedBy = "creditCardNumber", fetch = FetchType.EAGER) // Correct field name
    @JsonIgnore
    private List<Orders> orders;



}
