package com.example.demo.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.models.Planet;

import io.github.resilience4j.retry.annotation.Retry;

/**
 * 
 * @author benjaminarayathel
 * 
 * 	Advantanges: 
 * 		Easy to deploy seperate service by themselves 
 * 		Easier to horizontally scale 
 *
 *	Issues we have right now: 
 *
 *		How do we track all of these services?
 *			We utilise a service registration and discovery service. This will keep track of all our services. 
 *			In our case, we'll be using Consul. 
 *
 *		How do our other services leverage other instances of my other service.? (It's like we need a gateway to funnel the traffic to the right services)
 *		What happens when something goes wrong? If a single services goes down, then that cause a cascading failure. 
 *		
 *
 */




//Is going to be responsible for getting the planet objects from my other project, Planet-Service 
@Service
public class PlanetService {

	private final RestTemplate restTemplate; //This is specifically what we're going to use!
	
	private final String endpoint  = "http://localhost:7001/"; //whichever endpoint our gateway using  
	
	
	public PlanetService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	
	
	//Resilience4J annotation 
	@Retry(name="planetSearch", fallbackMethod = "reliableMethod") //if this method fails, then we'll switch to reliableMethod!
	public List<Planet> getPlanetFromOtherService(){
		
		URI uri = URI.create("http://localhost:7001/planet-api/planets"); //"http://localhost:9700/planets"
		
		Planet[] allThePlanets = this.restTemplate.getForObject(uri, Planet[].class); // getting the array of planets from the endpoint 
		
//		this.restTemplate.get
		
		List<Planet> planetList = Arrays.asList(allThePlanets);
		
		return planetList;
	}
	
	// Our "reliable" method!
	public List<Planet> reliableMethod() {
		
		Planet p = new Planet(-1, "Fake",0);
		List<Planet> planetList = new ArrayList<>();
		planetList.add(p);
		
		return planetList;
		
		
	}
	
}
