package com.sistemapos.microserviceProduct.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoResponseDTO {

    private Long id;
    private String name;
    private String description;
    private Double price;
}
