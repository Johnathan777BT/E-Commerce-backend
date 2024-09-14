package com.microservice.orders.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.microservice.orders.dto.DetallePedidoDTO;
import com.microservice.orders.dto.ProductoDTO;
import com.microservice.orders.entities.DetallePedido;


public interface IDetallePedidoService {

    List<DetallePedido> findAll();
    DetallePedido findById(Long id);
    DetallePedido save(DetallePedido pedido);
    List<DetallePedido> findByProductId(Long pedidoId);
    Optional<DetallePedido> findById2(Long id);
    Optional<ProductoDTO> findAllProductsByProduct(Long prodId);
    List<DetallePedidoDTO> findAllByPedidoId(Long pedidoId);
    ResponseEntity<?> delete(Long pedidoId);
    DetallePedido finById(Long pedidoId);
}
