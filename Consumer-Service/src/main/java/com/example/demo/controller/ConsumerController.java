package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Moon;
import com.example.demo.models.Planet;
import com.example.demo.service.MoonService;
import com.example.demo.service.PlanetService;

@RestController
public class ConsumerController {

	@Autowired
	PlanetService planetService;
	
	@Autowired
	MoonService moonService;
	
	@GetMapping("/planets")
	public List<Planet> getPlanets(){
		return this.planetService.getPlanetFromOtherService();
	}
	
	@GetMapping("/moons")
	public List<Moon> getMoons(){
		return this.moonService.getMoons();
	}
	
	@PostMapping("/planet")
	public void insertPlanet(@RequestBody Planet p) {
		this.planetService.storePlanet(p);
	}
	
	@PostMapping("/moon")
	public boolean insertMoon(@RequestBody Moon m) {
		
		if(this.planetService.checkPlanetExists(m)) {
			this.moonService.insertMoon(m);
			return true;
		}else {
			return false;
		}
		
	}
	
	@GetMapping("hello")
	public String hello() {
		return "Hello";
	}
}
