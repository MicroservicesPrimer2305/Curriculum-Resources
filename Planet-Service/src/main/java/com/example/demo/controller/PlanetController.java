package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Planet;
import com.example.demo.repo.PlanetRepo;

@RestController
public class PlanetController {

	@Autowired
	PlanetRepo planetRepo;
	
	@GetMapping("/planets")
	public List<Planet> getAllPlanets(){
		
		return this.planetRepo.findAll();
	};
	
	@GetMapping("/planet/{name}")
	public Planet getPlanetByName(@PathVariable String name) {
		return this.planetRepo.findByName(name);
		
	};
	
	@PostMapping("/planet")
	public boolean insertPlanet(@RequestBody Planet p) {
		
		Planet deletedP = this.planetRepo.save(p);
		
		if(deletedP!= null) {
			return true;
		}else {
			return false;
		}
		
	};
	
	@DeleteMapping("/planet/{name}")
	public void deletePlanetByName(String name) {
		
		//basic validation, check if planet exists 
		Planet p = this.planetRepo.findByName(name);
		
		this.planetRepo.delete(p);
		
	};
	
}
