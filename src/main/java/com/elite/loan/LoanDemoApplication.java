package com.elite.loan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
@EnableEurekaClient
@SpringBootApplication
public class LoanDemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(LoanDemoApplication.class, args);
	}
	
	
	@Bean
	@LoadBalanced
	public WebClient.Builder builderRef(){
		return WebClient.builder();
	}
	
	@Bean
	public WebClient client(WebClient.Builder builderRef) {
		return builderRef.build();
	}
}
