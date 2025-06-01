// src/main/java/com/sistemapos/microserviceProduct/service/ProductService.java
package com.sistemapos.microserviceProduct.services;

import com.sistemapos.microserviceProduct.models.dtos.ProductoConsultaResponseDTO;
import com.sistemapos.microserviceProduct.models.dtos.ProductoRequestDTO;
import com.sistemapos.microserviceProduct.models.dtos.ProductoResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductoService {

    ProductoResponseDTO createProduct(ProductoRequestDTO request);

    ProductoResponseDTO getProductById(Long id);

    List<ProductoResponseDTO> getAllProducts();

    ProductoConsultaResponseDTO consultarProductoPorId(Long id);

}
