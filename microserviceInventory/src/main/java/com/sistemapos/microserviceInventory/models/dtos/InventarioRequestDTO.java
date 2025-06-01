package com.sistemapos.microserviceInventory.models.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventarioRequestDTO {
    private Long productoId;
    private Integer quantity;

}
