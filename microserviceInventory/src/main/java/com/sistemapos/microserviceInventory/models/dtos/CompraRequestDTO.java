package com.sistemapos.microserviceInventory.models.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompraRequestDTO {
    private Long productId;
    private Integer cantidad;
}
