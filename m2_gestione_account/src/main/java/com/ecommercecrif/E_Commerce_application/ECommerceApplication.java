package com.ecommercecrif.E_Commerce_application;

import com.ecommercecrif.E_Commerce_application.security.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
//import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class ECommerceApplication {

	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}
	/*@Bean
	@LoadBalanced
	public WebClient.Builder getWebClientBUilder(){
		return WebClient.builder();
	}*/
	public static void main(String[] args) {
		SpringApplication.run(ECommerceApplication.class, args);
	}

}
