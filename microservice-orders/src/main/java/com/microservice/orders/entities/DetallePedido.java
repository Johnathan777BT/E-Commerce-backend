package com.microservice.orders.entities;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import jakarta.persistence.JoinColumn;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "detalle_pedidos")
public class DetallePedido {

	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
 
	 @NotNull(message="la cantidad es requerida")
     @Min(1)
	 private Long amount;
	 
	 private BigDecimal total;

	 @NotNull(message="el id del producto es requerido")
	 @Column(name = "product_id")
	 private Long productId;

	 @ManyToOne	
	 @JoinColumn(name="pedido_id")
	 private Pedido pedido;
	 
	 
}
