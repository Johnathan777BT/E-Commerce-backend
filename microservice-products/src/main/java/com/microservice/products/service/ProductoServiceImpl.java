package com.microservice.products.service;


import com.microservice.products.client.PedidosClient;
import com.microservice.products.controller.sto.PedidoDTO;
import com.microservice.products.entity.Producto;
import com.microservice.products.http.response.OrderByProductResponse;
import com.microservice.products.persistence.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements IProductoService {

    @Autowired
    private IProductoRepository productRepository;

    @Autowired
    private PedidosClient pedidosClient;

    @Override
    public List<Producto> findAll() {
        return (List<Producto>) productRepository.findAll();
    }

    @Override
    public Producto findById(Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    
    @Override
    public Optional<Producto> findById2(Long id) {
    	
    	return productRepository.findById(id);
    }
    
    @Override
    public Producto save(Producto course) {
        return productRepository.save(course);
    }

    @Override
    public OrderByProductResponse findOrdersByProductId(Long prodId) {

        // Consultar si existe el producto
    	Producto prod = productRepository.findById(prodId).orElseThrow();

        // Obtener los pedidos
        List<PedidoDTO> ordersDTOList = pedidosClient.findAllOrdersByProduct(prod.getId());

        return OrderByProductResponse.builder()
                .name(prod.getName())
                .price(prod.getPrice())
                .url(prod.getUrl())
                .ordersDTOList(ordersDTOList)
                .build();
    }

	@Override
	public List<Producto> findAllByName(String name) {
		// TODO Auto-generated method stub
		return productRepository.findAllByName(name);
	}
}
