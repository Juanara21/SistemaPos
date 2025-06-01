package com.sistemapos.microserviceInventory.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiErrorDTO {
    private int status;
    private String message;
    private String path;
}
