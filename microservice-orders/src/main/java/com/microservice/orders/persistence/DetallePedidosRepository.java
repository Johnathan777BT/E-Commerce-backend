package com.microservice.orders.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.microservice.orders.entities.DetallePedido;

import jakarta.persistence.Tuple;


@Repository
public interface DetallePedidosRepository  extends JpaRepository<DetallePedido, Long> {
	

    List<DetallePedido> findAllByProductId(Long prodId);
    
    @Query(value="SELECT  id, pedido_id, product_id,  amount, total FROM `detalle_pedidos`   WHERE pedido_id = ?"
			+ "			  "
			+ "          ", 
			nativeQuery=true)
    List<Object[]> findAllByPedidoId(Long pedidoId);

}
