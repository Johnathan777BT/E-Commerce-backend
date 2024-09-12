package com.microservice.orders.entities;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique=true)
    private Long number;

   
    private BigDecimal total;
    

	@NotNull(message="la fecha inicio es requerida")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", locale = "es-CO", timezone = "America/Bogota")
	private Date date;

	
	@JsonIgnore
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	private Set<DetallePedido> ListDetailOrder= new HashSet<>();
	
}
