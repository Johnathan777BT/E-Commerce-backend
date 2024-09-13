package com.microservice.products.service;

import com.microservice.products.entity.Producto;
import com.microservice.products.http.response.OrderByProductResponse;

import java.util.List;
import java.util.Optional;

public interface IProductoService {
    List<Producto> findAll();
    Producto findById(Long id);
    Producto save(Producto prod);
    Optional<Producto> findById2(Long id);
    OrderByProductResponse findOrdersByProductId(Long prodId);
    List<Producto> findAllByName(String name);
    void delete(Long id);
}
