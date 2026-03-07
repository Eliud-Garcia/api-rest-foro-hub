package com.alura.api_rest_foro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class ApiRestForoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiRestForoApplication.class, args);
	}

}
