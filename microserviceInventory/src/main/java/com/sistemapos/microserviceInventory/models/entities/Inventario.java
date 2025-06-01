package com.sistemapos.microserviceInventory.models.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "inventory")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inventario {

    @Id
    private Long productId;

    private Integer quantity;

    private Double price;
}
