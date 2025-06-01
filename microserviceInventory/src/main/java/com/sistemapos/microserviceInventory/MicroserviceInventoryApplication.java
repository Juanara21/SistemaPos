package com.sistemapos.microserviceInventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.sistemapos.microserviceInventory.services.ServiceProperties;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;

@SpringBootApplication
public class MicroserviceInventoryApplication {

	private final ServiceProperties serviceProperties = new ServiceProperties();

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceInventoryApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		// Configura timeout
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		factory.setConnectTimeout(3000);
		factory.setReadTimeout(3000);

		RestTemplate restTemplate = new RestTemplate(factory);

		// Agrega interceptor con la API Key
		ClientHttpRequestInterceptor interceptor = (request, body, execution) -> {
			request.getHeaders().add("X-API-KEY", serviceProperties.getApiKeyProductos());
			return execution.execute(request, body);
		};

		restTemplate.setInterceptors(List.of(interceptor));

		return restTemplate;
	}

}
