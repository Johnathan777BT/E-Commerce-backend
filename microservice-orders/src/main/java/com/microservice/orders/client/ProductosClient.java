package com.microservice.orders.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservice.orders.dto.ProductoDTO;

@FeignClient(name = "msvc-product", url = "localhost:9090")
public interface ProductosClient {

	    @GetMapping("/api/productos/search/{productId}")
	    ProductoDTO findAllProductsByProduct(@PathVariable Long productId);
}
