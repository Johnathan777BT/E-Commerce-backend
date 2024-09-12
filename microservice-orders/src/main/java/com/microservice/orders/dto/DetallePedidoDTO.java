package com.microservice.orders.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DetallePedidoDTO {

	
	 private Long id;
     private Long amount;
     private BigDecimal total;
     private Long id_pedido;
	 private String name;
}
