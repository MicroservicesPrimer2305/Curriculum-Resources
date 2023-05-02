package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.consul.discovery.ConditionalOnConsulDiscoveryEnabled;


/**
 * 
 * @author benjaminarayathel
 *
 *	This project will consume our other services - in this case the Planet-Service
 *
 *	My goal is to create another API that will spit out planets, but it will get those planets from our other Project ("Planet-Service")
 *
 *
 *
 */

@SpringBootApplication
@EnableDiscoveryClient
public class ConsumerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerServiceApplication.class, args);
	}

}
