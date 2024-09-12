package com.microservice.orders.service;

import com.microservice.orders.entities.Pedido;

import java.util.List;
import java.util.Optional;

public interface IPedidoService {

    List<Pedido> findAll();
    Pedido findById(Long id);
    Pedido save(Pedido pedido);    
    Optional<Pedido> findById2(Long id);    
}
