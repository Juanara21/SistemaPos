package com.sistemapos.microserviceProduct.services.impl;

import com.sistemapos.microserviceProduct.models.dtos.*;
import com.sistemapos.microserviceProduct.models.entities.Producto;
import com.sistemapos.microserviceProduct.repository.ProductoRepository;
import com.sistemapos.microserviceProduct.services.ProductoService;
import com.sistemapos.microserviceProduct.util.exceptions.BadRequestException;
import com.sistemapos.microserviceProduct.util.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

        private final ProductoRepository productRepository;

        @Override
        public ProductoResponseDTO createProduct(ProductoRequestDTO request) {
                productRepository.findByName(request.getName()).ifPresent(p -> {
                        throw new BadRequestException("Ya existe un producto con el nombre: " + request.getName());
                });

                Producto product = Producto.builder()
                                .name(request.getName())
                                .description(request.getDescription())
                                .price(request.getPrice())
                                .build();

                Producto savedProduct = productRepository.save(product);
                return toDTO(savedProduct);
        }

        @Override
        public ProductoResponseDTO getProductById(Long id) {
                Producto product = productRepository.findById(id)
                                .orElseThrow(() -> new NotFoundException("Producto no encontrado"));
                return toDTO(product);
        }

        @Override
        public List<ProductoResponseDTO> getAllProducts() {
                List<Producto> productos = productRepository.findAll();

                if (productos.isEmpty()) {
                        throw new NotFoundException("No hay productos registrados en el sistema.");
                }

                return productos.stream()
                                .map(this::toDTO)
                                .collect(Collectors.toList());
        }

        private ProductoResponseDTO toDTO(Producto product) {
                return new ProductoResponseDTO(
                                product.getId(),
                                product.getName(),
                                product.getDescription(),
                                product.getPrice());
        }

        @Override
        public ProductoConsultaResponseDTO consultarProductoPorId(Long id) {
                return productRepository.findById(id)
                                .map(p -> ProductoConsultaResponseDTO.builder()
                                                .id(p.getId())
                                                .name(p.getName())
                                                .description(p.getDescription())
                                                .price(p.getPrice())
                                                .exists(true)
                                                .build())
                                .orElse(ProductoConsultaResponseDTO.builder()
                                                .exists(false)
                                                .build());
        }
}
