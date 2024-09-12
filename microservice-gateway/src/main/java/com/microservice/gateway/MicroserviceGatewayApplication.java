package com.microservice.gateway;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceGatewayApplication.class, args);
	}
	

	 @Bean
	    public CorsWebFilter corsWebFilter() {
	        CorsConfiguration corsConfig = new CorsConfiguration();
	        corsConfig.setAllowedOrigins(List.of("http://localhost:4200"));
	        corsConfig.setAllowedOriginPatterns(List.of("/**"));
	        corsConfig.setAllowedMethods(List.of("PUT", "GET", "POST", "DELETE", "OPTIONS"));
	        corsConfig.setAllowedHeaders(List.of("*", "Authorization"));
	        corsConfig.setAllowCredentials(true);
	        corsConfig.setMaxAge(3600L);
	        return new CorsWebFilter(exchange -> corsConfig);
	    }
		
	}


