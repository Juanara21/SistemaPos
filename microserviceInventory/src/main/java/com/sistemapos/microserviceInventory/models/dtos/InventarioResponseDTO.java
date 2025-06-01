package com.sistemapos.microserviceInventory.models.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventarioResponseDTO {
    private Long productoId;
    private Integer cantidad;
    private Double precioUnitario;
    private boolean registrado;
    private String mensaje;
}