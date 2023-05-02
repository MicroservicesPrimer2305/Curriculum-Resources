package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Planet;
import com.example.demo.service.PlanetService;

@RestController
public class ConsumerController {

	@Autowired
	PlanetService planetService;
	
	@GetMapping("/consumer/planets")
	public List<Planet> getPlanets(){
		return this.planetService.getPlanetFromOtherService();
	}
	
	@GetMapping("hello")
	public String hello() {
		return "Hello";
	}
}
