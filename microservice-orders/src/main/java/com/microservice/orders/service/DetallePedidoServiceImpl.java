package com.microservice.orders.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.orders.client.ProductosClient;
import com.microservice.orders.dto.DetallePedidoDTO;
import com.microservice.orders.dto.ProductoDTO;
import com.microservice.orders.entities.DetallePedido;
import com.microservice.orders.entities.Pedido;
import com.microservice.orders.persistence.DetallePedidosRepository;
import com.microservice.orders.persistence.PedidosRepository;

import jakarta.persistence.Tuple;

@Service
public class DetallePedidoServiceImpl implements IDetallePedidoService  {

	  @Autowired
	   private DetallePedidosRepository orderRepository;

	@Override
	public List<DetallePedido> findAll() {
		// TODO Auto-generated method stub
		 return (List<DetallePedido>) orderRepository.findAll();
	}

	@Override
	public DetallePedido findById(Long id) {
		// TODO Auto-generated method stub
		 return orderRepository.findById(id).orElseThrow();
	}

	@Override
	public DetallePedido save(DetallePedido pedido) {
		// TODO Auto-generated method stub
		return orderRepository.save(pedido);
	}

	@Override
	public Optional<DetallePedido> findById2(Long id) {
		// TODO Auto-generated method stub
		return orderRepository.findById(id);
	}
	
	 @Override
	 public List<DetallePedido> findByProductId(Long prodId) {
	        return orderRepository.findAllByProductId(prodId);
	 }

	 @Override
	 public List<DetallePedidoDTO> findAllByPedidoId(Long pedidoId)
	 {
		 var consulta = orderRepository.findAllByPedidoId(pedidoId);
		
		 DetallePedidoDTO com = new  DetallePedidoDTO();
		 List<DetallePedidoDTO> values = new ArrayList<>();
		 for (Object[] r : consulta) {
		 
			  System.out.println("prodId: "+(Long) r[2]);
			 
			  var op = productosClient.findAllProductsByProduct((Long) r[2]);
			 
			 com= new DetallePedidoDTO( (Long) r[0],  (Long) r[3],  (BigDecimal) r[4],  (Long) r[1],  (String) op.getName()  );
			 
			 values.add(com);
		 }		
		 
		 return values; 
	 }
	 
	 
	 @Autowired
	 private ProductosClient productosClient;	 
	 
	 @Override
	 public Optional<ProductoDTO> findAllProductsByProduct(Long prodId) {

	     // Obtener los productos
	     var op = productosClient.findAllProductsByProduct(prodId);	      
	     var proDTO= ProductoDTO.builder().price(op.getPrice()).name(op.getName())
	       .stock(op.getStock()).build(); 
	       
	      return Optional.of(proDTO);	       
	  }

	@Override
	public void delete(Long pedidoId) {
		// TODO Auto-generated method stub
		orderRepository.deleteById(pedidoId);
	}

	@Override
	public DetallePedido finById(Long pedidoId) {
		// TODO Auto-generated method stub
		return orderRepository.findById(pedidoId).get();
	}
		
}

