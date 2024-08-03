package com.provodromo.provodromo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ProvodromoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProvodromoApplication.class, args);
	}

}
