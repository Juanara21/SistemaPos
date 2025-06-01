package com.sistemapos.microserviceProduct.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemapos.microserviceProduct.models.entities.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    Optional<Producto> findByName(String name);

}
