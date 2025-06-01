package com.sistemapos.microserviceInventory.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ServiceProperties {

    @Value("${service.product-service-url}")
    private String productServiceUrl;

    @Value("${api.key.productos}")
    private String apiKeyProductos;

    public String getProductServiceUrl() {
        return productServiceUrl;
    }

    public String getApiKeyProductos() {
        return apiKeyProductos;
    }
}
