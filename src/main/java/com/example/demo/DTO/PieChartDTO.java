package com.example.demo.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PieChartDTO {
    BigDecimal percentage;
    String type;
    Integer categoryId;
    BigDecimal percentageOfPoints;

}
