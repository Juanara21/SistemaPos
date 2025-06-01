package com.sistemapos.microserviceProduct.models.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    private String description;

    @NotNull(message = "El precio es obligatorio")
    private Double price;
}
