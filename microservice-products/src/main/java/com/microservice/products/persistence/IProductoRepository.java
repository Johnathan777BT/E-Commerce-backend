package com.microservice.products.persistence;

import com.microservice.products.entity.Producto;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductoRepository extends CrudRepository<Producto, Long> {
	
	 @Query(value="SELECT  * FROM `productos`   WHERE name like  concat( '%', :nombre ,'%')"
				+ "			  "
				+ "          ", 
				nativeQuery=true)
	    List<Producto> findAllByName( @Param("nombre") String nombre);
}
