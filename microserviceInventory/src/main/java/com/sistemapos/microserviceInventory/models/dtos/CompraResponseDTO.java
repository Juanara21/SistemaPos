package com.sistemapos.microserviceInventory.models.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompraResponseDTO {
    private Long productId;
    private Integer cantidadFinal;
    private String message;
}
