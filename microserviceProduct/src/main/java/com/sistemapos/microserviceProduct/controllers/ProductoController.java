package com.sistemapos.microserviceProduct.controllers;

import com.sistemapos.microserviceProduct.models.dtos.*;
import com.sistemapos.microserviceProduct.services.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productService;

    @PostMapping
    public ResponseEntity<ProductoResponseDTO> createProduct(@RequestBody ProductoRequestDTO request) {
        return ResponseEntity.ok(productService.createProduct(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductoResponseDTO>> getAll() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/consultar/{id}")
    public ResponseEntity<ProductoConsultaResponseDTO> consultarProducto(@PathVariable Long id) {
        return ResponseEntity.ok(productService.consultarProductoPorId(id));
    }

}
