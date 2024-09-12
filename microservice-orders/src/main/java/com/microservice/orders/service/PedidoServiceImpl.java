package com.microservice.orders.service;

import com.microservice.orders.entities.Pedido;
import com.microservice.orders.persistence.PedidosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoServiceImpl implements IPedidoService {

    @Autowired
    private PedidosRepository orderRepository;

    @Override
    public List<Pedido> findAll() {
    	
        return (List<Pedido>) orderRepository.findAll();
    }

    @Override
    public Pedido findById(Long id) {
        return orderRepository.findById(id).orElseThrow();
    }
    
    @Override
    public Optional<Pedido> findById2(Long id) {
    	
    	return orderRepository.findById(id);
    }
    
    @Override
    public Pedido save(Pedido pedido) {
       return orderRepository.save(pedido);
    }

   
}
