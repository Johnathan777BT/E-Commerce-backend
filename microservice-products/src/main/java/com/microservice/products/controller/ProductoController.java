package com.microservice.products.controller;


import com.microservice.products.entity.Producto;
import com.microservice.products.service.IProductoService;
import com.microservice.products.service.StorageService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private IProductoService productService;
    
    @Autowired
	 private StorageService storageservice;
	 
	@Autowired
	private HttpServletRequest request;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> saveCourse( @RequestBody @Valid Producto prod){
        	
    	var save = productService.save(prod);    	
    	return ResponseEntity.status(HttpStatus.CREATED).body(save);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findById(){
        return ResponseEntity.ok(productService.findAll());
    }
    
    @GetMapping("/all/{name}")
    public ResponseEntity<?> findByName(@PathVariable("name") String name){
        return ResponseEntity.ok(productService.findAllByName(name));
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return ResponseEntity.ok(productService.findById(id));
    }

    
    @PutMapping("/update/{id}")
	private ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody  @Valid Producto product ) {
		
    	Producto save = null;
		if(!productService.findById2(id).isEmpty()) {
			Producto prod = productService.findById(id);
	
			System.out.println("nombre:" + product.getName());
			prod.setPrice(product.getPrice());
			prod.setName(product.getName());
			prod.setUrl(product.getUrl());
			prod.setDescription(product.getDescription());
			prod.setStatus(product.getStatus());
			save = productService.save(prod);
		
		}else {	
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error no se encuentra el producto");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(save);
		
	}
    
    @PostMapping("/upload")
	public Map<String , String> upload(@RequestParam("file") MultipartFile file)
			throws IOException {
		
		String path = storageservice.store(file) ;
		String host = request.getRequestURL().toString().replace(request.getRequestURI(), "");
		String url = ServletUriComponentsBuilder.fromHttpUrl(host).path("/api/productos/media/").path(path).toUriString();
			
		return Map.of("url", url);
	
	}
    
    @GetMapping("/media/{filename:.+}")
	public ResponseEntity<Resource> getFile(@PathVariable String filename) throws IOException{
		
		Resource file = storageservice.loadAsResource(filename);
		String contenttype = Files.probeContentType(file.getFile().toPath());
		
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, contenttype).body(file);
		
	}
    
    
    @GetMapping("/search-order/{prodId}")
    public ResponseEntity<?> findOrdersByProductId(@PathVariable Long prodId){
        return ResponseEntity.ok(productService.findOrdersByProductId(prodId));
    }
    
    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<?> deleteDetail(@PathVariable Long id)
    {
    	
    	return productService.delete(id);
    }
}
