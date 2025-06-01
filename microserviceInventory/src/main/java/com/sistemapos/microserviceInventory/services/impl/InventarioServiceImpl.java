package com.sistemapos.microserviceInventory.services.impl;

import com.sistemapos.microserviceInventory.models.dtos.*;
import com.sistemapos.microserviceInventory.models.entities.Inventario;
import com.sistemapos.microserviceInventory.repository.InventarioRepository;
import com.sistemapos.microserviceInventory.services.InventarioService;
import com.sistemapos.microserviceInventory.services.ServiceProperties;
import com.sistemapos.microserviceInventory.util.exceptions.BadRequestException;
import com.sistemapos.microserviceInventory.util.exceptions.NotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class InventarioServiceImpl implements InventarioService {

    private final InventarioRepository inventoryRepository;
    private final RestTemplate restTemplate;
    private final ServiceProperties serviceProperties;

    @Override
    public CompraResponseDTO realizarCompra(CompraRequestDTO request) {
        ProductoConsultaResponseDTO producto = consultarProducto(request.getProductId());

        if (!producto.isExists()) {
            throw new NotFoundException("El producto no existe");
        }

        Inventario inventario = inventoryRepository.findByProductId(request.getProductId())
                .orElseThrow(() -> new NotFoundException("Producto no encontrado en inventario"));

        if (inventario.getQuantity() < request.getCantidad()) {
            throw new BadRequestException("Inventario insuficiente para completar la compra");
        }

        inventario.setQuantity(inventario.getQuantity() - request.getCantidad());
        inventoryRepository.save(inventario);

        return new CompraResponseDTO(
                inventario.getProductId(),
                inventario.getQuantity(),
                "Compra realizada exitosamente");
    }

    @Override
    public InventarioResponseDTO registrarInventario(InventarioRequestDTO request) {
        ProductoConsultaResponseDTO producto = consultarProducto(request.getProductoId());

        if (!producto.isExists()) {
            throw new NotFoundException("El producto no existe");
        }

        if (inventoryRepository.existsByProductId(request.getProductoId())) {
            throw new BadRequestException("Ya existe inventario para este producto");
        }

        Inventario inventario = Inventario.builder()
                .productId(producto.getId())
                .quantity(request.getQuantity())
                .price(producto.getPrice())
                .build();

        inventoryRepository.save(inventario);

        return InventarioResponseDTO.builder()
                .productoId(inventario.getProductId())
                .cantidad(inventario.getQuantity())
                .precioUnitario(producto.getPrice())
                .registrado(true)
                .mensaje("Producto registrado en inventario correctamente")
                .build();
    }

    @Override
    public InventarioResponseDTO actualizarInventario(Long productoId, InventarioRequestDTO request) {
        Inventario inventario = inventoryRepository.findByProductId(productoId)
                .orElseThrow(
                        () -> new NotFoundException("Inventario no encontrado para el producto con ID: " + productoId));

        inventario.setQuantity(request.getQuantity());

        inventoryRepository.save(inventario);

        return InventarioResponseDTO.builder()
                .productoId(inventario.getProductId())
                .cantidad(inventario.getQuantity())
                .precioUnitario(inventario.getPrice())
                .registrado(true)
                .mensaje("Inventario actualizado correctamente")
                .build();
    }

    private ProductoConsultaResponseDTO consultarProducto(Long productoId) {
        String url = serviceProperties.getProductServiceUrl() + "/consultar/" + productoId;

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-API-KEY", serviceProperties.getApiKeyProductos()); // <-- cámbialo si lo cargas desde config

            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<ProductoConsultaResponseDTO> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    ProductoConsultaResponseDTO.class);

            return response.getBody();

        } catch (Exception e) {
            return ProductoConsultaResponseDTO.builder()
                    .exists(false)
                    .build();
        }
    }

}
