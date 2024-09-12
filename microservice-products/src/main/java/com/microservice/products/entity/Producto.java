package com.microservice.products.entity;

import java.math.BigDecimal;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message="el nombre es requerido")
    private String name;
    @NotNull(message="el precio es requerido")
    private BigDecimal price;
    private String url;
    @NotNull(message="el estado es requerido")
    private String status;
    private String description;
    
}
