package com.example.demo.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="ordered_product_list")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class OrderedList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "image")
    private String imageUrl;

    @Column(name = "type")
    private String type;

    @Column(name = "price")
    private double price;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "description")
    private String description;

    @Column(name = "category_id")
    private int categoryId;

    @ManyToOne
    @JoinColumn(name="order_id")
    @JsonIgnore
    private Orders order;





}
