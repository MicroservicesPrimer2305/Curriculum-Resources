package com.example.demo.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.models.Moon;

import io.github.resilience4j.retry.annotation.Retry;

@Service
public class MoonService {

	private final RestTemplate restTemplate; //This is specifically what we're going to use!
	
	private final String moonEndpoint  = "http://localhost:7001/moon-api/"; //whichever endpoint our gateway using  
	
	public MoonService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	
	@Retry(name = "getAllMoons", fallbackMethod = "fakeMoonList")
	public List<Moon> getMoons(){
		
		
		URI uri = URI.create(moonEndpoint + "moons"); //http://localhost:7001/moon-api/moons
		
		//this is telling the marshalling tool to convert the JSON it receives into the class we provided
		Moon[] moons = this.restTemplate.getForObject(uri, Moon[].class); 
		
		List<Moon> moonList = Arrays.asList(moons);
		
		
		return moonList;
		
	}
	
	public List<Moon> fakeMoonList(Exception e){
		return new ArrayList<>();
	}


	public void insertMoon(Moon m) {
		URI uri = URI.create(moonEndpoint + "moon"); //"http://localhost:9700/planets"
		
		this.restTemplate.postForObject(uri, m, Moon.class);
		
	}
	
}
