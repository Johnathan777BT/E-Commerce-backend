package com.microservice.products.controller.sto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {
    
    private BigDecimal total;
    private Long amount;
    private Long productId;
    private Long pedidoId;
}
