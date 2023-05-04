package com.example.demo.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.models.Moon;
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
	
	
	private final MoonService moonService;
	
	
	public PlanetService(RestTemplate restTemplate, MoonService moonService) {
		this.restTemplate = restTemplate;
		this.moonService = moonService;
	}
	
	
	
	//Resilience4J annotation 
	@Retry(name="planetSearch", fallbackMethod = "reliableMethod") //if this method fails, then we'll switch to reliableMethod!
	public List<Planet> getPlanetFromOtherService(){
		
		URI uri = URI.create("http://localhost:7001/planet-api/planets"); //"http://localhost:9700/planets"
		
		Planet[] allThePlanets = this.restTemplate.getForObject(uri, Planet[].class); // getting the array of planets from the endpoint 
		
//		this.restTemplate.get
		
		List<Planet> planetList = Arrays.asList(allThePlanets);
		
		
		
		//time to get the moons! 
		
		//DRY - Don't repeat yourself 
		List<Moon> moonList = moonService.getMoons();
		
		
		for(Planet p: planetList ) {
			
			for(Moon m: moonList) {
				
				if(p.getName().equals(m.getMyPlanet())) {
					
					//if the moon's myPlanet name match the planet, we'll add it to the Planet
					
					p.addMoon(m);
				}
			}
		}
		
		
		
		
		return planetList;
	}
	
	// Our "reliable" method! Need to provide an exception argument
	public List<Planet> reliableMethod(Exception e) {
		
		Planet p = new Planet(-1, e.getMessage(),0);
		List<Planet> planetList = new ArrayList<>();
		planetList.add(p);
		
		return planetList;
		
		
	}



	public void storePlanet(Planet p) {
		
		URI uri = URI.create("http://localhost:7001/planet-api/planet"); //"http://localhost:9700/planets"
		
		this.restTemplate.postForObject(uri, p, Boolean.class);
		
		
	}



	public boolean checkPlanetExists(Moon m) {
		
		List<Planet> planetList = this.getPlanetFromOtherService();
		
		for(Planet p: planetList) {
			if(m.getMyPlanet().equals(p.getName())) {
				return true;
			}
		}
		
		return false;
	}
	
}
