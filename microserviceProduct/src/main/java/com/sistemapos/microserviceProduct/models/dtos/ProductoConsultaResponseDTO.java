package com.sistemapos.microserviceProduct.models.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoConsultaResponseDTO {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private boolean exists;
}
