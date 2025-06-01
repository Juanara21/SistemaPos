package com.sistemapos.microserviceInventory.services;

import com.sistemapos.microserviceInventory.models.dtos.CompraRequestDTO;
import com.sistemapos.microserviceInventory.models.dtos.CompraResponseDTO;
import com.sistemapos.microserviceInventory.models.dtos.InventarioRequestDTO;
import com.sistemapos.microserviceInventory.models.dtos.InventarioResponseDTO;

public interface InventarioService {
    CompraResponseDTO realizarCompra(CompraRequestDTO request);

    InventarioResponseDTO registrarInventario(InventarioRequestDTO request);

    InventarioResponseDTO actualizarInventario(Long productoId, InventarioRequestDTO request);

}
