package com.microservice.products;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {


	
	@Bean
	GroupedOpenApi publicAPi() {
		return GroupedOpenApi.builder().group("public-apis").pathsToMatch("/**").build();
	}
	//http://localhost:9090/swagger-ui/index.html#/
	//http://localhost:9090/v3/api-docs
	
	
}
