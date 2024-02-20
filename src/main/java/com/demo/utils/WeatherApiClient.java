package com.demo.utils;

import java.time.Duration;
import java.util.Map;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.demo.dto.WeatherData;

import jakarta.annotation.PostConstruct;

@Component
public class WeatherApiClient {
	private RestTemplate restTemplate;

	@PostConstruct
	private void init() {
		restTemplate = new RestTemplateBuilder().rootUri("https://opendata.cwa.gov.tw/api/v1/rest/datastore")
				.setConnectTimeout(Duration.ofSeconds(20)).build();
	}
	
	
	public WeatherData getWeatherData(String function) {
		MultiValueMap<String, Object>paraMap=new LinkedMultiValueMap<String, Object>();
		HttpHeaders headers=new HttpHeaders();
		HttpEntity<MultiValueMap> formEntity=new HttpEntity<MultiValueMap> (paraMap,headers);
		String auth="CWB-8E8C080A-F103-4971-9A68-A7DC879346CC";
		String format="JSON";
		
		//獲得36小時天氣預報
		ResponseEntity<WeatherData> data=restTemplate.exchange(
	            "/{Function}?Authorization={Authorization}&format={format}",HttpMethod.GET,formEntity,
	            WeatherData.class,Map.of("Function", function,"Authorization", auth,"format",format));
		System.out.println(data);
		System.out.println(data.getStatusCode());
		if(data!=null && data.getStatusCode().is2xxSuccessful()) {
			return data.getBody();
		}else {
			return null;
		}
		
    }
	
	
}
