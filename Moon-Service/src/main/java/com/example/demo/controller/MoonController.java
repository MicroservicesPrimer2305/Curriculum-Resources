package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Moon;
import com.example.demo.repo.MoonRepo;

@RestController
public class MoonController {

	@Autowired
	MoonRepo moonRepo;
	
	@GetMapping("/moons")
	public List<Moon> getAllMoons(){
		return this.moonRepo.findAll();
	}
	
	@PostMapping("/moon")
	public Moon insertMoon(@RequestBody Moon m) {
		
		return this.moonRepo.save(m);
	}
}
