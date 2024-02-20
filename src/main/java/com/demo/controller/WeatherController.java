package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.service.WeatherService;

@RestController
public class WeatherController {
	
	@Autowired
	private WeatherService weatherService;
	
	@GetMapping("/weather")
	public ResponseEntity<?> getWeather() {
		
		weatherService.getWeather();
		
		return ResponseEntity.status(HttpStatus.CREATED).body("success");
		
	}
}
