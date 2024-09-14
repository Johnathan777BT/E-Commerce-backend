package com.microservice.orders.controller;

import com.microservice.orders.entities.DetallePedido;
import com.microservice.orders.entities.Pedido;

import com.microservice.orders.service.IDetallePedidoService;
import com.microservice.orders.service.IPedidoService;

import jakarta.validation.Valid;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedidos")
public class PedidosController {

    @Autowired
    private IPedidoService orderService;
    
    @Autowired
    private IDetallePedidoService orderdetailService;
    
    
   
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> saveOrder(@RequestBody @Valid  Pedido pedido){
          
    	pedido.setTotal(BigDecimal.ZERO);
    	var save = orderService.save(pedido);    	
    
    	return ResponseEntity.status(HttpStatus.CREATED).body(save);
    }
    

    @PostMapping("/create-detail")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> saveDetail( @RequestBody @Valid DetallePedido pedido){
        
        var prod =orderdetailService.findAllProductsByProduct(pedido.getProductId());
        
        System.out.print("aqui tam "+ prod.isEmpty());
    	if(prod.isEmpty()) {
    		return ResponseEntity.notFound().build();
    	}
    
    	
    	var op = orderdetailService.findAllProductsByProduct(pedido.getProductId());//(prod.get(0).getProductId());
    	BigDecimal price =  BigDecimal.ZERO;
    	if(!op.isEmpty()) {
    		price=  op.get().getPrice();    	 
    	}
    	System.out.println("price:"+price);
    	System.out.print("pedidoID: "+pedido.getPedido().getId()); 
    	System.out.print("prodId: " + pedido.getProductId()+"--");
  
    	BigDecimal total2=price;
    	BigDecimal Cantotal = new BigDecimal(pedido.getAmount());
    	BigDecimal Tproducto = Cantotal.multiply(total2);
     
    	System.out.print("total: " + Tproducto+"--");
   
    	pedido.setTotal(Tproducto);
    	var save = orderdetailService.save(pedido);
    
    	
    	return ResponseEntity.status(HttpStatus.CREATED).body(save);
    }
    
    
    @PutMapping("/update/{id}")
	private ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody @Valid   Pedido pedido ) {
		
        Pedido save = null;
		if(!orderService.findById2(id).isEmpty()) {
			Pedido ped = orderService.findById(id);	
			System.out.println("nombre:" + pedido.getDate());		
			ped.setTotal(pedido.getTotal());			
			save = orderService.save(ped);
		
		}else {	
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error no se encuentra el pedido");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(save);
		
	}
    
    @PutMapping("/update-detail/{id}")
	private ResponseEntity<?> update(@PathVariable("id") Long id,  @RequestBody @Valid DetallePedido pedido ) {
		
    	DetallePedido save = null;
		if(!orderdetailService.findById2(id).isEmpty()) {
			DetallePedido ped = orderdetailService.findById(id);
	
			System.out.println("nombre:" + pedido.getAmount());
			ped.setAmount(pedido.getAmount());
			ped.setTotal(pedido.getTotal());		
			ped.setProductId(pedido.getProductId());			
			
			var op = orderdetailService.findAllProductsByProduct(pedido.getProductId());//(prod.get(0).getProductId());
	    	BigDecimal price =  BigDecimal.ZERO;
	    	if(!op.isEmpty()) {
	    		price=  op.get().getPrice();	    	 
	    	}
	    	System.out.println("price:"+price);
	    	System.out.print("pedidoID: "+pedido.getPedido().getId()); 
	    	System.out.print("prodId: " + pedido.getProductId()+"--");
	     
	    	BigDecimal total2=price;
	    	BigDecimal CanTotal = new BigDecimal(pedido.getAmount());
	    	BigDecimal Tproducto = CanTotal.multiply(total2);
			ped.setTotal(Tproducto);
			save = orderdetailService.save(ped);
		
		}else {	
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error no se encuentra el detalle pedido");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(save);
		
	}
    

    @GetMapping("/all")
    public ResponseEntity<?> findById(){
    	
    	System.out.println("all");
    	
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("/search-by-order/{pedidoId}")
    public ResponseEntity<?> findAllByPedidoId(@PathVariable Long pedidoId){
    	return ResponseEntity.ok(orderdetailService.findAllByPedidoId(pedidoId));
    }
    
    
    @GetMapping("/search/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return ResponseEntity.ok(orderService.findById(id));
    }

    @GetMapping("/search-by-product/{productId}")
    public ResponseEntity<?> findByIdOrder(@PathVariable Long productId){
        return ResponseEntity.ok(orderdetailService.findByProductId(productId));
    }
    
    
    @DeleteMapping("/delete-detail/{id}")
    public ResponseEntity<?> deleteDetail(@PathVariable Long id)
    {
    	return orderdetailService.delete(id);
    }
    
    
    @GetMapping("/search-detail/{id}")
    public ResponseEntity<?> findDetailById(@PathVariable Long id){
        return ResponseEntity.ok(orderdetailService.findById(id));
    }
    
    
   
    
}
