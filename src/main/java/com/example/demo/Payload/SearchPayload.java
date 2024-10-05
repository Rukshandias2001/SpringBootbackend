package com.example.demo.Payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchPayload {
    private int searchFrom;
    private int searchTo;
    private  long productId;
    private String nameOfProduct;
    private String selectedType;
    private int quantity;

}
