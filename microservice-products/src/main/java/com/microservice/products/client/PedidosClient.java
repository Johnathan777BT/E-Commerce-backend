package com.microservice.products.client;

import com.microservice.products.controller.sto.PedidoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "msvc-order", url = "localhost:8090")
public interface PedidosClient {

    @GetMapping("/api/pedidos/search-by-product/{productId}")
    List<PedidoDTO> findAllOrdersByProduct(@PathVariable Long productId);
}
