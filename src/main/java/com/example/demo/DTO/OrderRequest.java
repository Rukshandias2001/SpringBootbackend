package com.example.demo.DTO;

import com.example.demo.Entities.OrderedList;
import com.example.demo.Entities.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequest {
    private int id;
    private String userName;
    private String lastName;
    private Date date;
    private Date expiryDate;
    private String nameOfCard;
    private String creditCardNumber;
    private String email;
    private String cardType;
    private String cvv;
    private double price;
    private List<Product> listOfProducts;
    private List<OrderedList> orderList;

}
