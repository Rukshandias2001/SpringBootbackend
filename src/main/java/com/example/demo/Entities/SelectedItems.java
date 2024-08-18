package com.example.demo.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="selected_items")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelectedItems {


    @Id
    @Column(name = "selected_item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name ="productId")
    private long productId;

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

    @Column(name="email")
    private String email;

    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonIgnore
    private User user;



}
