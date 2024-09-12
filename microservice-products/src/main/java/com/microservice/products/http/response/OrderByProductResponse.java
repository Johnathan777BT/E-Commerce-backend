package com.microservice.products.http.response;

import com.microservice.products.controller.sto.PedidoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderByProductResponse {

    private String name;
    private BigDecimal price;
    private String url;
    private List<PedidoDTO> ordersDTOList;
}
