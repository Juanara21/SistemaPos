package com.sistemapos.microserviceInventory.controllers;

import com.sistemapos.microserviceInventory.models.dtos.*;
import com.sistemapos.microserviceInventory.services.InventarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventarioController {

    private final InventarioService inventoryService;

    @PostMapping("/compra")
    public ResponseEntity<CompraResponseDTO> comprar(@RequestBody CompraRequestDTO request) {
        return ResponseEntity.ok(inventoryService.realizarCompra(request));
    }

    @PostMapping("/registrar")
    public ResponseEntity<InventarioResponseDTO> registrarInventario(@RequestBody InventarioRequestDTO request) {
        return ResponseEntity.ok(inventoryService.registrarInventario(request));
    }

    @PutMapping("/actualizar/{productoId}")
    public ResponseEntity<InventarioResponseDTO> actualizarInventario(
            @PathVariable Long productoId,
            @RequestBody InventarioRequestDTO request) {
        return ResponseEntity.ok(inventoryService.actualizarInventario(productoId, request));
    }

}
