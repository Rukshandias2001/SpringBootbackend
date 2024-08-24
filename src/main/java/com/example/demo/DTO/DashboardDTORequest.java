package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class DashboardDTORequest {

    private BigDecimal totalQuantity;
    private double totalPrice;
    private String productName;
    private String type;
    private Long productId;
    private double price;


}
