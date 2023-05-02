package com.example.demo.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration //This tells Spring that this class has methods that will spit out beans 
public class ConfigureRestTemplate {
	
	
	@Bean //the builder is injected into this method by Spring 
	public RestTemplate restTemplateGenerator(RestTemplateBuilder builder) {
		
		return builder.build(); //build() method will return a RestTemplate
		
	}

}
