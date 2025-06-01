package com.sistemapos.microserviceInventory.repository;

import com.sistemapos.microserviceInventory.models.entities.Inventario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {
    Optional<Inventario> findByProductId(Long productId);

    boolean existsByProductId(Long productId);

}
